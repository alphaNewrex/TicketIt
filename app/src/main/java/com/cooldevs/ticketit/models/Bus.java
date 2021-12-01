package com.cooldevs.ticketit.models;

public class Bus extends Transport
{

    public Bus(String name, String Id, String startPoint, String endpoint, long fare, String date, String time)
    {
        super(name, Id, startPoint, endpoint, fare, date, time);
    }
}
