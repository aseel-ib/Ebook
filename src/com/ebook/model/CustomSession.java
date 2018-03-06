package com.ebook.model;

import java.util.HashMap;
import java.util.Map;

public class CustomSession {
	public static Map<String, UserName> sessions = new HashMap<String, UserName>();//store all normal users sessions
	public static Map<String, UserName> adminSessions = new HashMap<String, UserName>();//store all admin users sessions

}
