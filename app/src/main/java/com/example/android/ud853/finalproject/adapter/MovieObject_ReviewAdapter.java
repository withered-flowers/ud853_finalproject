package com.example.android.ud853.finalproject.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.ud853.finalproject.app.R;
import com.example.android.ud853.finalproject.backend.MovieObject_Review;

import java.util.List;

public class MovieObject_ReviewAdapter extends ArrayAdapter<MovieObject_Review> {
    public MovieObject_ReviewAdapter(Activity context, List<MovieObject_Review> movieObjectReviewList) {
        super(context, 0 , movieObjectReviewList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieObject_Review obj = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_moviedetail_eachreview, parent, false);
        }

        //TODO set EachMovieReview data here
        TextView tvwAuthor = (TextView) convertView.findViewById(R.id.tvwEachReview_author);
        TextView tvwContent = (TextView) convertView.findViewById(R.id.tvwEachReview_content);

        assert obj != null;
        tvwAuthor.setText(obj.getReviewAuthor());
        tvwContent.setText(obj.getReviewContent());

        return convertView;
    }
}
