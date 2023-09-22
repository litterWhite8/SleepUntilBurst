package org.white.sleepuntilburst.unit;


import org.bukkit.Bukkit;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.white.sleepuntilburst.Entity.Dream;
import org.white.sleepuntilburst.Entity.DreamEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DreamParser {

    public List<Dream> getDream() {
        try{
            File inputFile = Files.dreamConfig;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList personNodes = doc.getElementsByTagName("dream");
            List<Dream> dreams = new ArrayList<>();
            for (int i = 0; i < personNodes.getLength(); i++) {

                Element personElement = (Element) personNodes.item(i);
                Dream dream = new Dream();

                dream.setName(personElement.getAttribute("name"));
                dream.setType(personElement.getAttribute("type"));
                dream.setDes(personElement.getAttribute("des"));
                dream.setWeight(Integer.valueOf(personElement.getAttribute("weight")));


                NodeList addressNodes = personElement.getElementsByTagName("event");
                for (int j = 0; j < addressNodes.getLength(); j++) {
                    DreamEvent dreamEvent = new DreamEvent();
                    Element dreamEventElement = (Element) addressNodes.item(j);

                    if(!dreamEventElement.getAttribute("term").isEmpty()){
                        dreamEvent.setEventContent(dreamEventElement.getAttribute("term"));
                    }
                    if(!dreamEventElement.getAttribute("command").isEmpty()){
                        dreamEvent.setCommand(dreamEventElement.getAttribute("command"));
                    }
                    if(!dreamEventElement.getAttribute("type").isEmpty()){
                        dreamEvent.setEventType(dreamEventElement.getAttribute("type"));
                    }
                    if(!dreamEventElement.getAttribute("count").isEmpty()){
                        dreamEvent.setCount(Integer.valueOf(dreamEventElement.getAttribute("count")));
                    }
                    if(!dreamEventElement.getAttribute("sec").isEmpty()){
                        dreamEvent.setSec(Integer.valueOf(dreamEventElement.getAttribute("sec")));
                    }

                    dream.addEvent(dreamEvent);
                }
                dreams.add(dream);
            }
            return dreams;
        }catch (Exception e){
            Bukkit.getLogger().info(e.getMessage());

            return null;
        }

    }
}
