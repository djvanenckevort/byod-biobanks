package org.molgenis.byod.rdf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class ParticipantEntityMetadata implements MetaData {

    public static final String PARTICIPANT_ID = "participantID";
    public static final String DIAGNOSIS_CODE = "diagnosis_code";
    public static final String ETHNICITY = "ethnicity";
    public static final String GENDER = "gender";
    public static final String SAMPLE_ID = "specimenID";

    private static final List<String> columns = Arrays.asList(PARTICIPANT_ID,
            DIAGNOSIS_CODE, ETHNICITY, GENDER, SAMPLE_ID);

    private final Map<String, String> attributeToOntologyUri;

    {
        attributeToOntologyUri = new HashMap<>();
        attributeToOntologyUri.put(DIAGNOSIS_CODE, RdfExporter.NCI_PREFIX);
        attributeToOntologyUri.put(PARTICIPANT_ID, RdfExporter.CATALOG_PARTICIPANT_ID_PREFIX);
        attributeToOntologyUri.put(SAMPLE_ID, RdfExporter.SAMPLE_PREFIX);
    }

    private final Map<String, Property> attributeProperty;

    {
        attributeProperty = new HashMap<>();
        attributeProperty.put(DIAGNOSIS_CODE, OMIABIS.DIAGNOSIS);
        attributeProperty.put(ETHNICITY, OMIABIS.ETHNICITY);
        attributeProperty.put(GENDER, FOAF.gender);
        attributeProperty.put(SAMPLE_ID, OMIABIS.SAMPLE);
    }

    private final Map<String, XSDDatatype> attrToDataType;

    {
        attrToDataType = new HashMap<>();
        attrToDataType.put(PARTICIPANT_ID, XSDDatatype.XSDstring);
        attrToDataType.put(DIAGNOSIS_CODE, XSDDatatype.XSDstring);
        attrToDataType.put(ETHNICITY, XSDDatatype.XSDstring);
        attrToDataType.put(GENDER, XSDDatatype.XSDstring);
        attrToDataType.put(SAMPLE_ID, XSDDatatype.XSDIDREF);
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
        return PARTICIPANT_ID;
    }

    @Override
    public List<String> getAttributeNames() {
        return columns;
    }

    @Override
    public Resource getResourceClass() {
        return FOAF.Person;
    }
}
