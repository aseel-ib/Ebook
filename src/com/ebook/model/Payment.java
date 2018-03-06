package com.ebook.model;

public class Payment {
	private String owner;
	private String number;
	private int month;
	private int year;
	private int cvv;
	private int target;
	public Payment(String owner, String number, int month, int year, int cvv, int target) {
		this.owner = owner;
		this.number = number;
		this.month = month;
		this.year = year;
		this.cvv = cvv;
		this.target = target;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return "Payment [owner=" + owner + ", number=" + number + ", month=" + month + ", year=" + year + ", cvv=" + cvv
				+ ", target=" + target + "]";
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

}
