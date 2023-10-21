package com.example.officetask;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    TextView contactTextView;
    String[] projection;
    ArrayList<ContactObject> contactObject = new ArrayList<ContactObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        //contactTextView =
        displayContacts();

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, contactObject);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );

        contactTextView = findViewById(R.id.contactList);
        projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

    }

    private void displayContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
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
                contactText.append("Name: ").append(name).append(" Number: ").append(number).append(" ,");

                contactObject.add(new ContactObject(name, number, ""));
            }
            cursor.close();

            //contactTextView.setText(contactText.toString());
        }
    }
}


