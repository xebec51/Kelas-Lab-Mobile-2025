package com.example.duolingoui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class EditProfileActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;

    private ImageView ivToggleNow, ivToggleNew;
    private ShapeableImageView imgAvatar;

    private EditText etNama, etUsername, etEmail, etPasswordNow, etPasswordNew;
    private Button btnSimpan;
    private TextView tvGantiAvatar;

    private boolean isPasswordNowVisible = false;
    private boolean isPasswordNewVisible = false;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inisialisasi View
        imgAvatar = findViewById(R.id.imgAvatar);
        etNama = findViewById(R.id.etNama);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPasswordNow = findViewById(R.id.etPasswordNow);
        etPasswordNew = findViewById(R.id.etPasswordNew);
        btnSimpan = findViewById(R.id.btnSimpan);
        tvGantiAvatar = findViewById(R.id.tvGantiAvatar);
        ivToggleNow = findViewById(R.id.ivToggleNow);
        ivToggleNew = findViewById(R.id.ivToggleNew);

        // Tampilkan avatar dari MainActivity (jika ada)
        String avatarFromMain = getIntent().getStringExtra("avatar");
        if (avatarFromMain != null) {
            imageUri = Uri.parse(avatarFromMain);
            imgAvatar.setImageURI(imageUri);
        }

        // Ganti avatar
        imgAvatar.setOnClickListener(v -> openGallery());
        tvGantiAvatar.setOnClickListener(v -> openGallery());

        // Toggle password
        ivToggleNow.setOnClickListener(v -> togglePassword(etPasswordNow, ivToggleNow, true));
        ivToggleNew.setOnClickListener(v -> togglePassword(etPasswordNew, ivToggleNew, false));

        // Simpan data
        btnSimpan.setOnClickListener(v -> saveData());
    }

    private void saveData() {
        String nama = etNama.getText().toString();
        String username = etUsername.getText().toString();
        String email = etEmail.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("nama", nama);
        resultIntent.putExtra("username", username);
        resultIntent.putExtra("email", email);
        if (imageUri != null) {
            resultIntent.putExtra("avatar", imageUri.toString());
        }
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void togglePassword(EditText editText, ImageView toggleIcon, boolean isNow) {
        boolean isVisible = isNow ? isPasswordNowVisible : isPasswordNewVisible;

        if (isVisible) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            toggleIcon.setImageResource(R.drawable.ic_eye);
        } else {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            toggleIcon.setImageResource(R.drawable.ic_eye);
        }

        if (isNow) {
            isPasswordNowVisible = !isPasswordNowVisible;
        } else {
            isPasswordNewVisible = !isPasswordNewVisible;
        }

        editText.setSelection(editText.getText().length());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();

            // Ambil akses jangka panjang untuk URI
            final int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            try {
                getContentResolver().takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } catch (SecurityException e) {
                e.printStackTrace();
            }

            imgAvatar.setImageURI(imageUri);
        }
    }
}
