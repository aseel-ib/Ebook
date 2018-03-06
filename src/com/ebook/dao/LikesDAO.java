package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.UserLikes;

public class LikesDAO {

	/**
	 * 
	 * @param target
	 * @return get books likes (amount) by book id
	 */
	public static int getBookLikes(int target) {
		DerbyUtil.createConnection();
		ResultSet results = null;
		int temp = 0;
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select  COUNT(*) AS total from Likes where book_id =" + target);

			while (results.next()) {
				temp = results.getInt("total");
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
	 * @param target
	 * @return list of users who liked a book
	 */
	public static List<UserLikes> getBookUsersLikes(int target) {
		DerbyUtil.createConnection();
		ResultSet results = null;
		List<UserLikes> list = new ArrayList<UserLikes>();
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select username from Likes where book_id =" + target);

			while (results.next()) {
				list.add(new UserLikes(results.getString(1)));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 * @param username
	 * @param target
	 * @return check if username liked the book
	 */
	public static boolean getMyLikes(String username, int target) 
	{
		DerbyUtil.createConnection();
		ResultSet results = null;
		boolean isLiked = false;
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("SELECT * from LIKES where username = '" +username +"' AND book_id = "+target);

			while (results.next()) {
				isLiked = true;
				break;
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return isLiked;
	}
	/**
	 * like a book 
	 * @param username
	 * @param target
	 */
	public static void LikeBook(String username, int target)
	{
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("insert into LIKES (book_id, USERNAME) VALUES(" + target +",'"+ username + "')");
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	/**
	 * unlike a book
	 * @param username
	 * @param target
	 */
	public static void UnlikeBook(String username, int target)
	{
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("delete from LIKES WHERE username='"+username+"' AND book_id =" + target);
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
}
