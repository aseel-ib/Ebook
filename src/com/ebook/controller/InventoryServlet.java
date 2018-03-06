package com.ebook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebook.dao.InventoryDAO;
import com.ebook.model.CustomSession;
import com.google.gson.Gson;

@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get all the books in users inventory
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		String sessid = request.getParameter("session_id");
		String json = new Gson().toJson("false");
		if (CustomSession.sessions.get(sessid) != null)
			json = new Gson().toJson(InventoryDAO.getUserInventory(CustomSession.sessions.get(sessid).getUsername()));
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}

}
