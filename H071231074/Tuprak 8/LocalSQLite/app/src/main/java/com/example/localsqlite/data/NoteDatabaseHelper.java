package com.example.localsqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDatabaseHelper extends SQLiteOpenHelper { // berfungsi untuk mengelola database SQLite
    public static final String DATABASE_NAME = "NoteDatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTES =
            "CREATE TABLE " + NoteContract.TABLE_NAME + " (" +
                    NoteContract.NoteColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NoteContract.NoteColumns.TITLE + " TEXT NOT NULL, " +
                    NoteContract.NoteColumns.DESCRIPTION + " TEXT NOT NULL, " +
                    NoteContract.NoteColumns.CREATED_AT + " TEXT NOT NULL, " +
                    NoteContract.NoteColumns.UPDATED_AT + " TEXT)";

    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NoteContract.TABLE_NAME);
        onCreate(db);
    }
}
