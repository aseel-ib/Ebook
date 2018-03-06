package com.ebook.model;

public class Comments {
	private String username;
	private String imageURL;
	private String reviewContent;
	public Comments() {
		super();
		
	}
	public Comments(String username, String imageURL, String reviewContent) {
		this.username = username;
		this.imageURL = imageURL;
		this.reviewContent = reviewContent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

}
