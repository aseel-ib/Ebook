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

import com.ebook.dao.CommentsDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.Reply;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/VerifyAdminServlet")
public class VerifyAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	//get all unverified reviews
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter writer = response.getWriter();
		String json = "";
		
		String sessid = request.getParameter("session_id");
		
		if (CustomSession.adminSessions.get(sessid) != null) json = new Gson().toJson(CommentsDAO.getUnverfiedReviews());//if admin session if found
		else json = new Gson().toJson("false");//do thing

		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
	//verify a comment by comment id
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();
		
		String sessid = request.getParameter("session_id");
		
		if (CustomSession.adminSessions.get(sessid) != null) {//if admin session is found
			Gson gson = new GsonBuilder().create();
			Reply getString = gson.fromJson(json, Reply.class);
			CommentsDAO.verifyComment(getString.getComment(), Integer.parseInt(getString.getTarget()));//verify
		} else json = new Gson().toJson("false");//do nothing
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
	

}
