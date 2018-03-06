package com.ebook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.BrowseEBook;

public class InventoryDAO {
	/**
	 * insert new book into the inventory
	 * @param inventory_id
	 * @param target
	 * @param username
	 */
	public static void insertUserInventoryID(int inventory_id ,int target, String username) {
		DerbyUtil.createConnection();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			stmt.execute("INSERT INTO ORDERS (USERNAME) VALUES('" + username + "')");
			stmt.execute("INSERT INTO ORDER_LOG (ORDER_ID, BOOK_ID) VALUES (" + getOrder(username) + "," + target +")");
			stmt.execute("insert into INVENTORY (inventory_id, book_id, username) VALUES(" + inventory_id +","+ target + ",'" + username + "')");
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}
	/**
	 * @param username
	 * @return order id by username
	 */
	public static int getOrder(String username) {
		DerbyUtil.createConnection();
		int id = 0;
		try {
			DerbyUtil.stmt = DerbyUtil.conn.createStatement();
			ResultSet results = DerbyUtil.stmt.executeQuery("SELECT order_id from ORDERS WHERE username='" + username +"'");
			while (results.next()) {
				id = results.getInt(1);
				break;
			}
			results.close();
			DerbyUtil.stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return id;
	}
	/**
	 * 
	 * @param username
	 * @return get user inventory by username
	 */
	public static List<BrowseEBook> getUserInventory(String username) {
		DerbyUtil.createConnection();
		ResultSet results = null;
		List<BrowseEBook> myInventory = new ArrayList<BrowseEBook>();
		try {
		    Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			results = stmt.executeQuery("select book_id from INVENTORY, users where users.username = '"+ username +"' AND users.inventory_id = inventory.inventory_id");
			
			while(results.next())
			{
				myInventory.add(BrowseEBookDAO.get(results.getInt(1), username));
			}
			
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return myInventory;
	}
	/**
	 * 
	 * @param username
	 * @param book_id
	 * @return check if book is found in the inventory
	 */
	public static int isFound(String username, int book_id) {
		DerbyUtil.createConnection();
		int count = 0;
		try {
			DerbyUtil.stmt = DerbyUtil.conn.createStatement();
			ResultSet results = DerbyUtil.stmt.executeQuery("SELECT * from INVENTORY WHERE book_id=" + book_id + " AND username = '" + username +"'");
			while (results.next()) {
				count++;
			}
			results.close();
			DerbyUtil.stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return count;
	}
}
