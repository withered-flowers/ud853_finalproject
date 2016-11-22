package com.example.android.ud853.finalproject.app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterfaces {
    @GET("movies/{sortBy}")
    Call<MovieData> getMoviesBySort(@Path ("sortBy") String sortBy, @Query ("api_key") String apiKey);

    @GET("movies/{id}")
    Call<MovieData> getMoviesById(@Path ("id") int id, @Query ("api_key") String apiKey);
}