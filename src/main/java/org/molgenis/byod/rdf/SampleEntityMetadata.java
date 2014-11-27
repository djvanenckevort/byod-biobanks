package org.molgenis.byod.rdf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.vocabulary.RDFS;

public class SampleEntityMetadata implements MetaData
{
	public static final String SAMPLE_ID = "specimenID";
	public static final String SAMPLE_TYPE_CODE = "specimenType_code";
	public static final String ANATOMIC_SOURCE_CODE = "anatomicSource_code";
	public static final String LONG_TERM_STROAGE_CONTAINER = "longTermStorageContainer";
	public static final String STORAGE_TEMPERATURE = "storageTemperature";
	public static final String PRESERVATION_TYPE_CODE = "preservationType_code";
	public static final String PARTICIPANT_AGE_UNIT = "participantAge_unit";
	public static final String PARTICIPANT_AGE = "participantAge_value";
	public static final String SPECIMEN_COUNT = "specimenCount";

	private static final List<String> columns = Arrays.asList(SAMPLE_ID, SAMPLE_TYPE_CODE, SAMPLE_TYPE_CODE,
			LONG_TERM_STROAGE_CONTAINER, STORAGE_TEMPERATURE, PRESERVATION_TYPE_CODE, PARTICIPANT_AGE_UNIT,
			PARTICIPANT_AGE, SPECIMEN_COUNT);

	private Map<String, String> attributeToOntologyUri;
	{
		attributeToOntologyUri = new HashMap<String, String>();
		attributeToOntologyUri.put(SAMPLE_ID, RdfExporter.SAMPLE_PREFIX);
		attributeToOntologyUri.put(SAMPLE_TYPE_CODE, RdfExporter.NCI_PREFIX);
		attributeToOntologyUri.put(ANATOMIC_SOURCE_CODE, RdfExporter.NCI_PREFIX);
		attributeToOntologyUri.put(PRESERVATION_TYPE_CODE, RdfExporter.NCI_PREFIX);
	}

	private Map<String, Property> attributeProperty;
	{
		attributeProperty = new HashMap<String, Property>();
		attributeProperty.put(SAMPLE_TYPE_CODE, RDFS.seeAlso);
		attributeProperty.put(ANATOMIC_SOURCE_CODE, RDFS.seeAlso);
		attributeProperty.put(LONG_TERM_STROAGE_CONTAINER, RDFS.seeAlso);
		attributeProperty.put(STORAGE_TEMPERATURE, RDFS.seeAlso);
		attributeProperty.put(PRESERVATION_TYPE_CODE, RDFS.seeAlso);
		attributeProperty.put(PARTICIPANT_AGE_UNIT, RDFS.seeAlso);
		attributeProperty.put(PARTICIPANT_AGE, RDFS.seeAlso);
		attributeProperty.put(SPECIMEN_COUNT, RDFS.seeAlso);
	}

	private Map<String, XSDDatatype> attrToDataType;
	{
		attrToDataType = new HashMap<String, XSDDatatype>();
		attrToDataType.put(SAMPLE_ID, XSDDatatype.XSDstring);
		attrToDataType.put(SAMPLE_TYPE_CODE, XSDDatatype.XSDstring);
		attrToDataType.put(LONG_TERM_STROAGE_CONTAINER, XSDDatatype.XSDstring);
		attrToDataType.put(STORAGE_TEMPERATURE, XSDDatatype.XSDinteger);
		attrToDataType.put(ANATOMIC_SOURCE_CODE, XSDDatatype.XSDstring);
		attrToDataType.put(PRESERVATION_TYPE_CODE, XSDDatatype.XSDstring);
		attrToDataType.put(PRESERVATION_TYPE_CODE, XSDDatatype.XSDstring);
		attrToDataType.put(PARTICIPANT_AGE_UNIT, XSDDatatype.XSDstring);
		attrToDataType.put(PARTICIPANT_AGE, XSDDatatype.XSDinteger);
		attrToDataType.put(SPECIMEN_COUNT, XSDDatatype.XSDinteger);
	}

	public String getIdentifierAttr()
	{
		return SAMPLE_ID;
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

	public List<String> getAttributeNames()
	{
		return columns;
	}
}
