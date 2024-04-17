package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ContactsPage extends AppCompatActivity {
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacts_page);
        nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.dial) {
                startActivity(new Intent(ContactsPage.this, DialPage.class));
                return true;
            } else if (id == R.id.favorites) {
                startActivity(new Intent(ContactsPage.this, MainActivity.class));
                return true;
            } else if (id == R.id.recent) {
                startActivity(new Intent(ContactsPage.this, RecentCalls.class));
                return true;
            }
            return false;

        });
    }
}