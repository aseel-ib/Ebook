package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.Comments;
import com.ebook.model.VerfiedReviews;
public class CommentsDAO {

	/**
	 * 
	 * @param target
	 * @return amount of comments by book id (target)
	 */
	public static int getBookComments(int target) {
		DerbyUtil.createConnection();
		ResultSet results = null;
		int temp = 0;
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select  COUNT(*) AS total from reviews where isVerfied = 1 AND book_id =" + target );

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
	 * @return list of comments by book id (target)
	 */
	public static List<Comments> getComments(int target) {
		DerbyUtil.createConnection();
		ResultSet results = null;
		List<Comments> lel = new ArrayList<Comments>();
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select username, reviewContent from reviews where book_id =" + target +"AND isVerfied = 1");

			while (results.next()) {
				lel.add(new Comments(results.getString(1), ProfileDAO.getUserProfilePic(results.getString(1)), results.getString(2)));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return lel;
	}
	/**
	 * post new comment to a book
	 * @param username
	 * @param target (book id)
	 * @param comment
	 */
	public static void postComment(String username, int target, String comment) {
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("INSERT INTO Reviews (book_id, username, reviewContent, isVerfied) VALUES(" + target + ",'" + username +"','" + comment +"', 0)");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	/**
	 * verify comment
	 * @param username
	 * @param target (book id)
	 */
	public static void verifyComment(String username, int target) {
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("UPDATE Reviews SET isVerfied = 1 WHERE username='"+ username +"' AND book_id="+target);
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	/**
	 * 
	 * @return list of un verfied reviews
	 */
	public static List<VerfiedReviews> getUnverfiedReviews() {
		DerbyUtil.createConnection();
		ResultSet results = null;
		List<VerfiedReviews> lel = new ArrayList<VerfiedReviews>();
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select book_id, username, reviewContent, isVerfied from Reviews where isVerfied = 0");

			while (results.next()) {
				System.out.println(results.getInt(4));
				lel.add(new VerfiedReviews(results.getInt(1), BrowseEBookDAO.getBookName(results.getInt(1)), results.getString(2), results.getString(3)));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return lel;
	}
	
	
}
