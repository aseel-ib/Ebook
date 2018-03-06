package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.derby.DerbyUtil;

public class LoginDAO {
	/**
	 * 
	 * @param username
	 * @param password
	 * @return validate if user is found in database 
	 */
	public static boolean validate(String username, String password) {
		DerbyUtil.createConnection();
		int count = 0;
		try {
			DerbyUtil.stmt = DerbyUtil.conn.createStatement();
			ResultSet results = DerbyUtil.stmt.executeQuery("select * from login where username = '" + username + "' AND password ='" + password+"'");
			while (results.next()) {
				count++;
			}
			results.close();
			DerbyUtil.stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return count == 1;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return validate if admin is found in the database
	 */
	public static boolean validateAdmin(String username, String password) {
		DerbyUtil.createConnection();
		int count = 0;
		try {
			DerbyUtil.stmt = DerbyUtil.conn.createStatement();
			ResultSet results = DerbyUtil.stmt.executeQuery("select * from login where username = '" + username + "' AND password ='" + password+"' AND isAdmin=1");
			while (results.next()) {
				System.out.println(results.getString(1));
				System.out.println(results.getString(2));
				System.out.println("LoginDAO.validateAdmin()" +  results.getInt(3)) ;
				count++;
			}
			results.close();
			DerbyUtil.stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return count == 1;
	}
	
	/**
	 * delete user from the database (admin feature)
	 * @param username
	 */
	public static void deleteUser(String username)
	{
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("delete from login WHERE username='"+username+"'");
			stmt.execute("delete from users WHERE username='"+username+"'");
			stmt.execute("delete from reviews WHERE username='"+username+"'");
			stmt.execute("delete from likes WHERE username='"+username+"'");
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
}
