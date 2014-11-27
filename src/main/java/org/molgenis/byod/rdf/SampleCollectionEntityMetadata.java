package org.molgenis.byod.rdf;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.util.Arrays;

public class SampleCollectionEntityMetadata implements MetaData {

    public static final String COLLECTION_NAME = "collectionName";
    public static final String START_DATE = "startDate";
    public static final String STOP_DATE = "stopDate";
    public static final String DATE_LAST_UPDATED = "datelastUpdated";
    public static final String ORGANISATION = "organisation";
    private static final String COORDINATOR = "sampleCoordinator";
    private static final String SAMPLE = "specimenID";
    private static final List<String> columns = Arrays.asList(COLLECTION_NAME,
            START_DATE, STOP_DATE, DATE_LAST_UPDATED, ORGANISATION, COORDINATOR, SAMPLE);
    private final Map<String, String> attributeToOntologyUri;

    {
        attributeToOntologyUri = new HashMap<>();
        attributeToOntologyUri.put(COLLECTION_NAME, RdfExporter.SAMPLE_COLLECTIONS_PREFIX);
        attributeToOntologyUri.put(START_DATE, RdfExporter.OMIABIS_PREFIX);
        attributeToOntologyUri.put(STOP_DATE, RdfExporter.OMIABIS_PREFIX);
        attributeToOntologyUri.put(DATE_LAST_UPDATED, RdfExporter.OMIABIS_PREFIX);
        attributeToOntologyUri.put(ORGANISATION, RdfExporter.ORGANISATION_PREFIX);
        attributeToOntologyUri.put(COORDINATOR, RdfExporter.COORDINATOR_PREFIX);
        attributeToOntologyUri.put(SAMPLE, RdfExporter.SAMPLE_PREFIX);
    }

    private final Map<String, Property> attributeProperty;

    {
        attributeProperty = new HashMap<>();
        attributeProperty.put(COLLECTION_NAME, RDFS.seeAlso);
        attributeProperty.put(START_DATE, OMIABIS.START_DATE);
        attributeProperty.put(STOP_DATE, OMIABIS.STOP_DATE);
        attributeProperty.put(DATE_LAST_UPDATED, OMIABIS.DATE_LAST_UPDATED);
        attributeProperty.put(ORGANISATION, RDFS.seeAlso);
        attributeProperty.put(COORDINATOR, OMIABIS.COORDINATOR);
        attributeProperty.put(SAMPLE, OMIABIS.SAMPLE);
    }

    private final Map<String, XSDDatatype> attrToDataType;

    {
        attrToDataType = new HashMap<>();
        attrToDataType.put(COLLECTION_NAME, XSDDatatype.XSDstring);
        attrToDataType.put(START_DATE, XSDDatatype.XSDdate);
        attrToDataType.put(STOP_DATE, XSDDatatype.XSDdate);
        attrToDataType.put(DATE_LAST_UPDATED, XSDDatatype.XSDdate);
        attrToDataType.put(ORGANISATION, XSDDatatype.XSDIDREF);
        attrToDataType.put(COORDINATOR, XSDDatatype.XSDIDREF);
        attrToDataType.put(SAMPLE, XSDDatatype.XSDIDREF);
    }

    @Override
    public List<String> getAttributeNames() {
        return columns;
    }

    @Override
    public XSDDatatype getDataType(String attributeName) {

        return attrToDataType.containsKey(attributeName) ? attrToDataType.get(attributeName) : XSDDatatype.XSDstring;
    }

    @Override
    public String getOntologyPrefix(String attributeName) {
        return attributeToOntologyUri.get(attributeName);
    }

    @Override
    public Property getAttrProperty(String attributeName) {
        return attributeProperty.get(attributeName);
    }

    @Override
    public String getIdentifierAttr() {
        return COLLECTION_NAME;
    }
}
