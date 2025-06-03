package com.example.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.networking.model.Character;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView name, status, species, gender, origin, location, textViewError;
    private LinearLayout detailLayout;
    private Button buttonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        imageView = findViewById(R.id.imageViewDetail);
        name = findViewById(R.id.textViewName);
        status = findViewById(R.id.textViewStatus);
        species = findViewById(R.id.textViewSpecies);
        gender = findViewById(R.id.textViewGender);
        origin = findViewById(R.id.textViewOrigin);
        location = findViewById(R.id.textViewLocation);
        textViewError = findViewById(R.id.textViewError);
        detailLayout = findViewById(R.id.boxdetail);
        buttonRefresh = findViewById(R.id.buttonRefresh);


        if (!isConnected()) { // periksa koneksi internet
            textViewError.setVisibility(View.VISIBLE); // tampilkan pesan error
            buttonRefresh.setVisibility(View.VISIBLE);
            hideDetailViews(); // sembunyikan detail karakter
            return;
        } else {
            loadCharacterDetails();
        }

        buttonRefresh.setOnClickListener(v -> {
            if (isConnected()) {
                textViewError.setVisibility(View.GONE); // sembunyikan pesan error
                buttonRefresh.setVisibility(View.GONE); // sembunyikan tombol refresh
                loadCharacterDetails(); // muat ulang detail karakter
            } else {
                Toast.makeText(this, "Tidak ada koneksi internet!", Toast.LENGTH_SHORT).show();
            }
            Log.d("DetailActivity", "Button Refresh clicked");
        });

        Character character = (Character) getIntent().getSerializableExtra("character");

        if (character != null) {
            name.setText(character.getName() != null ? character.getName() : "-");
            status.setText(character.getStatus() != null ? character.getStatus() : "-");
            species.setText(character.getSpecies() != null ? character.getSpecies() : "-");
            gender.setText(character.getGender() != null ? character.getGender() : "-");
            origin.setText(character.getOrigin() != null ? character.getOrigin().getName() : "Unknown");
            location.setText(character.getLocation() != null ? character.getLocation().getName() : "Unknown");

            if (character.getImage() != null && !character.getImage().isEmpty()) {
                Picasso.get()
                        .load(character.getImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            Log.e("DetailActivity", "Character data is null!");
            textViewError.setVisibility(View.VISIBLE); // Tampilkan pesan error jika data null
            hideDetailViews(); // Sembunyikan detail karakter
        }
    }

    private boolean isConnected() { // method untuk memeriksa koneksi internet
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void hideDetailViews() { // method untuk menyembunyikan detail karakter
        imageView.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        status.setVisibility(View.GONE);
        species.setVisibility(View.GONE);
        gender.setVisibility(View.GONE);
        origin.setVisibility(View.GONE);
        location.setVisibility(View.GONE);
        detailLayout.setVisibility(View.GONE); // Sembunyikan layout detail
    }

    private void loadCharacterDetails() {
        Character character = (Character) getIntent().getSerializableExtra("character");

        if (character != null) {
            name.setText(character.getName() != null ? character.getName() : "-");
            status.setText(character.getStatus() != null ? character.getStatus() : "-");
            species.setText(character.getSpecies() != null ? character.getSpecies() : "-");
            gender.setText(character.getGender() != null ? character.getGender() : "-");
            origin.setText(character.getOrigin() != null ? character.getOrigin().getName() : "Unknown");
            location.setText(character.getLocation() != null ? character.getLocation().getName() : "Unknown");

            if (character.getImage() != null && !character.getImage().isEmpty()) {
                Picasso.get()
                        .load(character.getImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(imageView);
            } else {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        } else {
            Log.e("DetailActivity", "Character data is null!");
            textViewError.setVisibility(View.VISIBLE); // Tampilkan pesan error jika data null
            hideDetailViews(); // Sembunyikan detail karakter
        }
    }
}