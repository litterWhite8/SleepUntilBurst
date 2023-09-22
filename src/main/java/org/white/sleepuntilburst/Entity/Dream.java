package org.white.sleepuntilburst.Entity;

import java.util.ArrayList;
import java.util.List;

public class Dream {
    private String type;

    private String name;

    private Integer weight;

    private String des;


    private List<DreamEvent> dreamEventList = new ArrayList<>();


    //减少连续好梦或连续噩梦的概率
    public void reduceWeights(float discipline){
        this.weight = discipline >= 0 ? Math.round(discipline*this.weight) : 0;
    }
    public void addEvent(DreamEvent event){
        dreamEventList.add(event);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DreamEvent> getDreamEventList() {
        return dreamEventList;
    }

    public void setDreamEventList(List<DreamEvent> dreamEventList) {
        this.dreamEventList = dreamEventList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
