package com.example.android.ud853.finalproject.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView tvwAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tvwAbout = (TextView) findViewById(R.id.tvwAbout);
        
        tvwAbout.setText(
            getString(
                R.string.textview_about_values, BuildConfig.GITHUB_UNAME, BuildConfig.GITHUB_ULINK
            )
        );
    }
}