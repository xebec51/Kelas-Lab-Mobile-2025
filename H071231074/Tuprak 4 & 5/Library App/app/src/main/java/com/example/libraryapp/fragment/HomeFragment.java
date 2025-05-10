package com.example.libraryapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.SearchView;

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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private List<Book> bookList;
    private List<Book> originalBookList; // Tambahkan variabel untuk menyimpan daftar buku asli
    private SearchView searchView;
    private ProgressBar progressBar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor(); // untuk menjalankan tugas di thread terpisah
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        searchView = view.findViewById(R.id.search_view);
        progressBar = view.findViewById(R.id.progress_bar);

        originalBookList = BookDataSource.getAllBooks(); // Simpan daftar buku asli
        bookList = new ArrayList<>(originalBookList); // Salin daftar buku asli ke daftar yang dapat dimodifikasi
        adapter = new BookAdapter(getContext(), bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    resetBookList(); // Kembalikan daftar buku asli
                } else {
                    filter(newText);
                }
                return true;
            }
        });

        return view;
    }

    private void filter(String query) {
        progressBar.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            List<Book> filteredList = new ArrayList<>();
            for (Book book : originalBookList) { //
                if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(book);
                }
            }

            mainHandler.post(() -> {
                adapter.filterList(filteredList);

                recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fade_in));
                recyclerView.getAdapter().notifyDataSetChanged();
                recyclerView.scheduleLayoutAnimation();

                progressBar.setVisibility(View.GONE);
            });
        });
    }

    private void resetBookList() {
        progressBar.setVisibility(View.VISIBLE);

        mainHandler.post(() -> {
            adapter.filterList(originalBookList); // Kembalikan daftar buku asli ke adapter
            recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fade_in));
            recyclerView.getAdapter().notifyDataSetChanged();
            recyclerView.scheduleLayoutAnimation();

            progressBar.setVisibility(View.GONE);
        });
    }
}
