package com.example.android.ud853.finalproject.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

import com.example.android.ud853.finalproject.backend.MovieObject;

public class MovieObjectAdapter extends ArrayAdapter<MovieObject> {
    public MovieObjectAdapter(Activity context, List<MovieObject> movieObjectList) {
        super(context, 0 , movieObjectList);
    }

    @Override 
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject obj = getItem(position);
        return convertView;
    }
}