package com.example.roompersistence.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    //Insert values to notes table in order
    @Insert
    void insertReminder(Note note);

    //Write Quires to get row of vaules or complete data of values in Live data
    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes")
    List<Note> getAllDefaultNotes();

    //Delete complete table
    @Query("DELETE FROM notes")
    void deleteAll();

    //Delete selected row from notes table
    @Delete
    void deleteRecord(Note note);
}
