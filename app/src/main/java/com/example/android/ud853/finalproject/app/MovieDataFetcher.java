package com.example.android.ud853.finalproject.app;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDataFetcher {
    Retrofit theFetcher;
    final String BASE_URL = "https://api.themoviedb.org/3/";

    public Retrofit getFetcher() {
        theFetcher = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return theFetcher;
    }
}
