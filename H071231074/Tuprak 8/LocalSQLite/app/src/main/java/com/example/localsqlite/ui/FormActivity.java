package com.example.localsqlite.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.localsqlite.R;
import com.example.localsqlite.data.Note;
import com.example.localsqlite.data.NoteContract;
import com.example.localsqlite.data.NoteHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_NOTE = "extra_note"; // key untuk mengirim data Note antar Activity
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    public static final int REQUEST_UPDATE = 200;

    private EditText etTitle, etDescription;
    private Button btnSave, btnDelete;

    private NoteHelper noteHelper;
    private Note note;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteHelper = new NoteHelper(this); //
        noteHelper.open();

        note = getIntent().getParcelableExtra(EXTRA_NOTE);
        if (note != null) {
            isEdit = true;
            etTitle.setText(note.getTitle());
            etDescription.setText(note.getDescription());
            btnDelete.setVisibility(View.VISIBLE);
            btnSave.setText(R.string.update);
            getSupportActionBar().setTitle("Edit Note");
        } else {
            note = new Note();
            getSupportActionBar().setTitle("Add Note");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave.setOnClickListener(v -> saveNote());
        btnDelete.setOnClickListener(v -> deleteNote());
    }

    private void saveNote() {
        String title = etTitle.getText().toString().trim();
        String desc = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Please enter title");
            return;
        }

        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        ContentValues values = new ContentValues();
        values.put(NoteContract.NoteColumns.TITLE, title);
        values.put(NoteContract.NoteColumns.DESCRIPTION, desc);
        note.setTitle(title);
        note.setDescription(desc);

        Intent intent = new Intent();
        intent.putExtra(EXTRA_NOTE, note);

        if (isEdit) {
            values.put(NoteContract.NoteColumns.UPDATED_AT, currentTime);
            long result = noteHelper.update(String.valueOf(note.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                showToast("Failed to update note");
            }
        } else {
            values.put(NoteContract.NoteColumns.CREATED_AT, currentTime);
            long result = noteHelper.insert(values);
            if (result > 0) {
                note.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                showToast("Failed to add note");
            }
        }
    }

    private void deleteNote() {
        if (note.getId() > 0) {
            long result = noteHelper.deleteById(String.valueOf(note.getId()));
            if (result > 0) {
                setResult(RESULT_DELETE);
                finish();
            } else {
                showToast("Failed to delete note");
            }
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() { // kasi tutup koneksi database ketika activity dihancurkan
        super.onDestroy();
        noteHelper.close();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // menangani aksi ketika item menu diklik
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
