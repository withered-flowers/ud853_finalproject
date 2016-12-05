package com.example.android.ud853.finalproject.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.android.ud853.finalproject.adapter.MovieObject_ReviewAdapter;
import com.example.android.ud853.finalproject.adapter.MovieObject_TrailerAdapter;
import com.squareup.picasso.Picasso;

import com.example.android.ud853.finalproject.backend.*;

import java.util.List;

public class MovieDetailFragment extends Fragment {
    Call<MovieObject> callMovieData;
    Call<MovieData_Trailers> callMovieTrailer;
    Call<MovieData_Reviews> callMovieComment;

    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();
    View rootView;
    MovieObject theMovieObj;
    List<MovieObject_Trailer> theTrailerObj;
    List<MovieObject_Review> theReviewObj;

    Button btnMovieFavorite;
    LinearLayout lytMovieTrailer;
    LinearLayout lytMovieReview;
    TextView txtMovieTitle;
    TextView txtMovieReleaseDate;
    TextView txtMovieDuration;
    TextView txtMovieRating;
    TextView txtMovieOverview;
    ImageView imgMovieDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_moviedetail, container, false);

        btnMovieFavorite = (Button) rootView.findViewById(R.id.btnMovieFavorite);
        txtMovieTitle = (TextView) rootView.findViewById(R.id.txtMovieTitle);
        txtMovieReleaseDate = (TextView) rootView.findViewById(R.id.txtMovieReleaseDate);
        txtMovieDuration = (TextView) rootView.findViewById(R.id.txtMovieDuration);
        txtMovieRating = (TextView) rootView.findViewById(R.id.txtMovieRating);
        txtMovieOverview = (TextView) rootView.findViewById(R.id.txtMovieOverview);
        imgMovieDetail = (ImageView) rootView.findViewById(R.id.imgMovieDetail);
        lytMovieTrailer = (LinearLayout) rootView.findViewById(R.id.lytMovieTrailer);
        lytMovieReview = (LinearLayout) rootView.findViewById(R.id.lytMovieReview);

        Bundle b = this.getArguments();
        theMovieObj = b.getParcelable("theObj");

        assert theMovieObj != null;
        updateAll(theMovieObj.getMovieId());

        return rootView;
    }

    private void updateAll(Integer movieId) {
        MovieDataFetcher theFetcher = new MovieDataFetcher();
        MovieInterfaces theInterface = theFetcher.getFetcher().create(MovieInterfaces.class);
        callMovieData = theInterface
            .getMoviesById(movieId, BuildConfig.MOVIE_DB_API_KEY_V3);
        callMovieTrailer = theInterface
            .getTrailerByMovieId(movieId, BuildConfig.MOVIE_DB_API_KEY_V3);
        callMovieComment = theInterface
            .getReviewByMovieId(movieId, BuildConfig.MOVIE_DB_API_KEY_V3);

        callMovieData.enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> resp) {
                assert resp != null;
                theMovieObj.setMovieRuntime(resp.body().getMovieRuntime());
                fillPart01();
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        callMovieTrailer.enqueue(new Callback<MovieData_Trailers>() {
            @Override
            public void onResponse(Call<MovieData_Trailers> call, Response<MovieData_Trailers> resp) {
                assert resp != null;
                theTrailerObj = resp.body().getTrailerResults();
                fillPart02();
            }

            @Override
            public void onFailure(Call<MovieData_Trailers> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        callMovieComment.enqueue(new Callback<MovieData_Reviews>() {
            @Override
            public void onResponse(Call<MovieData_Reviews> call, Response<MovieData_Reviews> resp) {
                assert resp != null;
                theReviewObj = resp.body().getReviewResults();
                fillPart03();
            }

            @Override
            public void onFailure(Call<MovieData_Reviews> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }

    private void fillPart01() {
        txtMovieTitle.setText(theMovieObj.getMovieTitle());
        txtMovieReleaseDate.setText(theMovieObj.getMovieReleaseDate());
        txtMovieDuration.setText(String.format("%d Minutes", theMovieObj.getMovieRuntime()));
        txtMovieRating.setText(String.format("%s/10", theMovieObj.getMovieVoteAverage().toString()));
        txtMovieOverview.setText(theMovieObj.getMovieOverview());

        btnMovieFavorite.setVisibility(View.VISIBLE);

        String imageUrl = "http://image.tmdb.org/t/p/w185/";
        Picasso.with(getContext()).load(imageUrl + theMovieObj.getMoviePosterPath()).into(imgMovieDetail);
    }

    private void fillPart02() {
        assert theTrailerObj.size() != 0;

        final MovieObject_TrailerAdapter adp = new MovieObject_TrailerAdapter(getActivity(), theTrailerObj);

        for(Integer i = 0; i<adp.getCount(); i ++) {
            View item = adp.getView(i, null, null);

            final Integer x = i;

            LinearLayout eachLayout = (LinearLayout) item.findViewById(R.id.lytEachTrailer);
            eachLayout.setClickable(true);
            eachLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String baseYoutubeURL = "https://www.youtube.com/watch?v=";
                    String theUrl = baseYoutubeURL + adp.getItem(x).getTrailerKey();

                    startActivity(
                        new Intent(Intent.ACTION_VIEW, Uri.parse(theUrl))
                    );
                }
            });

            lytMovieTrailer.addView(item);
        }
    }

    private void fillPart03() {
        assert theReviewObj.size() != 0;

        final MovieObject_ReviewAdapter adp = new MovieObject_ReviewAdapter(getActivity(), theReviewObj);

        for(Integer i = 0; i<adp.getCount(); i++) {
            View item = adp.getView(i, null, null);

            LinearLayout eachLayout = (LinearLayout) item.findViewById(R.id.lytEachReview);
            eachLayout.setClickable(true);

            lytMovieReview.addView(item);
        }
    }
}
