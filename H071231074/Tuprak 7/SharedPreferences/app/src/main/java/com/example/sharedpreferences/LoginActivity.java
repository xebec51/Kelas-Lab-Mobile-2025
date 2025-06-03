package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etNim, etNama, etPassword;
    Button btnLogin;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mahasiswa_pref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {
            String inputNim = etNim.getText().toString().trim();
            String inputNama = etNama.getText().toString().trim();
            String inputPassword = etPassword.getText().toString().trim(); // Get password input

            String savedNim = sharedPreferences.getString(KEY_NIM, "");
            String savedNama = sharedPreferences.getString(KEY_NAMA, "");
            String savedPassword = sharedPreferences.getString(KEY_PASSWORD, ""); // Get saved password

            if (inputNim.equals(savedNim) && inputNama.equalsIgnoreCase(savedNama) && inputPassword.equals(savedPassword)) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "NIM, Nama, atau Password salah!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
