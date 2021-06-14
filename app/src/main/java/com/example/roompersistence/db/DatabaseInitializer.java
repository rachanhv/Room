package com.example.roompersistence.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.Date;

public class DatabaseInitializer {

    public static void addNotes(final AppDatabase database, final String title, final String description, Date date) {
        Note note = new Note();
        note.title = title;
        note.description = description;
        note.forDay = date;
        database.noteDao().insertReminder(note);
    }
    public static void deleteNoteTable(final AppDatabase database){
        database.noteDao().deleteAll();
    }

    public static void deleteSelectedRowFromTable(final AppDatabase database,Note note){
        database.noteDao().deleteRecord(note);
    }
}
