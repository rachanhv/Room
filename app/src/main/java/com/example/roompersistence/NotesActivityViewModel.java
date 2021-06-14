package com.example.roompersistence;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.roompersistence.db.AppDatabase;
import com.example.roompersistence.db.DatabaseInitializer;
import com.example.roompersistence.db.Note;

import java.util.Date;
import java.util.List;

public class NotesActivityViewModel extends AndroidViewModel {

    public LiveData<List<Note>> notes;
    public List<Note> noteList;
    AppDatabase mDb;

    public NotesActivityViewModel(Application application) {
        super(application);
        createDb();
        notes = mDb.noteDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotesResult() {
        notes = mDb.noteDao().getAllNotes();
        return notes;
    }

    public void addNote(String title, String discription, Date date) {
        DatabaseInitializer.addNotes(mDb, title, discription,date);
    }

    public void deleteTableNote(){
        DatabaseInitializer.deleteNoteTable(mDb);
    }

    public void deleteSelectedRowFromNote(Note note){
        DatabaseInitializer.deleteSelectedRowFromTable(mDb, note);
    }

    public void createDb() {
        mDb = AppDatabase.getInMemoryDatabase(this.getApplication());
    }

    public List<Note> getDefaultNotes() {
        return noteList = mDb.noteDao().getAllDefaultNotes();
    }
}
