package com.example.android.ud853.finalproject.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.squareup.picasso.Picasso;

import com.example.android.ud853.finalproject.backend.*;

public class MovieDetailFragment extends Fragment {
    Call<MovieObject> callMovieData;

    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();
    View rootView;
    MovieObject theObj;

    Button btnMovieFavorite;
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
        
        Bundle b = this.getArguments();
        theObj = (MovieObject)b.getParcelable("theObj");

        updateAll(theObj.getMovieId());

        return rootView;
    }

    private void updateAll(Integer movieId) {
        MovieDataFetcher theFetcher = new MovieDataFetcher();
        MovieInterfaces theInterface = theFetcher.getFetcher().create(MovieInterfaces.class);
        callMovieData = theInterface
            .getMoviesById(movieId, BuildConfig.MOVIE_DB_API_KEY_V3);

        callMovieData.enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> resp) {
                assert resp != null;
                theObj.setMovieRuntime(resp.body().getMovieRuntime());
                fillPart01();
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }

    private void fillPart01() {
        txtMovieTitle.setText(theObj.getMovieTitle());
        txtMovieReleaseDate.setText(theObj.getMovieReleaseDate());
        txtMovieDuration.setText(theObj.getMovieRuntime() + " Minutes");
        txtMovieRating.setText(theObj.getMovieVoteAverage().toString() + "/10");
        txtMovieOverview.setText(theObj.getMovieOverview());

        btnMovieFavorite.setVisibility(View.VISIBLE);

        String imageUrl = "http://image.tmdb.org/t/p/w92/";
        Picasso.with(getContext()).load(imageUrl + theObj.getMoviePosterPath()).into(imgMovieDetail);
    }

    private void fillPart02() {

    }
}
