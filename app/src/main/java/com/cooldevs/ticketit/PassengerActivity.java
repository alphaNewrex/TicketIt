package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cooldevs.ticketit.models.Transport;

public class PassengerActivity extends AppCompatActivity {

    TextView date,city1,city2,price,selectedTransport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        Transport transport= (Transport) i.getSerializableExtra("transport");
        setContentView(R.layout.activity_passenger);
        selectedTransport=findViewById(R.id.textView11);
        price=findViewById(R.id.priceView);
        city1=findViewById(R.id.city1);
        city2=findViewById(R.id.city2);
        date=findViewById(R.id.dateView);

        selectedTransport.setText(transport.name + " - "+ transport.Id);
        price.setText("$ : " +transport.fare);
        city1.setText(transport.startPoint);
        city2.setText(transport.endpoint);
        date.setText(transport.date+ "Time :" +transport.time);


    }
}