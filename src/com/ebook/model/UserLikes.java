package com.ebook.model;

public class UserLikes {

	private String user;

	@Override
	public String toString() {
		return "UserLikes [user=" + user + "]";
	}

	public UserLikes(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	

}
