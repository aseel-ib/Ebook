package com.ebook.admin.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ebook.dao.LoginDAO;
import com.ebook.dao.ProfileDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/UsersAdminServlet")
public class UsersAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//return all profiles
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String json = "";
		
		String sessid = request.getParameter("session_id");
		if (CustomSession.adminSessions.get(sessid) != null) json = new Gson().toJson(ProfileDAO.getUsersProfile());//if admin session if found
		else json = new Gson().toJson("false");//do nothing if not found
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
	
	//delete user from database
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();
		String sessid = request.getParameter("session_id");
		
		if (CustomSession.adminSessions.get(sessid) != null) {//if admin session is found
			Gson gson = new GsonBuilder().create();
			User getString = gson.fromJson(json, User.class);
			LoginDAO.deleteUser(getString.getTarget());
			json = new Gson().toJson("true");
		} else json = new Gson().toJson("false");
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}


}
