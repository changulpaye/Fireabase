package com.param.firebase.database.models;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by pchangul on 8/25/2016.
 */
@IgnoreExtraProperties
public class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}