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
import com.ebook.dao.ProfileDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.UserName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/ProfileServlet/*")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get username profile
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		String sessid = request.getParameter("session_id");
		String json = new Gson().toJson("false");
		if (CustomSession.sessions.get(sessid) != null) {//if session not null then look up for the profile
			json = new Gson().toJson(ProfileDAO.getUserInfo(CustomSession.sessions.get(sessid).getUsername()));
		}
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}
	//post (update) profile
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) json = br.readLine();
		
		String sessid = request.getParameter("session_id");

		if (CustomSession.sessions.get(sessid) != null) {
			Gson gson = new GsonBuilder().create();
			ProfileDAO.updateUserInfo(gson.fromJson(json, UserName.class));//if session found, update
			json = new Gson().toJson("true");
		} else json = new Gson().toJson("false");//if session not found, do nothing
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(json);
		writer.close();
	}

}