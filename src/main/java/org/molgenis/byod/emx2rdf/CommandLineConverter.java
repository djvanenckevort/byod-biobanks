/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.emx2rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import java.nio.file.Paths;
import java.util.Map;
import org.molgenis.byod.rdf.ParticipantEntityMetadata;
import org.molgenis.byod.rdf.RdfExporter;
import static org.molgenis.byod.rdf.RdfExporter.prefixMaps;
import org.molgenis.byod.rdf.SampleEntityMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
@Component
public class CommandLineConverter implements CommandLineRunner {
    @Value("${in:in.xlsx}")
    private String infile;

    @Value("${out:out.ttl}")
    private String outdir;

    @Override
    public void run(String... strings) throws Exception {
        ExcelReader reader = new ExcelReader(Paths.get(infile));
        ModelMaker modelMaker = ModelFactory.createFileModelMaker(outdir);
        String outfile = Paths.get(infile).toFile().getName();
        outfile = outfile.substring(0, outfile.lastIndexOf('.'));
        outfile += ".ttl";
        Model model = modelMaker.createModel(outfile);
        model.setNsPrefixes(prefixMaps);

        RdfExporter rdfExporter = new RdfExporter();
        while (reader.hasNext()) {
            Map<String, String> entity = reader.next();
            model.add(rdfExporter.convert(entity, new ParticipantEntityMetadata()));
            model.add(rdfExporter.convert(entity, new SampleEntityMetadata()));
        }
        model.close();
        modelMaker.close();
    }

}
