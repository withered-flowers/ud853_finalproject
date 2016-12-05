package com.example.android.ud853.finalproject.app;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.ud853.finalproject.backend.MovieObject;

public class MainActivity extends AppCompatActivity implements CustomOnChangeSelectionListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Multi Pane Fragment here !
        if(findViewById(R.id.fmtContainer) != null) {
            if(savedInstanceState != null) return;

            GridMovieFragment fmtGridMovie = new GridMovieFragment();

            getSupportFragmentManager().beginTransaction()
                .add(R.id.fmtContainer, fmtGridMovie)
                .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;

        switch(id) {
            case (R.id.action_settings):
                i = new Intent(this, SettingsActivity.class);
                startActivity(i);
                break;
            case(R.id.action_about):
                i = new Intent(this, AboutActivity.class);
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectionChange(MovieObject obj) {
        MovieDetailFragment fmtMovieDetail = (MovieDetailFragment) getSupportFragmentManager()
            .findFragmentById(R.id.fmtMovieDetail);

        if(fmtMovieDetail != null) {
            //TODO Multi Pane Here
        }
        else {
            //TODO Single Pane Here
            Bundle b = new Bundle();
            b.putParcelable("theObj", obj);
            
            MovieDetailFragment fmtMovieDetail_new = new MovieDetailFragment();
            fmtMovieDetail_new.setArguments(b);

            FragmentTransaction fmtTr = getSupportFragmentManager().beginTransaction();
            fmtTr.replace(R.id.fmtContainer, fmtMovieDetail_new);
            fmtTr.addToBackStack(null);
            fmtTr.commit();
        }
    }
}
