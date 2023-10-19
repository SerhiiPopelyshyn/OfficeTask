package com.example.officetask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button btn = (Button) findViewById(R.id.btnSearch);


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String enteredText = editText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("enteredText", enteredText);
                startActivity(intent);
            }
        });

        Button facebtn = (Button) findViewById(R.id.facebtn);
        facebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Facebook.class);
                startActivity(intent);
            }
        });

        Button contacts = (Button) findViewById(R.id.contacts);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Contacts.class);
                startActivity(intent);
            }
        });

    }

}
