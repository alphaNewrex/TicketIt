package com.cooldevs.ticketit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.cooldevs.ticketit.models.Bus;
import com.cooldevs.ticketit.models.Train;

import java.io.Serializable;
import java.util.Calendar;

public class  TrainActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    String arrival,destination,date;
    TextView dateText,city11,city12,city21,city22,date1,date2,price1,price2;
    Button findB,bookTrain1,bookTrain2;

    Train t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        dateText =findViewById(R.id.showDate);
        findB=findViewById(R.id.button2);
        city11=findViewById(R.id.city1_1);
        city12=findViewById(R.id.city1_2);
        city21=findViewById(R.id.city2_1);
        city22=findViewById(R.id.city2_2);
        date1=findViewById(R.id.dateView1);
        date2=findViewById(R.id.dateView2);
        price1=findViewById(R.id.priceView1);
        price2= findViewById(R.id.priceView2);
        bookTrain1=findViewById(R.id.button6);
        bookTrain2=findViewById(R.id.button8);

        Spinner arrivalSpinner =findViewById(R.id.arrivalSpinner);
        Spinner destinationSpinner =findViewById(R.id.departureSpinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(TrainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.TrainArrival));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrivalSpinner.setAdapter(myAdapter);

        destinationSpinner.setAdapter(myAdapter);




        findViewById((R.id.pickDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        bookTrain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption1();
            }
        });
        bookTrain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption2();
            }
        });

        findB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.listView).setVisibility(View.VISIBLE);
                arrival= arrivalSpinner.getSelectedItem().toString();
                destination= destinationSpinner.getSelectedItem().toString();
                city11.setText(arrival);
                city12.setText(destination);
                city21.setText(arrival);
                city22.setText(destination);
                date1.setText(date +"\nTime : 7 A.M");
                date2.setText(date +"\nTime : 6 P.M");

                price1.setText("$ "+210);
                price2.setText("$ "+320);
            }
        });
    }

    private void  showDatePicker()
    {
        DatePickerDialog  datePickerDialog= new DatePickerDialog(
                this, this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

//    ListView myListView = findViewById(R.id.myListView);

    public void selectOption1(){
        t = new Train("bus","Bus 621",arrival,destination,100,date,"9 A.M");
        Intent i = new Intent(this,PassengerActivity.class);
        i.putExtra("transport", (Serializable) t);
        startActivity(i);
    }
    public void selectOption2(){
        t = new Train("Bus","Bus 220",arrival,destination,120,date,"10 P.M");
        Intent i = new Intent(this,PassengerActivity.class);
        i.putExtra("transport", (Serializable) t);
        startActivity(i);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date =month +"/"+ dayOfMonth+"/" + year;
        dateText.setText(date);
    }
}