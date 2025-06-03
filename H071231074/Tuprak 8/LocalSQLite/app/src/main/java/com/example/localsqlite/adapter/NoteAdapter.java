package com.example.localsqlite.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localsqlite.R;
import com.example.localsqlite.data.Note;
import com.example.localsqlite.ui.FormActivity;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> { // berfungsi sebagai adapter untuk RecyclerView yang menampilkan daftar catatan
    private final ArrayList<Note> listNotes = new ArrayList<>();
    private final Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListNotes(ArrayList<Note> notes) {
        listNotes.clear();
        listNotes.addAll(notes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view); // menginflate layout item_note.xml untuk setiap item dalam RecyclerView
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(listNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDesc, tvTimestamp;
        final CardView cardView;

        NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_description);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            cardView = itemView.findViewById(R.id.card_view);
        }

        void bind(final Note note) {
            tvTitle.setText(note.getTitle());
            tvDesc.setText(note.getDescription());
            if (note.getUpdatedAt() != null && !note.getUpdatedAt().isEmpty()) {
                tvTimestamp.setText("Updated at " + note.getUpdatedAt());
            } else {
                tvTimestamp.setText("Created at " + note.getCreatedAt());
            }

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, FormActivity.class);
                intent.putExtra(FormActivity.EXTRA_NOTE, note);
                activity.startActivityForResult(intent, FormActivity.REQUEST_UPDATE);
            });
        }
    }
}
