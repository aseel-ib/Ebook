package com.ebook.model;

public class Log {
	private int orderID;
	private String username;
	private String bookName;
	private float price;
	@Override
	public String toString() {
		return "Log [orderID=" + orderID + ", username=" + username + ", bookName=" + bookName + ", price=" + price
				+ "]";
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Log(int orderID, String username, String bookName, float price) {
		this.orderID = orderID;
		this.username = username;
		this.bookName = bookName;
		this.price = price;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
}
