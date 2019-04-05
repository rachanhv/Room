package com.example.roompersistence.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertReminder(Note note);

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note")
    List<Note> getAllDefaultNotes();

    @Query("DELETE FROM note")
    void deleteAll();
}
