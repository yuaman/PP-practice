package com.etc.pp.entity;

import java.sql.Date;

public class UserInfo {
	private String loginName;
	private String loginPwd;
	private String Nick;
	private String Sex;
	private String Mark;
	private String Age;
	
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	
	public String getU_time() {
		return U_time;
	}
	public void setU_time(String u_time) {
		U_time = u_time;
	}

	private String U_time;
	
	
	public String getLoginName() {
		return loginName;
	}
	public  void setLoginName(String loginName) {
		this.loginName  = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public  void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getNick() {
		return Nick;
	}
	public void setNick(String nick) {
		this.Nick = nick;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	
	
	
	
}
