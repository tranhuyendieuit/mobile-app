package com.example.demo.model.response;

public class ShoesResponseModel {

	private long id;

	private String name;

	private String imgJpg;

	private String imgPng;

	private String gender;

	private float price;

	private String description;

	private int rating;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
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

}
