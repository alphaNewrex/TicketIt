package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FinalAvtivity extends AppCompatActivity {
    Button btnFinal;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_avtivity);
        btnFinal=findViewById(R.id.finalLogout);
        fAuth=FirebaseAuth.getInstance();


        btnFinal.setOnClickListener(v -> {
            fAuth.signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        });
    }
}