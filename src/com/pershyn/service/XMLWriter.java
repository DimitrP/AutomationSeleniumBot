package com.pershyn.service;

import com.pershyn.entity.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;


public class XMLWriter {
	
	public static Logger logger = Logger.getLogger(XMLWriter.class.getName());

    public static void initWritingXML (List<Item> listOfItems) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        
        
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                doc.createElementNS("https://www.amazon.com", "Items");
            //append root element to document
            doc.appendChild(rootElement);

            //append first child element to root element
            for (Item itemEl : listOfItems){
            	rootElement.appendChild(getItem(doc, itemEl));
            }
            

            //append second child
//            rootElement.appendChild(getItem(doc, "2", "Lisa", "35", "Manager", "Female"));

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("resource/amazonItems.xml"));

            //write data
            transformer.transform(source, console);
            transformer.transform(source, file);
            logger.info("XML file written");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Node getItem(Document doc, Item item) {
        Element itemElement = doc.createElement("Item");

        //set id attribute
//        employee.setAttribute("id", id);

        //create name element
        itemElement.appendChild(getItemElements(doc, itemElement, "itemId", item.getItemId()));

        //create age element
        itemElement.appendChild(getItemElements(doc, itemElement, "itemName", item.getItemName()));

        //create role element
        itemElement.appendChild(getItemElements(doc, itemElement, "itemPrice", Integer.toString(item.getPrice())));

        itemElement.appendChild(getItemElements(doc, itemElement, "itemUrl", item.getUrl()));

      

        return itemElement;
    }


    //utility method to create text node
    private static Node getItemElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

}
