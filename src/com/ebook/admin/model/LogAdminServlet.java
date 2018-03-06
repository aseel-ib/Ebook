package com.ebook.admin.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebook.dao.LogDAO;
import com.ebook.model.CustomSession;
import com.google.gson.Gson;

@WebServlet("/LogAdminServlet")
public class LogAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//return log
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		
		String sessid = request.getParameter("session_id");
		if (CustomSession.adminSessions.get(sessid) != null) json = new Gson().toJson(LogDAO.getLogs());//if admin session is found
		else json = new Gson().toJson("false");//if admin session not found, do nothing
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
}

