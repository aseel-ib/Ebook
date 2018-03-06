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

import com.ebook.dao.InventoryDAO;
import com.ebook.dao.ProfileDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.Payment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//buy new book
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if (br != null) json = br.readLine();

		String sessid = request.getParameter("session_id");
		if (CustomSession.sessions.get(sessid) != null) {

			int inventory_id = ProfileDAO.getUserInventoryID(CustomSession.sessions.get(sessid).getUsername());
			
			Gson gson = new GsonBuilder().create();
			Payment getString = gson.fromJson(json, Payment.class);
			
			if(InventoryDAO.isFound(CustomSession.sessions.get(sessid).getUsername(), getString.getTarget()) == 0) //if book not found
			{
				InventoryDAO.insertUserInventoryID(inventory_id, getString.getTarget(), CustomSession.sessions.get(sessid).getUsername());//insert to inventory
				json = new Gson().toJson("true");//return true 
			} else json = new Gson().toJson("false");//if book found, do nothing
		} else json = new Gson().toJson("false");//if session not found, do nothing
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}

}
