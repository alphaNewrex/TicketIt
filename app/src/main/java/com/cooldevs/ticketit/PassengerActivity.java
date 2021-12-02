package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cooldevs.ticketit.models.Transport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PassengerActivity extends AppCompatActivity {

    TextView date,city1,city2,price,selectedTransport;
    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerContactAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton btnOpenDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Transport transport= (Transport) i.getSerializableExtra("transport");
        setContentView(R.layout.activity_passenger);
        recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        selectedTransport=findViewById(R.id.textView4);
        price=findViewById(R.id.priceView);
        city1=findViewById(R.id.city1);
        city2=findViewById(R.id.city2);
        date=findViewById(R.id.dateFinalView);

        selectedTransport.setText(String.format("%s - %s", transport.name, transport.Id));
        price.setText(String.format("$ : %d", transport.fare));
        city1.setText(transport.startPoint);
        city2.setText(transport.endpoint);
        date.setText(String.format("%s  Time :%s", transport.date, transport.time));

        RecyclerView recyclerView = findViewById(R.id.recyclerContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, arrContacts);
        recyclerView.setAdapter(adapter);
        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(PassengerActivity.this);
                dialog.setContentView(R.layout.add_update_layout);
                EditText userName = dialog.findViewById(R.id.userName);
                EditText userAge = dialog.findViewById(R.id.userAge);
                Button btnAction = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = userName.getText().toString();
                        String age = userAge.getText().toString();
                        arrContacts.add(new ContactModel(name, age));
                        adapter.notifyItemChanged(arrContacts.size() - 1);
                        recyclerView.scrollToPosition(arrContacts.size()-1);
                        dialog.dismiss();
                    }

                });
                dialog.show();

            }
        });

    }
}