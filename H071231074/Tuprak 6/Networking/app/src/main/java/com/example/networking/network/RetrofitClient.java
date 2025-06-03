package com.example.networking.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient { // brfungsi untuk menghubungkan ke api
    private static final String BASE_URL = "https://rickandmortyapi.com/api/";
    private static Retrofit retrofit;

    public static ApiService getInstance() { // berfungsi untuk mendapatkan instance dari retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build(); // brfungsi untuk mengkonfigurasi retrofit
        }
        return retrofit.create(ApiService.class);
    }
}
