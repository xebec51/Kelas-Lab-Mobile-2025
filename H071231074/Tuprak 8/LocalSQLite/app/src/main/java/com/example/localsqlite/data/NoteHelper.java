package com.example.localsqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteHelper { // berfungsi untuk mengelola operasi database SQLite pada tabel
    private static final String TABLE_NAME = NoteContract.TABLE_NAME;
    private final NoteDatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public NoteHelper(Context context) {
        dbHelper = new NoteDatabaseHelper(context);
    } // konstruktor untuk menginisialisasi NoteDatabaseHelper

    public void open() throws SQLException { // membuka koneksi ke database
        database = dbHelper.getWritableDatabase();
    }

    public void close() { // menutup koneksi ke database
        dbHelper.close();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() { // mengambil semua data dari tabel
        return database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                NoteContract.NoteColumns._ID + " DESC"
        );
    }

    public Cursor searchByTitle(String keyword) { // mencari data berdasarkan judul
        return database.query(
                TABLE_NAME,
                null,
                NoteContract.NoteColumns.TITLE + " LIKE ?",
                new String[]{"%" + keyword + "%"},
                null,
                null,
                NoteContract.NoteColumns._ID + " DESC"
        );
    }

    public long insert(ContentValues values) {
        return database.insert(TABLE_NAME, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(TABLE_NAME, values, NoteContract.NoteColumns._ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(TABLE_NAME, NoteContract.NoteColumns._ID + " = ?", new String[]{id});
    }
}
