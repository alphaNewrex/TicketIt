package com.cooldevs.ticketit.models;

    public abstract class Transport {
        public String name;
        public String Id;
        public String startPoint;
        public String endpoint;
        public long fare;
        public String date;
        public String time;
        Transport(String name, String Id, String startpoint, String endpoint, long fare, String date, String time)
        {
            this.name=name;
            this.Id=Id;
            this.startPoint=startpoint;
            this.endpoint=endpoint;
            this.fare=fare;
            this.date=date;
            this.time=time;
        }
    }


