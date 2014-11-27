/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.molgenis.byod.rdf;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David van Enckevort <david@allthingsdigital.nl>
 */
public class OrganisationEntityMetadata implements MetaData {

    private static final String ORGANISATION_ID = "id";
    private static final String ORGANISATION_NAME = "organisationName";
    private static final String HOMEPAGE_URL = "homepageURL";
    private static final List<String> columns = Arrays.asList(ORGANISATION_ID,
            ORGANISATION_NAME, HOMEPAGE_URL);

    private final Map<String, String> attributeToOntologyUri;

    {
        attributeToOntologyUri = new HashMap<>();
        attributeToOntologyUri.put(ORGANISATION_ID, RdfExporter.ORGANISATION_PREFIX);
    }
    private final Map<String, Property> attributeProperty;

    {
        attributeProperty = new HashMap<>();
        attributeProperty.put(HOMEPAGE_URL, FOAF.homepage);
        attributeProperty.put(ORGANISATION_NAME, FOAF.name);
        attributeProperty.put(ORGANISATION_ID, DC.identifier);
    }
    private final Map<String, XSDDatatype> attrToDataType;

    {
        attrToDataType = new HashMap<>();
        attrToDataType.put(ORGANISATION_ID, XSDDatatype.XSDstring);
        attrToDataType.put(ORGANISATION_NAME, XSDDatatype.XSDstring);
        attrToDataType.put(HOMEPAGE_URL, XSDDatatype.XSDanyURI);
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
        return ORGANISATION_ID;
    }

    public Resource getResourceClass() {
        return FOAF.Organization;
    }
}
