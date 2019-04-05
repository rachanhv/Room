package com.example.roompersistence.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Note {

    @PrimaryKey(autoGenerate=true)
    @NonNull
    public int id;

    public String title;

    public String description;
    //public static String date;

}
