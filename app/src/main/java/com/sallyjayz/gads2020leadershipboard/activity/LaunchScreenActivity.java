package com.sallyjayz.gads2020leadershipboard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sallyjayz.gads2020leadershipboard.R;

public class LaunchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launch_screen);

        Intent intent = new Intent(this, LeadershipboardActivity.class);
        startActivity(intent);
        finish();
    }
}