package com.example.minddrop.model;

import java.util.Date;

public class MindDropItem {
    private String content;
    private Date timestamp;

    public MindDropItem(String content , Date  timestamp){
        this.content=content;
        this.timestamp=timestamp;
    }

    public String getContent(){
        return content ;
    }

    public void setContent(String content){
        this.content=content;
    }

    public Date getTimestamp(){
        return timestamp;
    }

    public void  setTimestamp(Date timestamp){
        this.timestamp=timestamp;
    }

}

