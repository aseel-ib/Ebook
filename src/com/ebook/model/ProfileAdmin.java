package com.ebook.model;

public class ProfileAdmin {

	private String sessionid;
	private String target;
	public ProfileAdmin(String sessionid, String target) {
		this.sessionid = sessionid;
		this.target = target;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "ProfileAdmin [sessionid=" + sessionid + ", target=" + target + "]";
	}
}
