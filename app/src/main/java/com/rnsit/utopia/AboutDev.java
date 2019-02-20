package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class AboutDev extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);

    }

    /*private void displayFromAsset() {

        pdfView.fromAsset("tech_fest.pdf")
                .defaultPage(0)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }*/

}
