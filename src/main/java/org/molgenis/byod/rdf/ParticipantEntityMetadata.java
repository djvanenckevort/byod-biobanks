package org.molgenis.byod.rdf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDFS;

public class ParticipantEntityMetadata implements MetaData
{
	public static final String PARTICIPANT_ID = "participantID";
	public static final String DIAGNOSIS_CODE = "diagnosis_code";
	public static final String ETHNICITY = "ethnicity";
	public static final String GENDER = "gender";

	private static final List<String> columns = Arrays.asList(PARTICIPANT_ID, DIAGNOSIS_CODE, ETHNICITY, GENDER);

	private Map<String, String> attributeToOntologyUri;
	{
		attributeToOntologyUri = new HashMap<String, String>();
		attributeToOntologyUri.put(DIAGNOSIS_CODE, RdfExporter.NCI_PREFIX);
		attributeToOntologyUri.put(PARTICIPANT_ID, RdfExporter.CATALOG_PARTICIPANT_ID_PREFIX);
	}

	private Map<String, Property> attributeProperty;
	{
		attributeProperty = new HashMap<String, Property>();
		attributeProperty.put(DIAGNOSIS_CODE, RDFS.seeAlso);
		attributeProperty.put(ETHNICITY, DCTerms.identifier);
		attributeProperty.put(GENDER, FOAF.gender);
	}

	private Map<String, XSDDatatype> attrToDataType;
	{
		attrToDataType = new HashMap<String, XSDDatatype>();
		attrToDataType.put(PARTICIPANT_ID, XSDDatatype.XSDstring);
		attrToDataType.put(DIAGNOSIS_CODE, XSDDatatype.XSDstring);
		attrToDataType.put(ETHNICITY, XSDDatatype.XSDstring);
		attrToDataType.put(GENDER, XSDDatatype.XSDstring);
	}

	public XSDDatatype getDataType(String attributeName)
	{
		return attrToDataType.containsKey(attributeName) ? attrToDataType.get(attributeName) : XSDDatatype.XSDstring;
	}

	public String getOntologyPrefix(String attributeName)
	{
		return attributeToOntologyUri.containsKey(attributeName) ? attributeToOntologyUri.get(attributeName) : null;
	}

	public Property getAttrProperty(String attributeName)
	{
		return attributeProperty.containsKey(attributeName) ? attributeProperty.get(attributeName) : null;
	}

	public String getIdentifierAttr()
	{
		return PARTICIPANT_ID;
	}

	public List<String> getAttributeNames()
	{
		return columns;
	}
}