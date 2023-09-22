package org.white.sleepuntilburst.Entity;

public class DreamEvent {
    //事件类型
    private String eventType;

    //数量
    private Integer count;

    //事件详情(物品名或状态名等)
    private String eventContent;

    //指令
    private String command;

    //持续时间
    private Integer sec;

    public DreamEvent() {
    }

    public String getEventType() {
        return eventType;
    }

    public Integer getCount() {
        return count;
    }

    public String getEventContent() {
        return eventContent;
    }

    public String getCommand() {
        return command;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getSec() {
        return sec;
    }

    public void setSec(Integer sec) {
        this.sec = sec;
    }
}
