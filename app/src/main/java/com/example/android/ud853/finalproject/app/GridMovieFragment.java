package com.example.android.ud853.finalproject.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.android.ud853.finalproject.adapter.MovieObjectAdapter;
import com.example.android.ud853.finalproject.backend.*;

public class GridMovieFragment extends Fragment {
    private static final String LOG_TAG = GridMovieFragment.class.getSimpleName();

    GridView grdMovieList;
    Call<MovieData> callMovieData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gridmovie, container, false);
        
        grdMovieList = (GridView) rootView.findViewById(R.id.grdMovieList);
        grdMovieList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {

                MovieObject obj = (MovieObject) grdMovieList.getAdapter().getItem(position);

                //TODO PASS DATA TO ANOTHER ACTIVITY HERE
                Toast.makeText(getContext(), obj.getMovieReleaseDate(), Toast.LENGTH_LONG).show();
            };
        });   

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        callMovieData.cancel();
    }

    private void updateList() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortBy = prefs.getString(
            getString(R.string.pref_movie_sortby_key),
            getString(R.string.pref_movie_sortby_option_default_value)
        );

        MovieDataFetcher theFetcher = new MovieDataFetcher();
        MovieInterfaces theInterface = theFetcher.getFetcher().create(MovieInterfaces.class);
        callMovieData = theInterface
            .getMoviesBySort(sortBy, BuildConfig.MOVIE_DB_API_KEY_V3);
        
        callMovieData.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> resp) {
                assert resp != null;
                List<MovieObject> listMovies = resp.body().getDataResults();

                assert listMovies.size() != 0;
                
                MovieObjectAdapter adp = new MovieObjectAdapter(getActivity(), listMovies);
                grdMovieList.setAdapter(adp);

                Log.d(LOG_TAG, "Total Film: " + listMovies.size());
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });
    }
}