package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class AboutDev extends AppCompatActivity{
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        displayFromAsset();
    }

    private void displayFromAsset() {

        pdfView.fromAsset("tech_fest.pdf")
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

}
