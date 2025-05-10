package com.example.libraryapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libraryapp.R;
import com.example.libraryapp.data.Book;
import com.example.libraryapp.data.BookDataSource;

public class AddBookFragment extends Fragment {

    private static final int PICK_IMAGE = 1; // kode request untuk pilhi gambar

    private ImageView imageCover;
    private EditText etTitle, etAuthor, etYear, etBlurb;
    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        imageCover = view.findViewById(R.id.image_cover);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etBlurb = view.findViewById(R.id.et_blurb);
        Button btnSave = view.findViewById(R.id.btn_save);

        imageCover.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE);
        });

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            String yearStr = etYear.getText().toString();
            String blurb = etBlurb.getText().toString();

            if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || blurb.isEmpty() || selectedImageUri == null) {
                Toast.makeText(getContext(), "Mohon lengkapi semua data", Toast.LENGTH_SHORT).show();
                return;
            }

            int year = Integer.parseInt(yearStr);
            Book newBook = new Book(title, author, year, blurb, selectedImageUri, false);
            BookDataSource.addBook(newBook);

            Toast.makeText(getContext(), "Buku ditambahkan", Toast.LENGTH_SHORT).show();

            etTitle.setText("");
            etAuthor.setText("");
            etYear.setText("");
            etBlurb.setText("");
            imageCover.setImageResource(R.drawable.placeholder);
            selectedImageUri = null;
        });

        return view;
    }

    @Override // onActivityResult untuk menangani hasil dari pemilihan gambar
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imageCover.setImageURI(selectedImageUri);
        }
    }
}
