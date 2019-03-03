package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {
    private EditText feedback_text;
    private Button submit;
    private Context context;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        feedback_text = (EditText) findViewById(R.id.feedback_text);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedback_text.getText().toString().compareTo("")==0)
                    Toast.makeText(context,"Please enter Feedback text",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(context,"Thank you for your Feedback",Toast.LENGTH_SHORT).show();
                    String value = feedback_text.getText().toString();
                    mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                    mDatabaseRef.child("Feedback").push().setValue(value);
                    feedback_text.setText("");
                }
            }
        });
    }
}
