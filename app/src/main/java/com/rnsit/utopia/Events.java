package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.util.Objects;

public class Events extends AppCompatActivity{

    private Context context;
    private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        context = this;

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        displayFromAsset();
    }
    private void displayFromAsset() {
        pdfView.fromAsset("scheduled_events.pdf")
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(context))
                .load();
    }
}
