package com.example.addressbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView nav;
    private ListView fave;
    private Button faveButton;
   private AlertDialog.Builder favealert;
   private int selectedPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fave = findViewById(R.id.faveList);
        faveButton = findViewById(R.id.faveEdit);

        ContactManager manager = ContactManager.getContact();
        List<String> contacts = manager.getFavoritesList();

        //Array Adapter for displaying Contacts
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, contacts);
        fave.setAdapter(adapter);

        Intent intent = getIntent();
        String new_fave = intent.getStringExtra("new fave");

        if (new_fave != null && !new_fave.isEmpty()) {
            contacts.add(new_fave);
            adapter.notifyDataSetChanged(); // Update the adapter to reflect the new item
        }

        fave.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            faveButton.setVisibility(View.VISIBLE);
        });
        //-------------------------------------------------------
        favealert = new AlertDialog.Builder(MainActivity.this);
        favealert.setTitle("Edit Options");
        favealert.setMessage("Choose Whether you would Like to remove Contacts from favorites or Cancel Action:");
        favealert.setCancelable(false);
        favealert.setPositiveButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) ->{
            dialog.dismiss();
        });
        favealert.setNegativeButton("Remove from favorites", (DialogInterface.OnClickListener) (dialog, which) ->{
            if (selectedPosition != -1){
                contacts.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
                faveButton.setVisibility(View.INVISIBLE);
            }

        });


        faveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = favealert.create();
                alertDialog.show();

            }
        });


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