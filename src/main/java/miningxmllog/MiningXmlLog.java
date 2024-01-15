/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miningxmllog;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author andy
 */
public class MiningXmlLog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // TODO code application logic here
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("simulation.xml"));
        NodeList listRecords = document.getElementsByTagName("record");
        
        for (int i = 0; i < listRecords.getLength(); i++) {
            Node node = listRecords.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) node;
                NodeList children = e.getChildNodes();
                
                for (int j = 0; j < children.getLength(); j++) {
                    Node child = children.item(j);
                    if(child.getNodeType() == Node.ELEMENT_NODE){
                        System.out.println("Propiedad: " + child.getNodeName() + ", Valor: " + child.getTextContent());
                    }
                }
                        
            }
        }
    }
    
    
    
}
