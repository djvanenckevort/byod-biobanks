/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.emx2rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.rdf.model.Statement;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.molgenis.byod.rdf.OrganisationEntityMetadata;
import org.molgenis.byod.rdf.ParticipantEntityMetadata;
import org.molgenis.byod.rdf.RdfExporter;
import static org.molgenis.byod.rdf.RdfExporter.prefixMaps;
import org.molgenis.byod.rdf.SampleCollectionEntityMetadata;
import org.molgenis.byod.rdf.SampleCoordinatorEntityMetadata;
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
        ModelMaker modelMaker = ModelFactory.createFileModelMaker(outdir);
        String outfile = Paths.get(infile).toFile().getName();
        outfile = outfile.substring(0, outfile.lastIndexOf('.'));
        outfile += ".ttl";
        Model model = modelMaker.createModel(outfile);
        model.setNsPrefixes(prefixMaps);

        exportOrganisation(model);
        exportSampleCoordinator(model);
        for (String dataset : strings) {
            if (!dataset.startsWith("--")) {
                System.err.println("processing " + dataset);
                exportDataset(model, dataset);
            }
        }
        model.close();
        modelMaker.close();
    }

    private void exportDataset(Model model, String dataset) throws IOException, InvalidFormatException {
        RdfExporter rdfExporter = new RdfExporter();
        ExcelReader reader = new ExcelReader(Paths.get(infile), dataset);
        Set<List<Statement>> participants = new HashSet<>();
        Set<List<Statement>> collections = new HashSet<>();
        while (reader.hasNext()) {
            Map<String, String> entity = reader.next();
            participants.add(rdfExporter.convert(entity, new ParticipantEntityMetadata()));
            model.add(rdfExporter.convert(entity, new SampleEntityMetadata()));
            collections.add(rdfExporter.convert(entity, new SampleCollectionEntityMetadata()));
        }
        participants.forEach(p -> model.add(p));
        collections.forEach(c -> model.add(c));

    }

    private void exportOrganisation(Model model) throws IOException, InvalidFormatException {
        RdfExporter rdfExporter = new RdfExporter();
        ExcelReader reader = new ExcelReader(Paths.get(infile), "decode_organisations");
        while (reader.hasNext()) {
            Map<String, String> entity = reader.next();
            model.add(rdfExporter.convert(entity, new OrganisationEntityMetadata()));
        }
    }

    private void exportSampleCoordinator(Model model) throws IOException, InvalidFormatException {
        RdfExporter rdfExporter = new RdfExporter();
        ExcelReader reader = new ExcelReader(Paths.get(infile), "decode_persons");
        while (reader.hasNext()) {
            Map<String, String> entity = reader.next();
            model.add(rdfExporter.convert(entity, new SampleCoordinatorEntityMetadata()));
        }
    }
}
