package com.example.officetask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Contacts extends AppCompatActivity implements RecyclerViewInterface{
 /*   TextView contactTextView;
    String[] projection;
    ArrayList<ContactObject> contactObject = new ArrayList<ContactObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        displayContacts();

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, contactObject,this );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );

        contactTextView = findViewById(R.id.contactList);
        //projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Email.ADDRESS};
        projection = new String[]{
          ContactsContract.CommonDataKinds.Phone.NUMBER
        };
    }

    private void displayContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            StringBuilder contactText = new StringBuilder();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));
                //contactText.append("Name: ").append(name).append(" Number: ").append(number).append(" Email: ");

                contactObject.add(new ContactObject(name, number, email));
            }
            cursor.close();

           // contactTextView.setText(contactText.toString());
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Contacts.this, MainActivity.class);

        intent.putExtra("Name", contactObject.get(position).getNameContact());

        startActivity(intent);

    }*/

    ArrayList<ContactObject> contactObject = new ArrayList<>();
    TextView contactInfoTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, contactObject);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactInfoTextView = findViewById(R.id.contactInfoTextView);

        // Find the "Select Contact" button and add a click event listener
        Button selectContactButton = findViewById(R.id.selectContactButton);
        selectContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an implicit intent to pick a contact
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(pickContactIntent, 1);
            }
        });

        displayContacts();
    }

    // Handle the result from the contact selection intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1 && data != null) {
                Uri contactUri = data.getData();

                // Query the selected contact's name and number
                Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    // Display the selected contact's info in the TextView
                    String contactInfo = "Name: " + name + "\nNumber: " + number;
                    contactInfoTextView.setText(contactInfo);
                    cursor.close();
                }
            }
        }
    }

    // The displayContacts method remains the same
    private void displayContacts() {
        // ...
    }

    @Override
    public void onItemClick(int position) {

    }
}


