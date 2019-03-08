package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class AboutDev extends AppCompatActivity{
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);

        photo = (ImageView)findViewById(R.id.photo);

        Glide.with(this)
                .load(R.drawable.my_photo)
                .apply(RequestOptions.circleCropTransform())
                .into(photo);

    }
}
