package com.ebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebook.dao.BrowseEBookDAO;
import com.ebook.derby.DerbyUtil;
import com.ebook.model.CustomSession;
import com.google.gson.Gson;

@WebServlet("/browseEbook/*")
public class BrowseEBookController extends HttpServlet {//HTTP
	private static final long serialVersionUID = 1L;

	//get all books 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(DerbyUtil.fetchData == false) {
			DerbyUtil.createConnection();
			DerbyUtil.createTable();
			DerbyUtil.fetchData = true;
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		String path = request.getPathInfo();
		String sessid = request.getParameter("session_id");

		String json = null;
		if (path != null) {
			Integer id = getUserId(path);
			if (CustomSession.sessions.get(sessid) != null)
				json = new Gson().toJson(BrowseEBookDAO.get(id, CustomSession.sessions.get(sessid).getUsername()));
			else
				json = new Gson().toJson(BrowseEBookDAO.get(id, ""));
		}
		if (path == null)
			json = new Gson().toJson(BrowseEBookDAO.list());

		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
	
	//method to split bookID from the url
	public Integer getUserId(String path) {
		try {
			String[] urldata = path.split("/");
			return Integer.parseInt(urldata[1]);
		} catch (Exception e) {
		}
		return 0;
	}
}
