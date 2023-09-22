package org.white.sleepuntilburst.unit;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.white.sleepuntilburst.Entity.Dream;
import org.white.sleepuntilburst.Entity.DreamEvent;
import org.white.sleepuntilburst.SleepUntilBurst;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DreamParser {

    private static List<Dream> dreamChecked = new ArrayList<>();

    public static List<Dream> getDreamChecked() {
        return dreamChecked;
    }

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
           SleepUntilBurst.getInstance().getLogger().warning(e.getMessage());
           return null;
        }

    }

    public void dreamCheck(){
        List<Dream> dreams = getDream();
        System.out.println(dreams.size());
        for (Dream tempDream : dreams){
            if(tempDream.getName().isEmpty()){
                SleepUntilBurst.getInstance().getLogger().warning("有梦的name未设定,该梦无法生效");
                continue;
            }
            boolean eventCheck = true;
            if(tempDream.getType() == null){
                SleepUntilBurst.getInstance().getLogger().warning(tempDream.getName()+"的type未设定，无法生效");
                eventCheck = false;
            }
            if(tempDream.getWeight() == null){
                SleepUntilBurst.getInstance().getLogger().warning(tempDream.getName()+"的weight未设定，无法生效");
                eventCheck = false;
            }
            if(tempDream.getDes() == null){
                SleepUntilBurst.getInstance().getLogger().warning(tempDream.getName()+"的des未设定，无法生效");
                eventCheck = false;
            }

            for(DreamEvent event : tempDream.getDreamEventList()){
                if(event.getEventType().isEmpty()){
                    SleepUntilBurst.getInstance().getLogger().warning(tempDream.getName()+"中含有未设定type的事件，该梦无法生效");
                    continue;
                }
                switch (event.getEventType()) {
                    case "exp" -> eventCheck = checkCount(tempDream.getName(), event);
                    case "give", "entity" ->
                            eventCheck = checkCount(tempDream.getName(), event) && checkContent(tempDream.getName(), event);
                    case "potion" ->
                            eventCheck = checkCount(tempDream.getName(), event) && checkSec(tempDream.getName(), event) && checkContent(tempDream.getName(), event);
                    case "boom" , "lightning"->
                            eventCheck = checkCount(tempDream.getName(), event) && checkSec(tempDream.getName(), event);
                    case "command" -> eventCheck = checkCommand(tempDream.getName(), event);
                    default ->
                            SleepUntilBurst.getInstance().getLogger().warning(tempDream.getName() + "中的类型为" + event.getEventType() + "事件的不是合法类型，该梦无法生效");
                }
            }
            if(!eventCheck){
                continue;
            }
            dreamChecked.add(tempDream);
        }
    }

    private boolean checkCount(String dreamName,DreamEvent event){
        if(event.getCount() == null){
            SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为"+event.getEventType()+"事件的count不能为空，该梦无法生效");
            return false;
        }else {
            return true;
        }
    }

    private boolean checkSec(String dreamName,DreamEvent event){
        if(event.getSec() == null){
            SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为"+event.getEventType()+"事件的sec不能为空，该梦无法生效");
            return false;
        }else {
            return true;
        }
    }

    private boolean checkContent(String dreamName,DreamEvent event){

        if(event.getEventContent() == null){
            SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为"+event.getEventType()+"事件的term不能为空，该梦无法生效");
            return false;
        }
        if(event.getEventType().equals("give")){
            if(Material.matchMaterial(event.getEventContent(), true) == null){
                SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为give事件的term是错误的物品，该梦无法生效");
                return false;
            }
        }
        if(event.getEventType().equals("entity")){
            try {
                EntityType.valueOf(event.getEventContent().toUpperCase());
            } catch (IllegalArgumentException e) {
                SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为entity事件的term是错误的实体，该梦无法生效");
                return false;
            }
        }
        if(event.getEventType().equals("potion")){
            if(PotionEffectType.getByName(event.getEventContent()) == null){
                SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为potion事件的term是错误的药水效果，该梦无法生效");
                return false;
            }
        }
        return true;
    }

    private boolean checkCommand(String dreamName,DreamEvent event){
        if(event.getCommand() == null){
            SleepUntilBurst.getInstance().getLogger().warning(dreamName+"中的类型为"+event.getEventType()+"事件的command不能为空，该梦无法生效");
            return false;
        }else {
            return true;
        }
    }
}
