package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

public class AboutApp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        VideoView videoView;
        videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath("https://firebasestorage.googleapis.com/v0/b/utopia-8701a.appspot.com/o/Images%2F8173aa10-9d91-4502-be5a-b29726370e7d?alt=media&token=4ca7016c-f98d-416e-bb4a-42f90a49d975");
        videoView.start();
    }
}
