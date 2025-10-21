package com.example.minddrop.model;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.minddrop.model.databse.DateConverter;
import com.example.minddrop.model.databse.AppDatabase;

import java.util.Date;

@Entity(tableName = "mind_drop_items")
@TypeConverters({DateConverter.class}) // Tell Room how to handle the Date type
public class MindDropItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;

    //Room can't store Date objects directly.
    // We need a TypeConverter to convert the Date to a Long (timestamp) and back.
    private Date timestamp;


    // Constructor without id, as Room will generate it
    public MindDropItem(String content , Date  timestamp){
        this.content=content;
        this.timestamp=timestamp;
    }

    public int getId() {
        return id;
    }
    public void setId(int id ){
        this.id=id;

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

