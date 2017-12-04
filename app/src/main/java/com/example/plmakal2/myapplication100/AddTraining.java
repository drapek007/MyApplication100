package com.example.plmakal2.myapplication100;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by PLMAKAL2 on 2017-11-18.
 */

public class AddTraining {
    public String id;
    public String name;
    public String place;
    public String date;
    public int time;
    public int quantity;
    public String parentname;

    public AddTraining() {
    }

    public AddTraining(String place, String date, int time, int quantity, String id, String name, String parentname) {
        this.id=id;
        this.place = place;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.name = name;
        this.parentname = parentname;
    }

    public String getPlace() {
        return place;
    }

    public String getParentname() {return  parentname;}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getTime() { return time; }

    public int getQuantity() {
        return quantity;
    }
}

