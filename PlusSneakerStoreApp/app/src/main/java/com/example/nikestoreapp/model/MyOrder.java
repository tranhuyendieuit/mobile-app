package com.example.nikestoreapp.model;

public class MyOrder {
    public String email;
    public String ordernumber;
    public String date;
    public String total;

    public MyOrder(String email, String ordernumber, String date, String total) {
        this.email = email;
        this.ordernumber = ordernumber;
        this.date = date;
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
