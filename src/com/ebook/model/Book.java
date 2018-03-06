package com.ebook.model;

public class Book {
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", imgURL="
				+ imgURL + "]";
	}
	
	private int id;
	private String name;
	private float price;
	private String description;
	private String imgURL;
	
	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param description
	 * @param imgURL
	 */
	public Book(int id, String name, float price, String description, String imgURL) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imgURL = imgURL;
	}
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
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

}
