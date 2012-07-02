package com.neo4j.sqlimport.mapper;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The mapper
 * User: luanne
 * Date: 30/06/12
 * Time: 10:10 PM
 */
public class Mapper {

    private Map<String, MappedNode> mappedNodes;

    /**
     * Initialize the mapper
     * Reads the mapping file and sets up data for nodes and relationships
     *
     * @param mappingFile
     */
    public void init(String mappingFile) {
        mappedNodes = new HashMap<String, MappedNode>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = null;

            doc = builder.parse(mappingFile);


            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            XPathExpression expression = xPath.compile("//node");
            Object result = expression.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                MappedNode mappedNode = new MappedNode();
                Node node = nodes.item(i);
                String nodeType = node.getAttributes().getNamedItem("type").getTextContent();
                mappedNode.setNodeType(nodeType);
                mappedNode.setIndexName(node.getAttributes().getNamedItem("index").getTextContent());

                //Get the table information
                XPathExpression tableExpr = xPath.compile("//node[@type='" + nodeType + "']/table");
                NodeList tableResult = (NodeList) tableExpr.evaluate(doc, XPathConstants.NODESET);

                Node tableNode = tableResult.item(0);  //Only one table per node type. Remember to change this if we start supporting multiplt tables
                Table table = new Table();
                table.setTableName(tableNode.getAttributes().getNamedItem("name").getTextContent());

                //Get column information
                List<Column> columns = new ArrayList<Column>();
                for (int k = 0; k < tableNode.getChildNodes().getLength(); k++) {
                    Node n = tableNode.getChildNodes().item(k);
                    NamedNodeMap attributes = n.getAttributes();
                    if (n.getNodeName().equals("field")) {

                        if(attributes.getNamedItem("relationship")!=null) {
                            RelationshipColumn relationshipColumn=new RelationshipColumn();
                            relationshipColumn.setColumnName(attributes.getNamedItem("name").getTextContent());
                            relationshipColumn.setRelationshipType(attributes.getNamedItem("relationship").getTextContent());
                            relationshipColumn.setColumnNodeType(attributes.getNamedItem("nodeType").getTextContent());
                            if(attributes.getNamedItem("direction").getTextContent().equals("outgoing")) {
                                relationshipColumn.setStartNode(true);
                            }
                            columns.add(relationshipColumn);

                        }
                        else {
                            PropertyColumn propertyColumn=new PropertyColumn();
                            propertyColumn.setColumnName(attributes.getNamedItem("name").getTextContent());
                            if(attributes.getNamedItem("property")!=null) {
                                propertyColumn.setPropertyName(attributes.getNamedItem("property").getTextContent());

                            }
                            else {
                                propertyColumn.setPropertyName(attributes.getNamedItem("name").getTextContent());

                            }
                            if(attributes.getNamedItem("indexKey")!=null && attributes.getNamedItem("indexKey").getTextContent().equals("true")) {
                                propertyColumn.setIndexed(true);
                            }
                            columns.add(propertyColumn);
                        }
                    }
                }
                table.setColumns(columns);
                mappedNode.setTable(table);

                mappedNodes.put(nodeType, mappedNode);
            }
        } catch (SAXException e) {
            throw new MappingException("Error initializing the mapper", e);
        } catch (IOException e) {
            throw new MappingException("Error initializing the mapper", e);
        } catch (XPathExpressionException e) {
            throw new MappingException("Error with XPath while initializing the mapper", e);
        } catch (ParserConfigurationException e) {
            throw new MappingException("Error with parser configuration while initializing the mapper", e);
        }


    }

    public Map<String, MappedNode> getMappedNodes() {
        return mappedNodes;
    }
}
