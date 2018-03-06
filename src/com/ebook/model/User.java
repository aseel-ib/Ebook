package com.ebook.model;

public class User {
	private String target;

	public User(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "User [target=" + target + "]";
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	

}
