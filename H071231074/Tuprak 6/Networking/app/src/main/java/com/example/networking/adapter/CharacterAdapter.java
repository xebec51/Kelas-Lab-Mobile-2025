package com.example.networking.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.DetailActivity;
import com.example.networking.R;
import com.example.networking.model.Character;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private final List<Character> characterList = new ArrayList<>(); // list untuk simpan data karakter

    public void setCharacters(List<Character> characters) { // method untuk set data karakter
        characterList.clear();
        characterList.addAll(characters);
        notifyDataSetChanged();
    }

    public void addCharacters(List<Character> characters) { // method untuk menambah data karakter
        int startPosition = characterList.size();
        characterList.addAll(characters);
        notifyItemRangeInserted(startPosition, characters.size());
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characterList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewAvatar;
        private final TextView textViewName;
        private final TextView textViewStatus;
        private final TextView textViewSpecies;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewSpecies = itemView.findViewById(R.id.textViewSpecies);
        }

        public void bind(Character character) { // method untuk bind data karakter ke view
            textViewName.setText(character.getName());
            textViewStatus.setText(character.getStatus());
            textViewSpecies.setText(character.getSpecies());

            Picasso.get()
                    .load(character.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageViewAvatar);

            itemView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                if (character != null) {
                    intent.putExtra("character", character);
                    context.startActivity(intent);
                } else {
                    Log.e("CharacterAdapter", "Character is null");
                }
            });
        }
    }
}
