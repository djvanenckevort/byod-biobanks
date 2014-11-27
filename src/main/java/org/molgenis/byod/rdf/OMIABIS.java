/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.rdf;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.impl.PropertyImpl;
import com.hp.hpl.jena.rdf.model.impl.ResourceImpl;

/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
public class OMIABIS {
    public static final Property START_DATE = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001007");
    public static final Property STOP_DATE = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001006");
    public static final Property DATE_LAST_UPDATED = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0001005");
    public static final Property COORDINATOR = new PropertyImpl(RdfExporter.OMIABIS_PREFIX, "OMIABIS_0000099");
    public static final Property SAMPLE = new PropertyImpl("http://purl.obolibrary.org/obo/OBI_0000747");
    public static final Property DIAGNOSIS = new PropertyImpl("http://purl.obolibrary.org/obo/OGMS_0000073");
    public static final Property STORAGE_TEMPERATURE = new PropertyImpl("http://purl.obolibrary.org/obo/OMIABIS_0001013");
    public static final Property LONG_TERM_STORAGE = new PropertyImpl("http://purl.obolibrary.org/obo/OBI_0302893");
    public static final Property ANATOMIC_SOURCE = new PropertyImpl("http://purl.obolibrary.org/obo/REO_0000132");
    public static final Property PRESERVATION_TYPE = new PropertyImpl("http://purl.org/net/OCRe/OCRe.owl#OCRE400020");
    public static final Property SPECIMEN_COUNT = new PropertyImpl("http://purl.org/net/OCRe/OCRe.owl#OCRE400115");
    public static final Resource SAMPLE_COLLECTION = new ResourceImpl("http://purl.obolibrary.org/obo/OMIABIS_0000036");
    public static final Property AGE_AT_SAMPLING = new PropertyImpl("http://ctmm-trait.nl/miabis/age");
    public static final Property AGE_UNIT = new PropertyImpl("http://ctmm-trait.nl/miabis/age_unit");
    public static final Property ETHNICITY = new PropertyImpl("http://ctmm-trait.nl/miabis/ethnicity");
}
