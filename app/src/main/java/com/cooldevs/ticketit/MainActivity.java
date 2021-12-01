package com.cooldevs.ticketit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cooldevs.ticketit.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {
    Button logoutBTn,bookTicket,cancelBtn,adminBtn;
    TextView fname;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Boolean adminCheck;
    User u1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    logoutBTn= findViewById(R.id.logoutBtn);
    fAuth = FirebaseAuth.getInstance();
    bookTicket=findViewById(R.id.book_btn);
    cancelBtn=findViewById(R.id.cancel_btn);
    adminBtn = findViewById(R.id.admin_btn);
    fname=findViewById(R.id.fname_text);
    fStore=FirebaseFirestore.getInstance();
    userId= fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                u1 =new User(value.getString("fName"),value.getString("email"),value.getString("phoneNo"),value.getBoolean("admin"),value.getString("username"));


                fname.setText("Welcome! " + u1.fname);
//

//                if (u1.admin)
//                {
//                    adminBtn.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    adminBtn.setVisibility(View.GONE);
//                }
            }

        });
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
            finish();
        }


    });
    }
}