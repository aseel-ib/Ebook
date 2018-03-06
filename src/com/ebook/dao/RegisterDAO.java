package com.ebook.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.Register;

public class RegisterDAO {
	
	public static boolean registerUser(Register json)
	{
		DerbyUtil.createConnection();
		boolean bRes = false;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into login (username, password, isAdmin) VALUES('"+ json.getUsername() +"','" + json.getPassword() +"'," + "0)");
			stmt.execute("insert into Users (username, email, address, tel_num, nick_name, description, photoURL)"
					+ " VALUES('"+ json.getUsername()  +"','" + json.getEmail() + "','" + json.getAddress()
					+ "','" + json.getTel_number() + "','" + json.getNickName() + "','"
					+ json.getDesc() + "','" + json.getPhotoURL() + "')");
			
			bRes = true;
			
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			bRes = false;
		}
		return bRes;
	}
	
	public static boolean registerUser(String username, String password, String email, String address, String tel_num, String nickName, String desc, String photoURL)
	{
		DerbyUtil.createConnection();
		boolean bRes = false;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into login (username, password, isAdmin) VALUES('"+ username +"','" + password +"'," + "0)");
			stmt.execute("insert into Users (username, email, address, tel_num, nick_name, description, photoURL)"
					+ " VALUES('"+ username  +"','" + email + "','" + address
					+ "','" + tel_num + "','" + nickName + "','"
					+ desc + "','" + photoURL + "')");
			
			bRes = true;
			
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			bRes = false;
		}
		return bRes;
	}
	
	public static boolean registerAdmin(String username, String password, String email, String address, String tel_num, String nickName, String desc, String photoURL)
	{
		DerbyUtil.createConnection();
		boolean bRes = false;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into login (username, password, isAdmin) VALUES('"+ username +"','" + password +"'," + "1)");
			stmt.execute("insert into Users (username, email, address, tel_num, nick_name, description, photoURL)"
					+ " VALUES('"+ username  +"','" + email + "','" + address
					+ "','" + tel_num + "','" + nickName + "','"
					+ desc + "','" + photoURL + "')");
			
			bRes = true;
			
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
			bRes = false;
		}
		return bRes;
	}

}
