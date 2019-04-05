package com.example.roompersistence.db;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseInitializer {

    private String DB_NAME = "db_task";

    AppDatabase appDatabase;

    public DatabaseInitializer(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public static Note addNotes(final AppDatabase database, final String title, final String description) {
        Note note = new Note();
        note.title = title;
        note.description = description;
        database.noteDao().insertReminder(note);
        return note;
    }
}
