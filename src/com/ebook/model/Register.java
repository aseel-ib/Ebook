package com.ebook.model;

import com.google.gson.Gson;

public class Register {
	private String username;//
	private String address;
	private String email;//
	private String tel_number;//
	private String password;//
	private String nickName;//
	private String desc;//
	private String photoURL;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public Register(String username, String address, String email, String tel_number, String password, String nickName,
			String desc, String photoURL) {
		this.username = username;
		this.address = address;
		this.email = email;
		this.tel_number = tel_number;
		this.password = password;
		this.nickName = nickName;
		this.desc = desc;
		this.photoURL = photoURL;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



}
