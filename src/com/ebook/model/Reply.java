package com.ebook.model;

public class Reply {
	private String target;
	private String comment;
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Reply(String target, String comment) {
		this.target = target;
		this.comment = comment;
	}
	
	

}
