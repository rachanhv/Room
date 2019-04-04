package com.example.roompersistence.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Reminder {

    @PrimaryKey
    public static String title;
    public static String description;
    public static String date;

}
