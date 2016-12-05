package com.example.android.ud853.finalproject.backend;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieData_Trailers {
    @SerializedName("id")
    private Integer movieId;

    @SerializedName("results")
    private List<MovieObject_Trailer> trailerResults;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public List<MovieObject_Trailer> getTrailerResults() {
        return trailerResults;
    }

    public void setTrailerResults(List<MovieObject_Trailer> trailerResults) {
        this.trailerResults = trailerResults;
    }
}
