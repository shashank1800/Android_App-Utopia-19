package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Teams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
    }
}
