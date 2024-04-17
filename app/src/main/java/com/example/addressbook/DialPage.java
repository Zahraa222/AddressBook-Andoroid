package com.example.addressbook;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DialPage extends AppCompatActivity {
    private Button callbtn;
    private EditText txt;
    private int PERMISSION_Code = 10;
    AlertDialog.Builder alert;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dial_page);
        nav = findViewById(R.id.bottom_navigation);
        callbtn = findViewById(R.id.callbtn);
        txt = findViewById(R.id.editTextPhone);


        alert = new AlertDialog.Builder(DialPage.this);
        alert.setTitle("Invalid Format");
        alert.setMessage("Phone Number Must be 10 digits long!");
        alert.setCancelable(false);
        alert.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog,which) ->{
            dialog.cancel();
        });


        //Handling nav bar selections
        nav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.favorites) {
                startActivity(new Intent(DialPage.this, MainActivity.class));
                return true;
            } else if (id == R.id.contacts) {
                startActivity(new Intent(DialPage.this, ContactsPage.class));
                return true;
            } else if (id == R.id.recent) {
                startActivity(new Intent(DialPage.this, RecentCalls.class));
                return true;
            }
            return false;
        });


        if(ContextCompat.checkSelfPermission(DialPage.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DialPage.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_Code);
        }

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = txt.getText().toString();
                if (phone.length() == 10){
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData((Uri.parse("tel:" + phone)));
                    startActivity(i);
                }
                else {
                    AlertDialog alertDialog = alert.create();
                    alertDialog.show();
                }

            }
        });
    }
}