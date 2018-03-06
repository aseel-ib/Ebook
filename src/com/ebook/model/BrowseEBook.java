package com.ebook.model;

import java.util.List;

import com.google.gson.Gson;

public class BrowseEBook {
	private int id;
	private String name;
	private String imageURL;
	private float price;
	private String desc;
	private int likesAmount;
	private int reviewsAmount;
	private List<UserLikes> list;
	private boolean isLiked;
	private String targetURL;
	public String getTargetURL() {
		return targetURL;
	}
	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getLikesAmount() {
		return likesAmount;
	}

	public void setLikesAmount(int likesAmount) {
		this.likesAmount = likesAmount;
	}

	public BrowseEBook() {
		super();
	}

	public BrowseEBook(Gson json) {
		
	}

	public BrowseEBook(int id, String name, String imageURL, float price, String desc, int likesAmount, int reviewsAmount, List<UserLikes> list, boolean isLiked) {
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
		this.desc = desc;
		this.likesAmount = likesAmount;
		this.reviewsAmount = reviewsAmount;
		this.list = list;
		this.isLiked = isLiked;
	}

	public BrowseEBook(String name) {
		this.name = name;
	}

	public int getReviewsAmount() {
		return reviewsAmount;
	}

	public void setReviewsAmount(int reviewsAmount) {
		this.reviewsAmount = reviewsAmount;
	}

	/**
	 * @param id
	 * @param name
	 * @param imageURL
	 * @param price
	 * @param desc
	 * @param likesAmount
	 * @param reviewsAmount
	 * @param list
	 * @param isLiked
	 * @param targetURL
	 */
	public BrowseEBook(int id, String name, String imageURL, float price, String desc, int likesAmount,
			int reviewsAmount, List<UserLikes> list, boolean isLiked, String targetURL) {
		this.id = id;
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
		this.desc = desc;
		this.likesAmount = likesAmount;
		this.reviewsAmount = reviewsAmount;
		this.list = list;
		this.isLiked = isLiked;
		this.targetURL = targetURL;
	}
	@Override
	public String toString() {
		return "BrowseEBook [id=" + id + ", name=" + name + ", imageURL=" + imageURL + ", price=" + price + ", desc="
				+ desc + ", likesAmount=" + likesAmount + ", reviewsAmount=" + reviewsAmount + ", list=" + list
				+ ", isLiked=" + isLiked + ", targetURL=" + targetURL + "]";
	}
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}



}
