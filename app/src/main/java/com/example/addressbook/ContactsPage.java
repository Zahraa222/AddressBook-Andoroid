package com.example.addressbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
    ArrayAdapter<String> adapter;
    EditText addName, addPhone;
    AlertDialog.Builder alert;
    AlertDialog.Builder dialog;
    int selectedPosition = -1;
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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, contacts);
        contactList.setAdapter(adapter);

        contactList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            editContacts.setVisibility(View.VISIBLE);
        });

        //alter for requesting edit, can either delete or add to favorites
        alert = new AlertDialog.Builder(ContactsPage.this);
        alert.setTitle("Edit Options");
        alert.setMessage("Choose Whether you would Like to delete or add contact to Favorites.");
        alert.setCancelable(false);
        alert.setPositiveButton("Add to Favorites", (DialogInterface.OnClickListener) (dialog, which) ->{
            //send to favorites with key or wotevah
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("new fave", contacts.get(selectedPosition));
            startActivity(intent);
            dialog.dismiss();
        });
        alert.setNegativeButton("Delete Contact", (DialogInterface.OnClickListener) (dialog, which) ->{
            if (selectedPosition != -1){
                contacts.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

        });


        editContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = alert.create();
                alertDialog.show();

            }
        });



        //Handling create new contact button
        createContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(ContactsPage.this);
                dialog.setTitle("Enter new Contact Info:");
                View view = getLayoutInflater().inflate(R.layout.add_contact, null);
                addName = view.findViewById(R.id.editTextName);
                addPhone = view.findViewById(R.id.editTextPhoneNumber);
                dialog.setView(view);
                dialog.setCancelable(false);
                dialog.setPositiveButton("Save", (DialogInterface.OnClickListener) (dialog, which) ->{
                        String name = addName.getText().toString();
                        String phone = addPhone.getText().toString();
                        if (!name.isEmpty() && !phone.isEmpty()){
                            contacts.add(name + "\n" + phone);
                            adapter.notifyDataSetChanged();

                        }
                });
                dialog.setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, which) ->{
                    dialog.cancel();
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
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