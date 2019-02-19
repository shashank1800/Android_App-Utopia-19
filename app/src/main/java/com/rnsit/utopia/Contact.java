package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity /*implements View.OnClickListener*/{

    /*private TextView contact_r1c1, contact_r1c2,contact_r2c1, contact_r2c2,contact_r3c1,contact_r3c2,contact_r4c1,contact_r4c2,contact_r5c1,contact_r5c2;
    private ImageView copy_r1c1,copy_r1c2,copy_r2c1,copy_r2c2,copy_r3c1,copy_r3c2,copy_r4c1,copy_r4c2,copy_r5c1,copy_r5c2;

    private ClipboardManager clipboard;
    private ClipData clip;
    private Context context;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*context= this;

        contact_r1c1 = (TextView)findViewById(R.id.contact_r1c1);
        contact_r1c2 = (TextView)findViewById(R.id.contact_r1c2);
        contact_r2c1 = (TextView)findViewById(R.id.contact_r2c1);
        contact_r2c2 = (TextView)findViewById(R.id.contact_r2c2);
        contact_r3c1 = (TextView)findViewById(R.id.contact_r3c1);
        contact_r3c2 = (TextView)findViewById(R.id.contact_r3c2);
        contact_r4c1 = (TextView)findViewById(R.id.contact_r4c1);
        contact_r4c2 = (TextView)findViewById(R.id.contact_r4c2);
        contact_r5c1 = (TextView)findViewById(R.id.contact_r5c1);
        contact_r5c2 = (TextView)findViewById(R.id.contact_r5c2);


        copy_r1c1 = (ImageView)findViewById(R.id.copy_r1c1);
        copy_r1c2 = (ImageView) findViewById(R.id.copy_r1c2);
        copy_r2c1 = (ImageView) findViewById(R.id.copy_r2c1);
        copy_r2c2 = (ImageView) findViewById(R.id.copy_r2c2);
        copy_r3c1 = (ImageView) findViewById(R.id.copy_r3c1);
        copy_r3c2 = (ImageView) findViewById(R.id.copy_r3c2);
        copy_r4c1 = (ImageView) findViewById(R.id.copy_r4c1);
        copy_r4c2 = (ImageView) findViewById(R.id.copy_r4c2);
        copy_r5c1 = (ImageView) findViewById(R.id.copy_r5c1);
        copy_r5c2 = (ImageView) findViewById(R.id.copy_r5c2);

        copy_r1c1.setOnClickListener(this);

        copy_r1c2.setOnClickListener(this);
        copy_r2c1.setOnClickListener(this);
        copy_r2c2.setOnClickListener(this);
        copy_r3c1.setOnClickListener(this);
        copy_r3c2.setOnClickListener(this);
        copy_r4c1.setOnClickListener(this);
        copy_r4c2.setOnClickListener(this);
        copy_r5c1.setOnClickListener(this);
        copy_r5c2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Vibrator vb = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if(id == R.id.copy_r1c1){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r1c1.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r1c2){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r1c2.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        } else if(id == R.id.copy_r2c1){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r2c1.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r2c2){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r2c2.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r3c1){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r3c1.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r3c2){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r3c2.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r4c1){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r4c1.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r4c2){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r4c2.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r5c1){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r5c1.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }else if(id == R.id.copy_r5c2){
            clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            clip = ClipData.newPlainText("Copied Text", contact_r5c2.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context,"Copied to Clipboard",Toast.LENGTH_SHORT).show();
            if (vb != null) {
                vb.vibrate(15);
            }
        }

    }*/
    }
}
