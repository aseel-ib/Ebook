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

import com.ebook.dao.AddBookDAO;
import com.ebook.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Post new book
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();

		Gson gson = new GsonBuilder().create();
		AddBookDAO.add(gson.fromJson(json, Book.class));
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write("true");
		writer.close();
	}

}
