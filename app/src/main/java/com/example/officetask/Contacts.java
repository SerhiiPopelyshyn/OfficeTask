package com.example.officetask;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class Contacts extends AppCompatActivity {

    TextView contactList;
    private static final int CONTACTS_PERMISSION_REQUEST = 123;

    public void getContact(View view) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_CONTACTS}, 10);
            showPermissionExplanationAndRequest();
        }else {

        }
    }
    private void showPermissionExplanationAndRequest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required");
        builder.setMessage("This app needs access to your contacts to provide a better user experience.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Request the permission
                ActivityCompat.requestPermissions(Contacts.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_REQUEST);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Permission request was canceled, handle accordingly
            }
        });
        builder.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);

        contactList = findViewById(R.id.contactList);

        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.append("Name: " + name + " " + "Number: " + number + " ,");
            }
            cursor.close();
        }
    }
}





