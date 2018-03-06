package com.ebook.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ebook.derby.DerbyUtil;
import com.ebook.model.Log;
public class LogDAO {
	/**
	 * 
	 * @return list of the all logs
	 */
	public static List<Log> getLogs()
	{
		DerbyUtil.createConnection();
		List<Log> list = new ArrayList<Log>();
		try {
			Statement stmt = null;
			stmt = DerbyUtil.conn.createStatement();
			ResultSet results = stmt.executeQuery("select O.order_id, O.username, B.name, B.price from orders O,book B, order_log OL WHERE O.order_id = OL.order_id AND OL.book_id = B.id");

			while (results.next()) {
				list.add(new Log(results.getInt(1), results.getString(2), results.getString(3), results.getFloat(4)));
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return list;
	}
}
