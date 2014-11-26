/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.emx2rdf;

import java.nio.file.Paths;
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
    private String outfile;

    @Override
    public void run(String... strings) throws Exception {
        ExcelReader reader = new ExcelReader(Paths.get(infile));
        while (reader.hasNext()) {
            System.err.println(reader.next());
        }
    }

}
