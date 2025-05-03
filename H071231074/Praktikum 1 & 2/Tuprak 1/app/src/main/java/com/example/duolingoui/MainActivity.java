package com.example.duolingoui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imgAvatar;
    private TextView tvNama, tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inisialisasi View
        imgAvatar = findViewById(R.id.profile_avatar);
        tvNama = findViewById(R.id.profile_name);
        tvUsername = findViewById(R.id.profile_username);

        // Klik gambar Avatar untuk edit profil
        imgAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);

            Object tag = imgAvatar.getTag();
            if (tag instanceof String) {
                intent.putExtra("avatar", (String) tag);
            }

            startActivityForResult(intent, 100);
        });

        // Handle padding atas-bawah (opsional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            String nama = data.getStringExtra("nama");
            String username = data.getStringExtra("username");
            String avatar = data.getStringExtra("avatar");

            if (nama != null) {
                tvNama.setText(nama);
            }
            if (username != null) {
                tvUsername.setText(username);
            }
            if (avatar != null) {
                Uri avatarUri = Uri.parse(avatar);
                imgAvatar.setImageURI(avatarUri);
                imgAvatar.setTag(avatar); // Simpan URI sebagai tag untuk dikirim ulang
            }
        }
    }
}
