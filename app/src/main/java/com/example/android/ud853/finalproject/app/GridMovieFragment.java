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

public class GridMovieFragment extends Fragment {
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

        //TODO Add CustomAdapter here !
        ArrayAdapter<String> adp = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            numbers
        );
        grdMovieList.setAdapter(adp);

        return rootView;
    }

}