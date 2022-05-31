package com.example.nikestoreapp.model;

import java.io.Serializable;

public class Account implements Serializable {
    public int id;
    public String username;
    public String password;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
