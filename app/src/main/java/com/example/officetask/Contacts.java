package com.example.officetask;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contacts extends AppCompatActivity {

        TextView contactList;
        String[] projection;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.contacts);

            contactList = findViewById(R.id.contactList);
            projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
            displayContacts();
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
                }
                cursor.close();

                contactList.setText(contactText.toString());
            }
        }
    }

