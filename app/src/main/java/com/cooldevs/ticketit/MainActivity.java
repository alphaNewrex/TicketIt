package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button logoutBTn;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    logoutBTn= findViewById(R.id.logoutBtn);
    fAuth = FirebaseAuth.getInstance();
    logoutBTn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fAuth.signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    });
    }
}