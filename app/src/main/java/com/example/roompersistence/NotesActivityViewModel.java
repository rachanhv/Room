package com.example.roompersistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.roompersistence.db.AppDatabase;
import com.example.roompersistence.db.DatabaseInitializer;
import com.example.roompersistence.db.Note;

import java.util.List;

public class NotesActivityViewModel extends AndroidViewModel {

    public LiveData<List<Note>> notes;
    public List<Note> noteList;
    //public final LiveData<List<Note>> notes;
    AppDatabase mDb;
    DatabaseInitializer databaseInitializer;

    public NotesActivityViewModel(Application application) {
        super(application);
        createDb();
        notes = mDb.noteDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotesResult() {
        notes = mDb.noteDao().getAllNotes();
        return notes;
    }

    public void addNote(String title, String discription) {
        databaseInitializer = new DatabaseInitializer(this.getApplication());
        databaseInitializer.addNotes(mDb, title, discription);
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());
    }

    public List<Note> getDefaultNotes() {
        return noteList = mDb.noteDao().getAllDefaultNotes();
    }
}
