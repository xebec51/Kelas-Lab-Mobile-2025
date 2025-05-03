package com.example.praktikum3;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class HighlightDetailActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private ImageView highlightImageView;
    private LinearLayout progressContainer;
    private List<ProgressBar> progressBars = new ArrayList<>();
    private List<Integer> highlightImages = new ArrayList<>();
    private int currentIndex = 0;

    private Handler handler;
    private Runnable runnable;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_highlight_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi view
        frameLayout = findViewById(R.id.main);
        highlightImageView = findViewById(R.id.highlightImageView);
        progressContainer = findViewById(R.id.progressContainer);

        // Ambil gambar dari Intent
        ArrayList<Integer> receivedImages = getIntent().getIntegerArrayListExtra("highlight_images");
        if (receivedImages != null && !receivedImages.isEmpty()) {
            highlightImages.addAll(receivedImages);
        } else {
            int imageResId = getIntent().getIntExtra("highlight_image", R.drawable.ic_launcher_background);
            highlightImages.add(imageResId);
        }

        setupProgressBars();
        showImage(currentIndex);

        // 1. Buat dulu gestureDetector
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float x = e.getX();
                float width = frameLayout.getWidth();

                if (x < width / 2) {
                    goToPreviousImage();
                } else {
                    goToNextImage();
                }
                return true;
            }
        });


// 2. Baru pasang frameLayout.setOnTouchListener
        frameLayout.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // Ketika jari ditekan ➔ pause progress
                    pauseProgress();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    // Ketika jari dilepas ➔ resume progress
                    resumeProgress();
                    v.performClick();
                    break;
            }

            gestureDetector.onTouchEvent(event);
            return true;
        });

        // Pasang listener ke seluruh frame layout
    }

    private void setupProgressBars() {
        progressContainer.removeAllViews();
        progressBars.clear();

        for (int i = 0; i < highlightImages.size(); i++) {
            ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            pb.setLayoutParams(new LinearLayout.LayoutParams(0, 5, 1));
            pb.setMax(100);
            pb.setProgress(0);
            pb.setProgressTintList(getResources().getColorStateList(android.R.color.white));

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) pb.getLayoutParams();
            params.setMargins(4, 0, 4, 0);
            pb.setLayoutParams(params);

            progressContainer.addView(pb);
            progressBars.add(pb);
        }
    }

    private void showImage(int index) {
        highlightImageView.setImageResource(highlightImages.get(index));
        simulateProgress();
    }

    private void simulateProgress() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }

        handler = new Handler();
        progressBars.get(currentIndex).setProgress(0);

        final int[] progress = {0};

        runnable = new Runnable() {
            @Override
            public void run() {
                if (progress[0] < 100) {
                    progress[0]++;
                    progressBars.get(currentIndex).setProgress(progress[0]);
                    handler.postDelayed(this, 20);
                } else {
                    progressBars.get(currentIndex).setProgress(100);
                    if (currentIndex < highlightImages.size() - 1) {
                        currentIndex++;
                        showImage(currentIndex);
                    } else {
                        finish();
                        overridePendingTransition(0, R.anim.fade_out);
                    }
                }
            }
        };

        handler.postDelayed(runnable, 20);
    }

    private void goToNextImage() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }

        if (currentIndex < highlightImages.size() - 1) {
            currentIndex++;
            showImage(currentIndex);
        } else {
            finish();
            overridePendingTransition(0, R.anim.fade_out);
        }
    }

    private void goToPreviousImage() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }

        if (currentIndex > 0) {
            currentIndex--;
            showImage(currentIndex);
        }
    }
    private void pauseProgress() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    private void resumeProgress() {
        if (handler != null && runnable != null) {
            handler.postDelayed(runnable, 20);
        }
    }


}
