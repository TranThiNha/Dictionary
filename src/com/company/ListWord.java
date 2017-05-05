package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 3/19/2017.
 */
public class ListWord {

    List<Word> list;

    public ListWord(String filename){
       try {
           list = new ArrayList<>();
           File fXmlFile = new File(filename);
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);
           doc.getDocumentElement().normalize();
           NodeList nList = doc.getElementsByTagName("record");
           for (int temp = 0; temp < nList.getLength(); temp++) {

               int x = nList.getLength();
               Node nNode = nList.item(temp);
               if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                   Element eElement = (Element) nNode;
                   Word word = new Word();
                   word.setWord(eElement.getElementsByTagName("word").item(0).getTextContent());
                   word.setMeanWord(eElement.getElementsByTagName("meaning").item(0).getTextContent());
                   list.add(word);
               }
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }

    public List<Word> getList() {
        return list;
    }
}
