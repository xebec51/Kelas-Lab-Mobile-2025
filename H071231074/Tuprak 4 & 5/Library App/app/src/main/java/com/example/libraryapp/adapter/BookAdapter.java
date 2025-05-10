// Path: adapter/BookAdapter.java
package com.example.libraryapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.data.Book;
import com.example.libraryapp.DetailActivity;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private final Context context;
    private List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // buat view holder
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override // isi view holder dengan data buku
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void filterList(List<Book> newBooks) {
        this.books.clear();
        this.books.addAll(newBooks);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitle, tvAuthor;
        private final ImageView ivCover;
        private Book currentBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text_title);
            tvAuthor = itemView.findViewById(R.id.text_author);
            ivCover = itemView.findViewById(R.id.image_cover);
            itemView.setOnClickListener(this);
        }

        public void bind(Book book) { // isi elemen ui dengan buku
            currentBook = book;
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());

            Uri uri = book.getCoverUri();
            if (uri != null) {
                ivCover.setImageURI(uri);
            } else {
                ivCover.setImageResource(R.drawable.placeholder);
            }
        }

        @Override
        public void onClick(View v) { // buka detail buku
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("BOOK", currentBook);
            context.startActivity(intent);
        }
    }
}
