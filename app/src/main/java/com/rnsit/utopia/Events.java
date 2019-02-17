package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
