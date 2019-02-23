package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UpcomingEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
