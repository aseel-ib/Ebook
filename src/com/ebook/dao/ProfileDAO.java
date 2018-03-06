package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.UserName;

public class ProfileDAO {

	/**
	 * 
	 * @param username
	 * @return user inventory id
	 */
	public static int getUserInventoryID(String username) {
		DerbyUtil.createConnection();
		int temp = 0;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select inventory_id from Users where username = '" + username + "'");
			while (results.next()) {
				temp = results.getInt(1);	
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return temp;
	}
	/**
	 * 
	 * @param username
	 * @return return user info (name, email, address, tel_num, nickname, desc, photoURL)
	 */
	public static UserName getUserInfo(String username) {
		DerbyUtil.createConnection();
		UserName temp = null;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from Users where username = '" + username + "'");
			while (results.next()) {
				temp = new UserName(results.getString(1), results.getString(2), results.getString(3), results.getString(4), 
						results.getString(5), results.getString(6), results.getString(7));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return temp;
	}
	/**
	 * 
	 * @param username
	 * @return user profile link
	 */
	public static String getUserProfilePic(String username) {
		DerbyUtil.createConnection();
		String temp = "";
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select photoURL from Users where username = '" + username + "'");
			while (results.next()) {
				temp = results.getString(1);
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return temp;
	}
	
	/**
	 * update user info(email, address, tel_num, nickName, description, photoURL)
	 * @param user
	 */
	public static void updateUserInfo(UserName user) {
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("UPDATE Users SET email='"+ user.getEmail() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.execute("UPDATE Users SET address='"+ user.getAddress() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.execute("UPDATE Users SET tel_num='"+ user.getTel_num() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.execute("UPDATE Users SET nick_name='"+ user.getNickName() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.execute("UPDATE Users SET description='"+ user.getDesc() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.execute("UPDATE Users SET photoURL='"+ user.getPhotoURL() +"' WHERE username ='" + user.getUsername() +"'");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	/**
	 * @return list of all the users profile (admin feature)
	 */
	public static List<UserName> getUsersProfile() {
		DerbyUtil.createConnection();
		List<UserName> list = new ArrayList<UserName>();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from Users");
			while (results.next()) {
				list.add(new UserName(results.getString(1), results.getString(2), results.getString(3), results.getString(4), 
						results.getString(5), results.getString(6), results.getString(7)));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return list;
	}
	
}
