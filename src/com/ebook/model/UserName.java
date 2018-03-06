package com.ebook.model;

public class UserName {
	@Override
	public String toString() {
		return "UserName [username=" + username + ", email=" + email + ", address=" + address + ", isAdmin=" + isAdmin
				+ ", tel_num=" + tel_num + ", nickName=" + nickName + ", desc=" + desc + ", photoURL=" + photoURL + "]";
	}

	private String username;
	private String email;
	private String address;
	private boolean isAdmin;
	private String tel_num;
	private String nickName;
	private String desc;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getTel_num() {
		return tel_num;
	}

	public void setTel_num(String tel_num) {
		this.tel_num = tel_num;
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

	public UserName(String username, String email, String address, String tel_num, String nickName, String desc,
			String photoURL) {
		this.username = username;
		this.email = email;
		this.address = address;
		//this.isAdmin = isAdmin;
		this.tel_num = tel_num;
		this.nickName = nickName;
		this.desc = desc;
		this.photoURL = photoURL;
	}
	public UserName(String username)
	{
		this.username = username;
	}

}
