package com.example.android.ud853.finalproject.backend;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterfaces {
    @GET("movie/{sortBy}")
    Call<MovieData> getMoviesBySort(@Path ("sortBy") String sortBy, @Query ("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieObject> getMoviesById(@Path ("id") int id, @Query ("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<MovieData_Trailers> getTrailerByMovieId(@Path ("id") int id, @Query ("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<MovieData_Reviews> getReviewByMovieId(@Path ("id") int id, @Query ("api_key") String apiKey);
}