/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;

/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
public class OMIABIS {
    public static Property START_DATE = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001007");
    public static Property STOP_DATE = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001006");
    public static Property DATE_LAST_UPDATED = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001005");
    public static Property COORDINATOR = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0000099");
    public static Property SAMPLE = new PropertyImpl("http://purl.obolibrary.org/obo/OBI_0000747");
    public static Property DIAGNOSIS = new PropertyImpl("http://purl.obolibrary.org/obo/OGMS_0000073");
}
