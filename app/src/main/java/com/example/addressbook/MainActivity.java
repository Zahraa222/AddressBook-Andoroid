package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView nav;
    ListView fave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fave = findViewById(R.id.faveList);

        ContactManager manager = ContactManager.getContact();
        List<String> contacts = manager.getContactsList();

        //Array Adapter for displaying Contacts
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, contacts);
        fave.setAdapter(adapter);

        Intent intent = getIntent();
        String new_fave = intent.getStringExtra("new fave");

        if (new_fave != null && !new_fave.isEmpty()) {
            contacts.add(new_fave);
            adapter.notifyDataSetChanged(); // Update the adapter to reflect the new item
        }

        nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.dial) {
                startActivity(new Intent(MainActivity.this, DialPage.class));
                return true;
            } else if (id == R.id.contacts) {
                startActivity(new Intent(MainActivity.this, ContactsPage.class));
                return true;
            } else if (id == R.id.SMS) {
                startActivity(new Intent(MainActivity.this, RecentCalls.class));
                return true;
            }
            return false;
        });



    }
}