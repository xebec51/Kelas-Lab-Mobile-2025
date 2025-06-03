package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    Button btnToLogin, btnToRegister;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mahasiswa_pref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String savedNim = sharedPreferences.getString(KEY_NIM, null);
        String savedNama = sharedPreferences.getString(KEY_NAMA, null);

        if (savedNim != null && savedNama != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_first);

        btnToLogin = findViewById(R.id.btnToLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        btnToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        btnToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
