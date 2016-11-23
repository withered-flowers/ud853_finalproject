package com.example.android.ud853.finalproject.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridMovieFragment extends Fragment {
    private static final String LOG_TAG = GridMovieFragment.class.getSimpleName();

    GridView grdMovieList;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gridmovie, container, false);

        grdMovieList = (GridView) rootView.findViewById(R.id.grdMovieList);

        MovieDataFetcher theFetcher = new MovieDataFetcher();
        MovieInterfaces theInterface = theFetcher.getFetcher().create(MovieInterfaces.class);
        Call<MovieData> callMovieData = theInterface
            .getMoviesBySort("popular", BuildConfig.MOVIE_DB_API_KEY_V3);
        
        callMovieData.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> resp) {
                if(resp != null) {
                    List<MovieObject> listMovies = resp.body().getDataResults();

                    //TODO Allocate the movie here !
                    String[] arrString = new String[listMovies.size()];
                    int index = 0;
                    for(MovieObject obj : listMovies) {
                        arrString[index] = String.valueOf(obj.getMovieOriginalTitle());
                        index++;
                    }

                    ArrayAdapter<String> adp = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        arrString
                    );
                    grdMovieList.setAdapter(adp);
                    //ENDOFTODO

                    Log.d(LOG_TAG, "Total Film: " + listMovies.size());
                }
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        return rootView;
    }

}