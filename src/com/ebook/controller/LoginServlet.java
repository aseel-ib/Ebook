package com.ebook.controller;

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
import com.ebook.model.CustomSession;
import com.ebook.model.Login;
import com.ebook.model.UserName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//login by username and password
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();
		
		Gson gson = new GsonBuilder().create();
		Login getString = gson.fromJson(json, Login.class);

		if (LoginDAO.validate(getString.getUsername(), getString.getPassword())) {
			CustomSession.sessions.put(request.getSession().getId(), new UserName(getString.getUsername()));//register session if found
			json = new Gson().toJson(request.getSession().getId());
		} else json = new Gson().toJson("false");//do nothing if not found
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
}