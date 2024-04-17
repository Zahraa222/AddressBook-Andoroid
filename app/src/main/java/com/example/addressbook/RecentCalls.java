package com.example.addressbook;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RecentCalls extends AppCompatActivity {
    private EditText phone;
    private EditText texts;
    private Button btn;
    AlertDialog.Builder alert;
    private int PERMISSION_Code = 10;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recent_calls);
        phone = findViewById(R.id.phonenum);
        texts = findViewById(R.id.textmsg);
        btn = findViewById(R.id.send);
        alert = new AlertDialog.Builder(RecentCalls.this);
        alert.setTitle("Invalid Format");
        alert.setMessage("Phone Number Must be 10 digits long!");
        alert.setCancelable(false);
        alert.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) ->{
            dialog.cancel();
        });



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




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    {
                        send_sms();
                    }
                }else {
                    requestPermissions(new String[]{android.Manifest.permission.SEND_SMS},PERMISSION_Code);
                }}
        });
    }

    private void send_sms(){
        String phone_no = phone.getText().toString();
        String sms_input = texts.getText().toString();

        if (phone_no.length() != 10) {
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone_no, null, sms_input, null, null);
            Toast.makeText(this, "Message Sent Successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, "Message sending failed", Toast.LENGTH_SHORT).show();
        }
    }
    }
