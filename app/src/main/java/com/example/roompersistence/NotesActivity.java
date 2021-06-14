package com.example.roompersistence;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roompersistence.db.Note;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotesActivity extends AppCompatActivity {

    @BindView(R.id.my_recyclerview)
    RecyclerView myRecyclerview;
    @BindView(R.id.container)
    ConstraintLayout coordinatorLayout;

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
        generateSnackBar();
        //Use this using with Live data
        mViewModel.getNotesResult().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                for (int i = 0; i < notes.size(); i++) {
                    Log.i("NotesActivity", notes.get(i).id + " | " + notes.get(i).title);
                }
                noteList = notes;
                //adapter.notifyDataSetChanged();
                for (Note note : notes) {
                    Log.i("NotesActivity", note.id + " | " + note.title);
                }
                setRecyclerview(notes);

            }
        });

       /* //Use this as List for without Live data for difference
        noteList = mViewModel.getDefaultNotes();
        setRecyclerview(noteList);*/
    }

    public void setRecyclerview(List<Note> list) {
        adapter = new NotesAdapter(NotesActivity.this, list);
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
                Date date = Calendar.getInstance().getTime();
                mViewModel.addNote(title, discription, date);
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

    public void generateSnackBar() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Delete All Records", Snackbar.LENGTH_LONG)
                .setAction("DELETE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();*/
                        mViewModel.deleteTableNote();
                        // mViewModel.deleteSelectedRowFromNote(noteList.get(0));
                    }
                });

        snackbar.show();
    }
}
