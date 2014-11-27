package org.molgenis.byod.rdf;

import java.util.List;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

public interface MetaData
{
	String getIdentifierAttr();

	List<String> getAttributeNames();

	String getOntologyPrefix(String attributeName);

	Property getAttrProperty(String attributeName);

	XSDDatatype getDataType(String attributeName);

        Resource getResourceClass();
}
