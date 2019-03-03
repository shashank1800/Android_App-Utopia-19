package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutDev extends AppCompatActivity{
    private ImageView img_profile;
    private Context context;
    private DatabaseReference mFirebaseDBRef;
    private String dpURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_dev);

        context = this;
        img_profile = (ImageView) findViewById(R.id.img_profile);
        mFirebaseDBRef = FirebaseDatabase.getInstance().getReference();
        mFirebaseDBRef.child("Dev").child("dpURL")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dpURL = String.valueOf(dataSnapshot.getValue());
                        dpURL = dpURL.trim();
                        Glide.with(context).load(dpURL).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(img_profile);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
