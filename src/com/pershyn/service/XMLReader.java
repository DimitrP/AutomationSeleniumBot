package com.pershyn.service;

import com.pershyn.entity.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLReader {

    public static List<Item> initReading() {
        String filePath = "resource/amazonItems.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Item> itemList = new ArrayList<Item>();;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("Item");
            //now XML is loaded as Document in memory, lets convert it to Object List
          //  itemList = new ArrayList<Item>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                itemList.add(getItem(nodeList.item(i)));
            }
            //lets print Employee list information
//            for (Item itemEl : itemList) {
//                System.out.println(itemEl.toString());
//            }
        } catch (SAXException | ParserConfigurationException | IOException e1) {
           // e1.printStackTrace();
        	
        }
        return itemList;
    }


    private static Item getItem(Node node) {
        //XMLReaderDOM domReader = new XMLReaderDOM();
        Item item = new Item();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            item.setItemId(getTagValue("itemId", element));
            item.setItemName(getTagValue("itemName", element));
            item.setPrice(getTagValue("itemPrice", element));
            item.setUrl(getTagValue("itemUrl",element));
            
        }

        return item;
    }


    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}
