package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button logoutBTn,bookTicket,cancelBtn,adminBtn;

    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    logoutBTn= findViewById(R.id.logoutBtn);
    fAuth = FirebaseAuth.getInstance();
    bookTicket=findViewById(R.id.book_btn);
    cancelBtn=findViewById(R.id.cancel_btn);
    adminBtn = findViewById(R.id.admin_btn);
    bookTicket.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),TransportActivity.class));
        }
    });
    cancelBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),CancelActivity.class));
        }
    });
    adminBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),AdminActivity.class));
        }
    });

    logoutBTn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fAuth.signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


    });
    }
}