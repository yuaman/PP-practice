package com.etc.pp.entity;

public class Fri_info {
     private  UserInfo user_name;
     public UserInfo getUser_name() {
		return user_name;
	}
	public void setUser_name(UserInfo user_name) {
		this.user_name = user_name;
	}
	public  UserInfo getFri_name() {
		return fri_name;
	}
	public void setFri_name(UserInfo fri_name) {
		this.fri_name = fri_name;
	}
	public String getFri_state() {
		return fri_state;
	}
	public void setFri_state(String fri_state) {
		this.fri_state = fri_state;
	}
	public String getFri_time() {
		return fri_time;
	}
	public void setFri_time(String fri_time) {
		this.fri_time = fri_time;
	}
	private UserInfo fri_name;
     private String fri_state;
     private String fri_time;
     
		
	
}
