package com.example.libraryapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.adapter.BookAdapter;
import com.example.libraryapp.data.Book;
import com.example.libraryapp.data.BookDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private static final String TAG = "FavoritesFragment"; // untuk debugging

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView emptyPlaceholder; // Declare the empty placeholder TextView
    private BookAdapter adapter;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        emptyPlaceholder = view.findViewById(R.id.empty_placeholder); // Initialize the empty placeholder

        adapter = new BookAdapter(getContext(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        progressBar.setVisibility(View.VISIBLE); // tampilkan progress bar

        executor.execute(() -> { // jalankan di thread terpisah
            List<Book> likedBooks = new ArrayList<>();
            List<Book> allBooks = BookDataSource.getAllBooks();

            if (allBooks == null) { // jika data buku tidak ada
                Log.e(TAG, "BookDataSource.getAllBooks() returned null");
            } else {
                for (Book book : allBooks) { // iterasi semua buku
                    if (book.isLiked()) {
                        likedBooks.add(book);
                    }
                }
            }

            mainHandler.post(() -> { // kembali ke thread utama
                if (adapter != null) {
                    adapter.filterList(likedBooks);
                } else {
                    Log.e(TAG, "Adapter is null");
                }

                // Show or hide the empty placeholder based on the list size
                if (likedBooks.isEmpty()) {
                    emptyPlaceholder.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    emptyPlaceholder.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }

                Context context = getContext();
                if (context != null) {
                    recyclerView.setLayoutAnimation(
                            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fade_in)
                    );
                    recyclerView.scheduleLayoutAnimation();
                }

                progressBar.setVisibility(View.GONE);
            });
        });
    }
}
