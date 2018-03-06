package com.ebook.model;

public class VerfiedReviews {

	private int reviewID;
	private String bookName;
	private String username;
	private String review;
	public VerfiedReviews(int reviewID, String bookName, String username, String review) {
		this.reviewID = reviewID;
		this.bookName = bookName;
		this.username = username;
		this.review = review;
	}
	@Override
	public String toString() {
		return "VerfiedReviews [reviewID=" + reviewID + ", bookName=" + bookName + ", username=" + username
				+ ", review=" + review + "]";
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
}
