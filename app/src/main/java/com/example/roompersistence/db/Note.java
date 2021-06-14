package com.example.roompersistence.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.example.roompersistence.utils.DateConverter;

import java.util.Date;

//declare table name if not mentioned default class name takes as a table name
@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    //Primary key should be NotNull
    public int id;

    //ColumnInfo annotaion is for declare column name is not declared object name will be the column anem
    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @TypeConverters(DateConverter.class)
    public Date forDay;

    @Ignore
    Bitmap picture;
}
