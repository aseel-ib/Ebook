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

import com.ebook.dao.RegisterDAO;
import com.ebook.model.Register;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//register new user
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();

		Gson gson = new GsonBuilder().create();
		if(RegisterDAO.registerUser(gson.fromJson(json, Register.class))) json = new Gson().toJson("true");//if success, then register
		else															  json = new Gson().toJson("false");//do nothing
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}

}
