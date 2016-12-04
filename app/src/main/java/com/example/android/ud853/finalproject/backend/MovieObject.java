package com.example.android.ud853.finalproject.backend;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MovieObject implements Parcelable {
    @SerializedName("poster_path")
    private String moviePosterPath;
    
    @SerializedName("adult")
    private boolean movieIsAdult;
    
    @SerializedName("overview")
    private String movieOverview;

    @SerializedName("release_date")
    private String movieReleaseDate;

    @SerializedName("genre_ids")
    private List<Integer> movieGenreIds;

    @SerializedName("id")
    private Integer movieId;

    @SerializedName("original_title")
    private String movieOriginalTitle;

    @SerializedName("original_language")
    private String movieOriginalLanguage;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("backdrop_path")
    private String movieBackdropPath;

    @SerializedName("popularity")
    private Double moviePopularity;

    @SerializedName("vote_count")
    private Integer movieVoteCount;

    @SerializedName("video")
    private boolean movieIsVideo;

    @SerializedName("vote_average")
    private Double movieVoteAverage;
    
    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public boolean isMovieIsAdult() {
        return movieIsAdult;
    }

    public void setMovieIsAdult(boolean movieIsAdult) {
        this.movieIsAdult = movieIsAdult;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public List<Integer> getMovieGenreIds() {
        return movieGenreIds;
    }

    public void setMovieGenreIds(List<Integer> movieGenreIds) {
        this.movieGenreIds = movieGenreIds;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieOriginalTitle() {
        return movieOriginalTitle;
    }

    public void setMovieOriginalTitle(String movieOriginalTitle) {
        this.movieOriginalTitle = movieOriginalTitle;
    }

    public String getMovieOriginalLanguage() {
        return movieOriginalLanguage;
    }

    public void setMovieOriginalLanguage(String movieOriginalLanguage) {
        this.movieOriginalLanguage = movieOriginalLanguage;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieBackdropPath() {
        return movieBackdropPath;
    }

    public void setMovieBackdropPath(String movieBackdropPath) {
        this.movieBackdropPath = movieBackdropPath;
    }

    public Double getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(Double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public Integer getMovieVoteCount() {
        return movieVoteCount;
    }

    public void setMovieVoteCount(Integer movieVoteCount) {
        this.movieVoteCount = movieVoteCount;
    }

    public boolean isMovieIsVideo() {
        return movieIsVideo;
    }

    public void setMovieIsVideo(boolean movieIsVideo) {
        this.movieIsVideo = movieIsVideo;
    }

    public Double getMovieVoteAverage() {
        return movieVoteAverage;
    }

    public void setMovieVoteAverage(Double movieVoteAverage) {
        this.movieVoteAverage = movieVoteAverage;
    }

    //------PARCELABLE START HERE------//
    @Override
    public int describeContents() {
        return 0;
    }

    //STORE TO PARCEL
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(moviePosterPath);
        out.writeInt(movieIsAdult ? 1 : 0);
        out.writeString(movieOverview);
        out.writeString(movieReleaseDate);
        out.writeList(movieGenreIds);
        out.writeInt(movieId);
        out.writeString(movieOriginalTitle);
        out.writeString(movieOriginalLanguage);
        out.writeString(movieTitle);
        out.writeString(movieBackdropPath);
        out.writeDouble(moviePopularity);
        out.writeInt(movieVoteCount);
        out.writeInt(movieIsVideo ? 1 : 0);
        out.writeDouble(movieVoteAverage);
    }


    //READ TO MOVIEOBJECT
    private MovieObject(Parcel in) {
        this.moviePosterPath = in.readString();
        this.movieIsAdult = in.readInt() == 1;
        this.movieOverview = in.readString();
        this.movieReleaseDate = in.readString();
        in.readList(this.movieGenreIds,List.class.getClassLoader());
        this.movieId = in.readInt();
        this.movieOriginalTitle = in.readString();
        this.movieOriginalLanguage = in.readString();
        this.movieTitle = in.readString();
        this.movieBackdropPath = in.readString();
        this.moviePopularity = in.readDouble();
        this.movieVoteCount = in.readInt();
        this.movieIsVideo = in.readInt() == 1;
        this.movieVoteAverage = in.readDouble();
    }

    public static final Creator<MovieObject> CREATOR = new Creator<MovieObject>() {
        @Override
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int size) {
            return new MovieObject[size];
        }
    };
}