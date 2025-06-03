package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvData;
    Button btnLogout;
    Switch switchTheme;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mahasiswa_pref";
    private static final String KEY_NIM = "nim";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_PRODI = "prodi";
    private static final String KEY_FAKULTAS = "fakultas";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_THEME = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);
        btnLogout = findViewById(R.id.btnLogout);
        switchTheme = findViewById(R.id.switchTheme);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String nim = sharedPreferences.getString(KEY_NIM, "-");
        String nama = sharedPreferences.getString(KEY_NAMA, "-");
        String prodi = sharedPreferences.getString(KEY_PRODI, "-");
        String fakultas = sharedPreferences.getString(KEY_FAKULTAS, "-");
        String semester = sharedPreferences.getString(KEY_SEMESTER, "-");
        boolean isDark = sharedPreferences.getBoolean(KEY_THEME, false);

        switchTheme.setChecked(isDark);
        applyTheme(isDark);

        String info = "NIM: " + nim +
                "\nNama: " + nama +
                "\nProgram Studi: " + prodi +
                "\nFakultas: " + fakultas +
                "\nSemester: " + semester;

        tvData.setText(info);

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
            finish();
        });

        switchTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(KEY_THEME, isChecked);
            editor.apply();
            applyTheme(isChecked);
        });
    }

    private void applyTheme(boolean isDark) {
        if (isDark) {
            findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            tvData.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(android.R.color.white));
            tvData.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}
