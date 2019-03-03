package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;

public class Teams extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout team1Card,team2Card,team3Card,team4Card;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        team1Card = (LinearLayout) findViewById(R.id.team1Card);
        team2Card = (LinearLayout) findViewById(R.id.team2Card);
        team3Card = (LinearLayout) findViewById(R.id.team3Card);
        team4Card = (LinearLayout) findViewById(R.id.team4Card);

        team1Card.setOnClickListener(this);
        team2Card.setOnClickListener(this);
        team3Card.setOnClickListener(this);
        team4Card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context,TeamPdfView.class);
        switch (v.getId()){
            case R.id.team1Card:
                intent.putExtra("pdfName","tech_fest.pdf");
                startActivity(intent);
                break;
            case R.id.team2Card:
                intent.putExtra("pdfName","cg.pdf");
                startActivity(intent);
                break;
            case R.id.team3Card:
                intent.putExtra("pdfName","team1.pdf");
                startActivity(intent);
                break;
            case R.id.team4Card:
                intent.putExtra("pdfName","tech_fest.pdf");
                startActivity(intent);
                break;
        }
    }
}
