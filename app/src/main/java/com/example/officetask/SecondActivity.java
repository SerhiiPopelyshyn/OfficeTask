package com.example.officetask;

import com.squareup.picasso.Picasso;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Intent intent = getIntent();
        String enteredText = intent.getStringExtra("enteredText");

        TextView displayText = findViewById(R.id.textView);
        displayText.setText(enteredText);

        TextView textView =findViewById(R.id.invisibleText);
        TextView textViewL =findViewById(R.id.looser);

        ImageView imageView = findViewById(R.id.imageView);
        String imageUrl = "https://ih1.redbubble.net/image.4282822462.7991/flat,750x,075,f-pad,750x1000,f8f8f8.jpg";

        Picasso.get()
                .load("https://ih1.redbubble.net/image.4282822462.7991/flat,750x,075,f-pad,750x1000,f8f8f8.jpg")
                .into(imageView);

        if (enteredText.equalsIgnoreCase("virus")) {
            Toast.makeText(getApplicationContext(), "VIRUS DETECTED          YEEEEEAAAAAHHHHH!!!!", Toast.LENGTH_LONG).show();
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }else{
            textViewL.setVisibility(View.VISIBLE);
        }
    }
}

