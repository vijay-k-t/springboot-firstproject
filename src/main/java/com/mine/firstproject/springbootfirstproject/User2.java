package com.mine.firstproject.springbootfirstproject;

import java.io.Serializable;

public class User2 implements Serializable{

	private static final long serialVersionUID = 1L;
	int id;
	String username;
	String desc;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}