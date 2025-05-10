package com.example.libraryapp;

import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.libraryapp.data.Book;
import com.example.libraryapp.data.BookDataSource;

public class DetailActivity extends AppCompatActivity {

    private Book book;
    private TextView tvTitle, tvAuthor, tvYear, tvBlurb;
    private ImageView ivCover;
    private ImageButton btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tambahkan listener untuk tombol back
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tvTitle = findViewById(R.id.text_title);
        tvAuthor = findViewById(R.id.text_author);
        tvYear = findViewById(R.id.text_year);
        tvBlurb = findViewById(R.id.text_blurb);
        ivCover = findViewById(R.id.image_cover);
        btnLike = findViewById(R.id.button_like);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("BOOK")) {
            book = intent.getParcelableExtra("BOOK");

            // sinkronkan status Like dengan data global
            for (Book b : BookDataSource.getAllBooks()) {
                if (b.getTitle().equals(book.getTitle())
                        && b.getAuthor().equals(book.getAuthor())) {
                    book.setLiked(b.isLiked());
                    break;
                }
            }

            if (book != null) { // jika buku tidak null
                tvTitle.setText(book.getTitle());
                tvAuthor.setText(book.getAuthor());
                tvYear.setText(String.valueOf(book.getYear()));
                tvBlurb.setText(book.getBlurb());
                ivCover.setImageURI(book.getCoverUri());

                updateLikeButton();

                btnLike.setOnClickListener(v -> {
                    book.setLiked(!book.isLiked());
                    BookDataSource.updateBookLiked(book, book.isLiked());
                    updateLikeButton();

                    // Animasi zoom in dan zoom out
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(btnLike, "scaleX", 1f, 1.2f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(btnLike, "scaleY", 1f, 1.2f, 1f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(scaleX, scaleY);
                    animatorSet.setDuration(300);
                    animatorSet.start();
                });

                ivCover.setOnClickListener(v -> { //
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(ivCover, "rotation", 0f, 360f);
                    rotation.setDuration(1000);
                    rotation.start();
                });
            }
        }
    }

    private void updateLikeButton() {
        btnLike.setImageResource(book.isLiked() ? R.drawable.like_icon : R.drawable.unlike_icon);
    }
}
