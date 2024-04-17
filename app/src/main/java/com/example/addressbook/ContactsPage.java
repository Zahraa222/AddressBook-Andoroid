package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ContactsPage extends AppCompatActivity {
    BottomNavigationView nav;
    ListView contactList;

    Button editContacts;
    Button createContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_page);
        editContacts = findViewById(R.id.edit);
        createContact = findViewById(R.id.create);
        contactList = findViewById(R.id.contactList);

        ContactManager manager = ContactManager.getContact();
        List<String> contacts = manager.getContactsList();

        //Array Adapter for displaying Contacts
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, contacts);
        contactList.setAdapter(adapter);

        contactList.setOnItemClickListener((parent, view, position, id) -> {
            editContacts.setVisibility(View.VISIBLE);
        });


        //nav bar handling
        nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.dial) {
                startActivity(new Intent(ContactsPage.this, DialPage.class));
                return true;
            } else if (id == R.id.favorites) {
                startActivity(new Intent(ContactsPage.this, MainActivity.class));
                return true;
            } else if (id == R.id.SMS) {
                startActivity(new Intent(ContactsPage.this, RecentCalls.class));
                return true;
            }
            return false;

        });
    }
}