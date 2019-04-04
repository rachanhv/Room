package com.example.roompersistence.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface ReminderDao {

    @Insert
    void insertReminder(Reminder reminder);
}
