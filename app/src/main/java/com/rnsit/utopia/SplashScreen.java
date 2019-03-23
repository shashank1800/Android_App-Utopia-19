package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {
    private int SPLASH_TIME = 300;
    private Context context;
    private ImageView splash_gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;

        //splash_gif = (ImageView)findViewById(R.id.splash_gif);
        //Glide.with(context).asGif().load(R.drawable.splash_screen).into(splash_gif);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(context, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);
                finish();
            }
        }, SPLASH_TIME);
    }
}
