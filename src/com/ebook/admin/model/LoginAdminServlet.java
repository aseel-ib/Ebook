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
import com.ebook.model.CustomSession;
import com.ebook.model.Login;
import com.ebook.model.UserName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//login by username grade
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();

		Gson gson = new GsonBuilder().create();
		Login getString = gson.fromJson(json, Login.class);
		if (LoginDAO.validateAdmin(getString.getUsername(), getString.getPassword())) {//validate if username is found
			CustomSession.adminSessions.put(request.getSession().getId(), new UserName(getString.getUsername()));//store session
			json = new Gson().toJson(request.getSession().getId());
		} else
			json = new Gson().toJson("false");//if not found, do nothing

		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
}