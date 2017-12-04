package com.example.plmakal2.myapplication100;


import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by PLMAKAL2 on 2017-11-12.
 */

@IgnoreExtraProperties
public class User {

    public String email;
    public boolean superuser;
    public int counter;
    public String name;


    public User() {
    }

    public User(String email, boolean superuser, int counter, String name) {
        this.email = email;
        this.superuser = superuser;
        this.counter = counter;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public boolean getSuperuser() {
        return superuser;
    }

    public String getName() {
        return name;
    }


}