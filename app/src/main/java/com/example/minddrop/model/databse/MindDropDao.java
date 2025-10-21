package com.example.minddrop.model.databse;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.minddrop.model.MindDropItem;
import com.example.minddrop.presenter.MainPresenter;

import java.util.List;
@Dao
public interface MindDropDao {
    @Insert
    Void insert(MindDropItem item); //calling our data entity class that hold tables and item is the instance of the maindropitem class

    @Query("Select * from mind_drop_items Order By timestamp Desc")
    List<MindDropItem>  //converts databse rows to java objects
    getAllItems(); // room generates this method ,i choose the method name
}



