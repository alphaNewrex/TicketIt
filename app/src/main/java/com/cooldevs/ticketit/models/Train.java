package com.cooldevs.ticketit.models;

public class Train extends Transport
{

    public Train(String name, String Id, String startPoint, String endpoint, long fare, String date, String time)
    {
        super(name, Id, startPoint, endpoint, fare, date, time);
    }
}
