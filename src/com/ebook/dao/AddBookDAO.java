package com.ebook.dao;

import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.Book;
public class AddBookDAO {

	/**
	 * register new user
	 * @param json
	 * @param json.getName()
	 * @param json.getPrice()
	 * @param json.getDescription()
	 * @param json.getImgURL()
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	public static void add(Book json) 
	{
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into BOOK (NAME, PRICE, DESCRIPTION, IMGURL)"
					+ " VALUES('"+ json.getName() + "'," + json.getPrice()
					+ ",'" + json.getDescription() + "','" + json.getImgURL() +  "')");
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	
	public static void add(String name, float price, String desc, String imgURL) 
	{
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into BOOK (NAME, PRICE, DESCRIPTION, IMGURL)"
					+ " VALUES('"+ name + "'," + price
					+ ",'" + desc + "','" + imgURL +  "')");
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

}
