package com.example.localsqlite.data;

import android.provider.BaseColumns;

public class NoteContract {
    public static final String TABLE_NAME = "notes";

    public static final class NoteColumns implements BaseColumns {
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
    }
}
