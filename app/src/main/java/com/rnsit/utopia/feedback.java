package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class feedback extends AppCompatActivity {
    private EditText feedback_text;
    private Button submit;
    private Context context;
    private FirebaseFirestore db;

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
                    Snackbar.make(findViewById(R.id.linearLayout), "Please enter Feedback text", Snackbar.LENGTH_SHORT).show();
                else {
                    Snackbar.make(findViewById(R.id.linearLayout), "Thanks for your Feedback!", Snackbar.LENGTH_SHORT).show();

                    String uniqueID = UUID.randomUUID().toString();
                    String feedbackText = feedback_text.getText().toString();

                    Map<String, Object> feedback = new HashMap<>();
                    feedback.put("feedbackText", feedbackText);

                    db = FirebaseFirestore.getInstance();
                    db.collection("Feedback").document(uniqueID).set(feedback);

                    feedback_text.setText("");

                    InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }
}
