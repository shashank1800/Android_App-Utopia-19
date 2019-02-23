package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Events extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private CardView totalEvents,upcomingEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        totalEvents = (CardView) findViewById(R.id.totalEvents);
        upcomingEvents = (CardView) findViewById(R.id.upcomingEvents);

        totalEvents.setOnClickListener(this);
        upcomingEvents.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        switch (id){
            case R.id.totalEvents:startActivityFunction(TotalEvents.class);
                break;
            case R.id.upcomingEvents:startActivityFunction(UpcomingEvents.class);
                break;
        }
    }

    protected void startActivityFunction(Class cl){
        Intent intent = new Intent(context,cl);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}
