package com.example.networking;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.adapter.CharacterAdapter;
import com.example.networking.model.ApiResponse;
import com.example.networking.model.Character;
import com.example.networking.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private Button buttonLoadMore;
    private int currentPage = 1;
    private int totalPages = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewCharacters);
        buttonLoadMore = findViewById(R.id.buttonLoadMore);

        adapter = new CharacterAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadCharacters(currentPage);

        buttonLoadMore.setOnClickListener(v -> {
            if (currentPage < totalPages) {
                currentPage++;
                loadCharacters(currentPage);
            } else {
                Toast.makeText(MainActivity.this, "No more characters to load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCharacters(int page) {
        RetrofitClient.getInstance().getCharacters(page).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<Character> characters = apiResponse.getResults();
                    if (page == 1) {
                        adapter.setCharacters(characters);
                    } else {
                        adapter.addCharacters(characters);
                    }
                    totalPages = apiResponse.getInfo().getPages();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
