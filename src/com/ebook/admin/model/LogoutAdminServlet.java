package com.ebook.admin.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ebook.model.CustomSession;
import com.google.gson.Gson;

@WebServlet("/LogoutAdminServlet")
public class LogoutAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//admin logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sessid = request.getParameter("session_id");
		CustomSession.adminSessions.remove(sessid);//remove admin session
		response.addHeader("Access-Control-Allow-Origin", "*");
		String json = new Gson().toJson(true);
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
}