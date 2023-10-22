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

public class Contacts extends AppCompatActivity implements RecyclerViewInterface{
    TextView contactTextView;
    //String[] projection;
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

    }

    private void displayContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                null,
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

    }
}


