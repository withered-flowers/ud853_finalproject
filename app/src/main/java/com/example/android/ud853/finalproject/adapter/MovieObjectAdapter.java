package com.example.android.ud853.finalproject.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.List;
import com.squareup.picasso.Picasso;

import com.example.android.ud853.finalproject.app.R;
import com.example.android.ud853.finalproject.backend.MovieObject;


public class MovieObjectAdapter extends ArrayAdapter<MovieObject> {
    public MovieObjectAdapter(Activity context, List<MovieObject> movieObjectList) {
        super(context, 0 , movieObjectList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject obj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_gridmovie_eachmovie, parent, false);
        }

        ImageView movieImage = (ImageView) convertView.findViewById(R.id.grdMovieList_image);

        String imageUrl = "http://image.tmdb.org/t/p/w185/";
        Picasso.with(getContext()).load(imageUrl + obj.getMoviePosterPath()).into(movieImage);

        return convertView;
    }
}