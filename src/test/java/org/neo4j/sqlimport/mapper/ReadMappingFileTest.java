package org.neo4j.sqlimport.mapper;

import com.neo4j.sqlimport.mapper.Mapper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * Test to read the mapping file
 * User: luanne
 * Date: 30/06/12
 * Time: 9:54 PM
 */
public class ReadMappingFileTest {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

    }

    public void testNodeTypes() {
        Mapper mapper = new Mapper();
        mapper.init("mappingExample.xml");
    }
}
