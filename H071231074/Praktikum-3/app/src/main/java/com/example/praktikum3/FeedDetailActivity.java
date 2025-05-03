package com.example.praktikum3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeedDetailActivity extends AppCompatActivity { // Untuk menampilkan feed secara detail

    private RecyclerView recyclerView;
    private FeedDetailAdapter adapter;
    private List<Feed> feedList;
    private int startPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_detail);

        recyclerView = findViewById(R.id.recyclerViewFeedDetail);

        feedList = (List<Feed>) getIntent().getSerializableExtra("feedList");
        startPosition = getIntent().getIntExtra("startPosition", 0);

        adapter = new FeedDetailAdapter(this, feedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Scroll ke postingan yang diklik pertama
        recyclerView.scrollToPosition(startPosition);
    }
}
