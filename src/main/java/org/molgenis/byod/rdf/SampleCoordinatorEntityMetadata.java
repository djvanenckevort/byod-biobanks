/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.rdf;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
public class SampleCoordinatorEntityMetadata implements MetaData {
    private static final String COORDINATOR_ID = "id";
    private static final String FULLNAME = "fullName";
    private static final String EMAIL = "emailAddress";
    private static final String PHONE = "phone";
    private static final List<String> columns = Arrays.asList();
    private final Map<String, String> attributeToOntologyUri;

    {
        attributeToOntologyUri = new HashMap<>();
        attributeToOntologyUri.put(COORDINATOR_ID, RdfExporter.COORDINATOR_PREFIX);
    }
    private final Map<String, Property> attributeProperty;

    {
        attributeProperty = new HashMap<>();
        attributeProperty.put(FULLNAME, FOAF.name);
        attributeProperty.put(EMAIL, RDFS.seeAlso);
        attributeProperty.put(PHONE, FOAF.phone);
    }
    private final Map<String, XSDDatatype> attrToDataType;

    {
        attrToDataType = new HashMap<>();
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
        return COORDINATOR_ID;
    }

}
