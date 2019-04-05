package com.example.roompersistence;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roompersistence.db.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesActivity extends AppCompatActivity {

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;

    private NotesActivityViewModel mViewModel;
    NotesAdapter adapter;
    List<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel = ViewModelProviders.of(this).get(NotesActivityViewModel.class);
        mViewModel.createDb();

        // Log.i("NotesActivity", String.valueOf(mViewModel.getDefaultNotes().size()));


        mViewModel.getNotesResult().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                /*for (int i = 0; i < notes.size(); i++) {
                    Log.i("NotesActivity", notes.get(i).id + " | " + notes.get(i).title);
                }*/
                noteList = notes;
                adapter.notifyDataSetChanged();
                /*for (Note note : notes) {
                    Log.i("NotesActivity", note.id + " | " + note.title);
                }*/
            }
        });
        adapter = new NotesAdapter(NotesActivity.this, noteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerview.setLayoutManager(mLayoutManager);
        myRecyclerview.setItemAnimator(new DefaultItemAnimator());
        myRecyclerview.setAdapter(adapter);
    }


    @OnClick(R.id.fab)
    public void createReminder() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.reminder_alert, null);
        alertDialog.setView(dialogView);

        final EditText edtTitle = (EditText) dialogView.findViewById(R.id.edt_title);
        final EditText edtDiscription = (EditText) dialogView.findViewById(R.id.edt_description);
        Button submitButton = (Button) dialogView.findViewById(R.id.buttonSubmit);
        Button CancelButton = (Button) dialogView.findViewById(R.id.buttonCancel);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(NotesActivity.this, "Submit", Toast.LENGTH_SHORT).show();
                String title = edtTitle.getText().toString();
                String discription = edtDiscription.getText().toString();
                mViewModel.addNote(title, discription);
                alertDialog.dismiss();
            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //Toast.makeText(NotesActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }
}
