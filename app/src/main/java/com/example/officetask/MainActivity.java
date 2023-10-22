package com.example.officetask;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    private static final int CONTACTS_PERMISSION_REQUEST = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button btn = (Button) findViewById(R.id.btnSearch);

        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("Name")){
                String name = intent.getStringExtra("Name");
                editText.setText(name);
            }
        }
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
                if(checkContactsPermission()){
                    goToContacts();
                }else{
                    requestContactPermissions();
                }
            }
        });
    }
    private boolean checkContactsPermission() {
        boolean check = (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);
        System.out.println("PERMISSION GRANTED : " + check);
        return check;
    }
    private void goToContacts(){
        Intent intent = new Intent(MainActivity.this, Contacts.class);
        startActivity(intent);
    }
    private void requestContactPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_REQUEST);
    }
}
