package com.example.networking.network;

import com.example.networking.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("character") // brfungsi untuk mendapatkan karakter
    Call<ApiResponse> getCharacters(@Query("page") int page); // method untuk mendapatkan karakter dgn parameter page
}
