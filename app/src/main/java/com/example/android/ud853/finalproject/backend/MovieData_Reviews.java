package com.example.android.ud853.finalproject.backend;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieData_Reviews {
    @SerializedName("id")
    private Integer movieId;

    @SerializedName("page")
    private Integer reviewCurrentPage;

    @SerializedName("results")
    private List<MovieObject_Review> reviewResults;

    @SerializedName("total_pages")
    private Integer reviewTotalPages;

    @SerializedName("total_results")
    private Integer reviewTotalResults;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getReviewCurrentPage() {
        return reviewCurrentPage;
    }

    public void setReviewCurrentPage(Integer reviewCurrentPage) {
        this.reviewCurrentPage = reviewCurrentPage;
    }

    public List<MovieObject_Review> getReviewResults() {
        return reviewResults;
    }

    public void setReviewResults(List<MovieObject_Review> reviewResults) {
        this.reviewResults = reviewResults;
    }

    public Integer getReviewTotalPages() {
        return reviewTotalPages;
    }

    public void setReviewTotalPages(Integer reviewTotalPages) {
        this.reviewTotalPages = reviewTotalPages;
    }

    public Integer getReviewTotalResults() {
        return reviewTotalResults;
    }

    public void setReviewTotalResults(Integer reviewTotalResults) {
        this.reviewTotalResults = reviewTotalResults;
    }
}
