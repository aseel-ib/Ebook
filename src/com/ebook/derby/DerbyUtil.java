package com.ebook.derby;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.derby.drda.NetworkServerControl;

import com.ebook.dao.AddBookDAO;
import com.ebook.dao.RegisterDAO;

public class DerbyUtil {
	public static Connection conn = null;
	public static Statement stmt = null;
	public static boolean isRunning = false;
	public static boolean fetchData = false;
	public static void createTable()
	{
		try {
		Statement stmt = null;
		stmt = DerbyUtil.conn.createStatement();
		stmt.execute("CREATE TABLE LOGIN (USERNAME VARCHAR(45) NOT NULL, PASSWORD VARCHAR(45) NOT NULL, ISADMIN INT DEFAULT 0, PRIMARY KEY(USERNAME))");
		stmt.execute("CREATE TABLE USERS (USERNAME VARCHAR(45) NOT NULL, EMAIL VARCHAR(45) , ADDRESS VARCHAR(45) , TEL_NUM VARCHAR(45), NICK_NAME VARCHAR(45), DESCRIPTION VARCHAR(1000), photoURL VARCHAR(1000), inventory_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), PRIMARY KEY(USERNAME))");
		stmt.execute("CREATE TABLE INVENTORY (inventory_id INT NOT NULL, USERNAME VARCHAR (45) NOT NULL, BOOK_ID INT NOT NULL)");
		stmt.execute("CREATE TABLE BOOK (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME VARCHAR(1000), PRICE FLOAT, DESCRIPTION VARCHAR(1000), IMGURL VARCHAR(1000), PRIMARY KEY (ID))");
		stmt.execute("CREATE TABLE REVIEWS (BOOK_ID INT NOT NULL, USERNAME VARCHAR(45), REVIEWCONTENT VARCHAR(1000), ISVERFIED INT DEFAULT 0)");
		stmt.execute("CREATE TABLE LIKES (BOOK_ID INT NOT NULL, USERNAME VARCHAR(45))");
		stmt.execute("CREATE TABLE ORDERS (ORDER_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), USERNAME VARCHAR(45) NOT NULL, PRIMARY KEY (ORDER_ID))");
		stmt.execute("CREATE TABLE ORDER_LOG (ORDER_ID INT NOT NULL, BOOK_ID INT NOT NULL)");
		
		RegisterDAO.registerAdmin("admin", "Passw0rd", "admin@gmail.com", "USA", "999999", "ADMIN", "ADMIN MANAGER", "http://www.thepixelfarm.co.uk/wp-content/uploads/2016/05/admin_v01D_support.png");

		for(int i = 0; i<20; i++)
			RegisterDAO.registerUser("abc"+i, "abc"+i, "abc"+i+"@gmail.com", "Nazareth", "054804119"+i, "Cool Name"+i, "What a description"+i, "https://i.ytimg.com/vi/SfLV8hD7zX4/maxresdefault.jpg");
	
		AddBookDAO.add("Danny Again_ a Project Gutenberg eBook", 50, 
				"This work has been selected by scholars as being culturally important, and is part of the knowledge base of "
				+ "civilization as we know it. This work was reproduced from the original artifact, and remains as true to the original"
				+ " work as possible", "https://www.gutenberg.org/cache/epub/56658/pg56658.cover.medium.jpg");
		AddBookDAO.add("Quaint Korea", 15, "This work has been selected by scholars as being culturally important, and is part of the knowledge base of civilization as we know it. This work was reproduced from the original artifact, and remains as true to the original work as possible", "http://t2.gstatic.com/images?q=tbn:ANd9GcTRU88KEvSP6--WTZGmq8RTNFJu7ei6jPn9T3nLI8rKsxI5nahf");
		AddBookDAO.add("The Project Gutenberg eBook of His Excellency [Son Exc. Eugène Rougon], by Émile Zola.", 20, "His Excellency (French: Son Excellence Eugene Rougon) - From Zolas Rougon-Macquart Series. Son Excellence Eugene Rougon is the one existing French novel which gives the reader a fair general idea of ...", "https://www.gutenberg.org/cache/epub/56654/pg56654.cover.medium.jpg");
		AddBookDAO.add("The Project Gutenberg eBook of The German Fleet, by Archibald Hurd.", 70, "Many of the earliest books, particularly those dating back to the 1900s and before, are now extremely scarce and increasingly expensive.", "https://www.gutenberg.org/cache/epub/56653/pg56653.cover.medium.jpg");
		AddBookDAO.add("Kitty of the Roses, by Ralph Henry Barbour—A Project Gutenberg eBook.", 10, "This work has been selected by scholars as being culturally important, and is part of the knowledge base of civilization as we know it. This work was reproduced from the original artifact, and remains as true to the original work as possible", "https://www.gutenberg.org/cache/epub/56652/pg56652.cover.medium.jpg");
		} catch (SQLException sqlExcept) {
		sqlExcept.printStackTrace();
		}
	}
	//start derby connection
	public static void createConnection() {
			if(DerbyUtil.isRunning == false) {
				NetworkServerControl server = null;
				try {
					server = new NetworkServerControl (InetAddress.getByName("localhost"),1527);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					server.start(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DerbyUtil.isRunning = true;
			}
			if(DerbyUtil.conn == null) {
				String nsURL="jdbc:derby://localhost:1527/C:/myDB;create=true";  
				java.util.Properties props = new java.util.Properties();
				props.put("user","me");
				props.put("password","mine");
				try {
					Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					DerbyUtil.conn = DriverManager.getConnection(nsURL, props);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
}