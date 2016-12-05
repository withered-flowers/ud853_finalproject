package com.example.android.ud853.finalproject.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.ud853.finalproject.app.R;
import com.example.android.ud853.finalproject.backend.MovieObject_Trailer;

import java.util.List;

public class MovieObject_TrailerAdapter extends ArrayAdapter<MovieObject_Trailer> {
    public MovieObject_TrailerAdapter(Activity context, List<MovieObject_Trailer> movieObjectTrailerList) {
        super(context, 0 , movieObjectTrailerList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject_Trailer obj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_moviedetail_eachtrailer, parent, false);
        }

        TextView trailerText = (TextView) convertView.findViewById(R.id.tvwEachTrailer);

        assert obj != null;
        trailerText.setText(obj.getTrailerName());

        return convertView;
    }
}
