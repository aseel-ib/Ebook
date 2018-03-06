package com.ebook.model;

public class Like {
	private int target;

	@Override
	public String toString() {
		return "Like [target=" + target + "]";
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

}
