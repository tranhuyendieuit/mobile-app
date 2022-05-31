package com.example.nikestoreapp.model;

import java.io.Serializable;

public class NewRelease implements Serializable {
    private int id;
    private String name;
    private String imgJpg;
    private String imgPng;
    private String gender;
    private int price;
    private String description;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public NewRelease(int id, String name, String imgJpg, String imgPng, String gender, int price, String description, int rating) {
        this.id = id;
        this.name = name;
        this.imgJpg = imgJpg;
        this.imgPng = imgPng;
        this.gender = gender;
        this.price = price;
        this.description = description;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "NewRelease{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgJpg='" + imgJpg + '\'' +
                ", imgPng='" + imgPng + '\'' +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
