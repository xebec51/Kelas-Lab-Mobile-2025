package com.example.localsqlite.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localsqlite.R;
import com.example.localsqlite.adapter.NoteAdapter;
import com.example.localsqlite.data.Note;
import com.example.localsqlite.data.NoteHelper;
import com.example.localsqlite.data.NoteContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;
    private NoteAdapter adapter;
    private NoteHelper noteHelper;
    private TextView tvNoData;
    private EditText etSearch;

    public static final int REQUEST_ADD = 100;
    public static final int REQUEST_UPDATE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Notes");

        rvNotes = findViewById(R.id.rv_notes);
        tvNoData = findViewById(R.id.tv_no_data);
        etSearch = findViewById(R.id.et_search);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        noteHelper = new NoteHelper(this);
        noteHelper.open();

        adapter = new NoteAdapter(this);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        etSearch.addTextChangedListener(new TextWatcher() { // Listener untuk menangani perubahan teks pada serach bar
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchNotes(s.toString().trim());
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        loadAllNotes();
    }

    private void loadAllNotes() {
        Cursor cursor = noteHelper.queryAll();
        updateAdapterWithCursor(cursor);
    }

    private void searchNotes(String keyword) {
        Cursor cursor;
        if (keyword.isEmpty()) {
            cursor = noteHelper.queryAll();
        } else {
            cursor = noteHelper.searchByTitle(keyword);
        }
        updateAdapterWithCursor(cursor);
    }

    private void updateAdapterWithCursor(Cursor cursor) {
        ArrayList<Note> notes = new ArrayList<>();
        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(NoteContract.NoteColumns._ID)));
            note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NoteColumns.TITLE)));
            note.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NoteColumns.DESCRIPTION)));
            note.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NoteColumns.CREATED_AT)));
            note.setUpdatedAt(cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NoteColumns.UPDATED_AT)));
            notes.add(note);
        }

        if (notes.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
        } else {
            tvNoData.setVisibility(View.GONE);
        }

        adapter.setListNotes(notes);
        cursor.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD && resultCode == FormActivity.RESULT_ADD) {
            loadAllNotes();
        } else if (requestCode == REQUEST_UPDATE) {
            if (resultCode == FormActivity.RESULT_UPDATE || resultCode == FormActivity.RESULT_DELETE) {
                loadAllNotes();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteHelper.close();
    }
}
