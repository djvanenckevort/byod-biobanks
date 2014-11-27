package org.molgenis.byod.rdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.RDFS;

public class RdfExporter {

    public final static String CATALOG_PARTICIPANT_ID_PREFIX = "http://ctmm-trait.nl/participants/";
    public final static String ORGANIZATION_PREFIX = "http://ctmm-trait.nl/organisation/";
    public final static String SAMPLE_PREFIX = "http://ctmm-trait.nl/sample/";
    public final static String SAMPLE_COLLECTIONS_PREFIX = "http://ctmm-trait.nl/samplecollections/";
    public final static String OMIABIS_PREFIX = "http://purl.obolibrary.org/obo/";
    public final static String NCI_PREFIX = "http://purl.bioontology.org/ontology/NCIT/";
    public final static String DEFAULT_SEPARATOR = ",";

    public final static Map<String, String> prefixMaps = new HashMap<>();

    static {
        prefixMaps.put("participants", CATALOG_PARTICIPANT_ID_PREFIX);
        prefixMaps.put("sample", SAMPLE_PREFIX);
        prefixMaps.put("sample_collection", SAMPLE_COLLECTIONS_PREFIX);
        prefixMaps.put("miabis", OMIABIS_PREFIX);
        prefixMaps.put("organization", ORGANIZATION_PREFIX);
        prefixMaps.put("nci", NCI_PREFIX);
        prefixMaps.put("foaf", FOAF.getURI());
        prefixMaps.put("rdfs", RDFS.getURI());
        prefixMaps.put("dcterms", DCTerms.getURI());
    }

    public List<Statement> convert(Map<String, String> entity, MetaData metaData) {
        List<Statement> statements = new ArrayList<>();
        String identifierAttr = metaData.getIdentifierAttr();
        Resource resource = ResourceFactory.createResource(createUri(metaData.getOntologyPrefix(identifierAttr), entity
                .get(identifierAttr)));
        for (String attr : entity.keySet()) {
            if (!attr.equals(identifierAttr) && metaData.getAttributeNames().contains(attr)) {
                String valueGroup = entity.get(attr);
                for (String value : valueGroup.split(DEFAULT_SEPARATOR)) {
                    RDFNode node;
                    if (metaData.getOntologyPrefix(attr) != null) {
                        node = ResourceFactory
                                .createResource(createUri(metaData.getOntologyPrefix(attr), value.trim()));
                    } else {
                        node = ResourceFactory.createTypedLiteral(value.trim(), metaData.getDataType(attr));
                    }
                    statements.add(ResourceFactory.createStatement(resource, metaData.getAttrProperty(attr), node));
                }
            }
        }
        return statements;
    }

    public String createUri(String prefix, String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix).append(id);
        return stringBuilder.toString();
    }
}
