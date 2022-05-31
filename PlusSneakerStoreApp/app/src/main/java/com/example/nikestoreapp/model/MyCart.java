package com.example.nikestoreapp.model;

public class MyCart {
    private String urlImg_png;
    private String name;
    private int price;
    private int size;
    private int amount;

    public MyCart(String urlImg_png, String name, int price, int size, int amount) {
        this.urlImg_png = urlImg_png;
        this.name = name;
        this.price = price;
        this.size = size;
        this.amount = amount;
    }

    public String getUrlImg_png() {
        return urlImg_png;
    }

    public void setUrlImg_png(String urlImg_png) {
        this.urlImg_png = urlImg_png;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
