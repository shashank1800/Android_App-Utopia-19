package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;

public class SplashScreen extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;

        int SPLASH_TIME = 400;
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ObsoleteSdkInt")
            @Override
            public void run() {

                try{
                    Pair<View, String> p1 = Pair.create(findViewById(R.id.splash_image), getResources().getString(R.string.utopiaLogo));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                            Intent intent = new Intent(context, MainActivity.class);
                            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation((Activity) context,p1)
                                    .toBundle();
                            context.startActivity(intent,bundle);
                        }
                    }
                }
                catch (Exception ex){
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);
                }
                finish();

            }
        }, SPLASH_TIME);
    }
}
