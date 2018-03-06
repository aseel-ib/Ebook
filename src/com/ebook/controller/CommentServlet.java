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

import com.ebook.dao.CommentsDAO;
import com.ebook.dao.InventoryDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.Reply;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get all the comments by book ID
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		String target = request.getParameter("target");

		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(new Gson().toJson(CommentsDAO.getComments(Integer.parseInt(target))));
		writer.close();
	}

	//post a new comment
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();
		
		String sessid = request.getParameter("session_id");

		if (CustomSession.sessions.get(sessid) != null) {
			Gson gson = new GsonBuilder().create();
			Reply getString = gson.fromJson(json, Reply.class);

			String target = getString.getTarget();
			String comment = getString.getComment();
			
			if(InventoryDAO.isFound(CustomSession.sessions.get(sessid).getUsername(), Integer.parseInt(target)) == 1)//if book is found
			{
				CommentsDAO.postComment(CustomSession.sessions.get(sessid).getUsername(), Integer.parseInt(target), comment);
				json = new Gson().toJson("true");
			} else json = new Gson().toJson("buy");//book not found, error msg (buy)
		} else json = new Gson().toJson("false");//session not found, do nothing
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}

}
