package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.util.Objects;

public class TeamPdfView extends AppCompatActivity {

    private Context context;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_pdf_view);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        context = this;

        String pdfName = getIntent().getStringExtra("pdfName");

        pdfView = (PDFView) findViewById(R.id.pdfView);
        displayFromAsset(pdfName);
    }
    private void displayFromAsset(String pdfName) {
        pdfView.fromAsset(pdfName)
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(context))
                .load();
    }
}
