package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RecentCalls extends AppCompatActivity {
    List<String> recentCalls = new ArrayList<>();
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recent_calls);
        nav = findViewById(R.id.bottom_navigation);
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.dial) {
                startActivity(new Intent(RecentCalls.this, DialPage.class));
                return true;
            } else if (id == R.id.contacts) {
                startActivity(new Intent(RecentCalls.this, ContactsPage.class));
                return true;
            } else if (id == R.id.favorites) {
                startActivity(new Intent(RecentCalls.this, MainActivity.class));
                return true;
            }
            return false;
        });

        Intent intent = getIntent();
        String lastDialed = intent.getStringExtra("recentNumber");
        if (lastDialed != null && !lastDialed.isEmpty()){
            recentCalls.add(lastDialed);
        }
    }
}