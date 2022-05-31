package com.example.nikestoreapp.model;

import java.io.Serializable;

public class BestSeller implements Serializable {
    private int id;
    private String imgJpg;
    private String imgPng;
    private String name;
    private int price;
    private int rating;
    private String type;

    @Override
    public String toString() {
        return "BestSeller{" +
                "id=" + id +
                ", imgJpg='" + imgJpg + '\'' +
                ", imgPng='" + imgPng + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgJpg() {
        return imgJpg;
    }

    public void setImgJpg(String imgJpg) {
        this.imgJpg = imgJpg;
    }

    public String getImgPng() {
        return imgPng;
    }

    public void setImgPng(String imgPng) {
        this.imgPng = imgPng;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BestSeller(int id, String imgJpg, String imgPng, String name, int price, int rating, String type) {
        this.id = id;
        this.imgJpg = imgJpg;
        this.imgPng = imgPng;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.type = type;
    }
}
