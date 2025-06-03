package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sharedpreferences.R;

public class RegisterActivity extends AppCompatActivity {

    EditText etNim, etNama, etProdi, etFakultas, etSemester, etPassword; // Added etPassword
    Button btnRegister;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mahasiswa_pref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_PRODI = "prodi";
    private static final String KEY_FAKULTAS = "fakultas";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_PASSWORD = "password"; // Added KEY_PASSWORD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etFakultas = findViewById(R.id.etFakultas);
        etSemester = findViewById(R.id.etSemester);
        etPassword = findViewById(R.id.etPassword); // Initialize etPassword
        btnRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> {
            String nim = etNim.getText().toString().trim();
            String nama = etNama.getText().toString().trim();
            String prodi = etProdi.getText().toString().trim();
            String fakultas = etFakultas.getText().toString().trim();
            String semester = etSemester.getText().toString().trim();
            String password = etPassword.getText().toString().trim(); // Get password input

            if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || fakultas.isEmpty() || semester.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NIM, nim);
            editor.putString(KEY_NAMA, nama);
            editor.putString(KEY_PRODI, prodi);
            editor.putString(KEY_FAKULTAS, fakultas);
            editor.putString(KEY_SEMESTER, semester);
            editor.putString(KEY_PASSWORD, password); // Save password
            editor.apply();

            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
