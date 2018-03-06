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

import com.ebook.dao.ProfileDAO;
import com.ebook.model.CustomSession;
import com.ebook.model.ProfileAdmin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/ProfileAdminServlet")
public class ProfileAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//get user info by username target
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		Gson gson = new GsonBuilder().create();
		ProfileAdmin getString = gson.fromJson(json, ProfileAdmin.class);
		
		String sessid = getString.getSessionid();
		String target = getString.getTarget();
		String res;
		res = new Gson().toJson("false");
		
		if (CustomSession.adminSessions.get(sessid) != null) {//if admin session is found
			System.out.println(sessid + " " + target);
			res = new Gson().toJson(ProfileDAO.getUserInfo(target));//profile info
		}
		
		PrintWriter writer = response.getWriter();
		response.setContentType("application/json");
		writer.write(res);
		writer.close();
	}
}
