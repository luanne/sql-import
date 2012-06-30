package com.neo4j.sqlimport.mapper;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.List;

/**
 * The mapper
 * User: luanne
 * Date: 30/06/12
 * Time: 10:10 PM
 */
public class Mapper {

    private List<MappedNode> mappedNodes;

    /**
     * Initialize the mapper
     * Reads the mapping file and sets up data for nodes and relationships
     *
     * @param mappingFile
     */
    public void init(String mappingFile) {
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
                System.out.println("nodes.item(i).getNodeValue() = " + nodes.item(i).getAttributes().getNamedItem("type").getTextContent());
            }
        } catch (SAXException e) {
            throw new MappingException("Error initializing the mapper",e);
        } catch (IOException e) {
            throw new MappingException("Error initializing the mapper",e);
        } catch (XPathExpressionException e) {
            throw new MappingException("Error with XPath while initializing the mapper",e);
        } catch (ParserConfigurationException e) {
            throw new MappingException("Error with parser configuration while initializing the mapper",e);
        }


    }


}
