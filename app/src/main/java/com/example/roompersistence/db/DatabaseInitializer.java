package com.example.roompersistence.db;

public class DatabaseInitializer {

    public static Note addNotes(final AppDatabase database, final String title, final String description) {
        Note note = new Note();
        note.title = title;
        note.description = description;
        database.noteDao().insertReminder(note);
        return note;
    }
}
