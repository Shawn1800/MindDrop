package com.example.minddrop.model.databse;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.minddrop.model.MindDropItem;

//this is pur databse class where we tie the entity class ,dao and db config together
@Database(entities = {MindDropItem.class}, version = 1 ,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MindDropDao mindDropDao();

    private static volatile AppDatabase INSTANCE;//CREATE INSTANCE OF APPDATABASE

    public static AppDatabase getDatabase(final Context context) { //context give access to app resources
        if (INSTANCE == null) {  // First time?
            synchronized (AppDatabase.class) { //create a new instance only for one ,if there are 2 intances it will make sure to run only one at a time  and use appdatabase class
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),//create a instance
                                AppDatabase.class, "mind-drop-database") //
                        .build();
            }
        }
        return INSTANCE;  // RETURN SAME ONE ALWAYS
    }
}