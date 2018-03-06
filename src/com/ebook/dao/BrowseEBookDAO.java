package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ebook.derby.DerbyUtil;
import com.ebook.model.BrowseEBook;

public class BrowseEBookDAO {

	/**
	 * 
	 * @param id
	 * @param username 
	 * @return object of Book with all the info
	 */
	public static BrowseEBook get(Integer id, String username) {
		DerbyUtil.createConnection();
		BrowseEBook eBookMap = null;
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from book where id =" + id);

			while (results.next()) {
				
				eBookMap = new BrowseEBook(results.getInt(1), results.getString(2), results.getString(5),
						results.getFloat(3), results.getString(4), LikesDAO.getBookLikes(results.getInt(1)),
						CommentsDAO.getBookComments(results.getInt(1)), LikesDAO.getBookUsersLikes(results.getInt(1)),
						LikesDAO.getMyLikes(username, results.getInt(1)), results.getString(2));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return eBookMap;
	}
	/**
	 * 
	 * @return list of all books in the database
	 */
	public static List<BrowseEBook> list() {
		DerbyUtil.createConnection();
		List<BrowseEBook> eBookMap = new ArrayList<BrowseEBook>();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select * from book");

			while (results.next()) {
				eBookMap.add(new BrowseEBook(results.getInt(1), results.getString(2), results.getString(5),
						results.getFloat(3), results.getString(4), LikesDAO.getBookLikes(results.getInt(1)), 
						CommentsDAO.getBookComments(results.getInt(1)), LikesDAO.getBookUsersLikes(results.getInt(1)),
						LikesDAO.getMyLikes(results.getString(2), results.getInt(1))));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return eBookMap;
	}
	/**
	 * 
	 * @param id
	 * @return book name
	 */
	public static String getBookName(int id)
	{
		DerbyUtil.createConnection();
		String temp = "";
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select name from book WHERE id = "+id);

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

}
