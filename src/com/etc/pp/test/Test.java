
package com.etc.pp.test;

import java.io.IOException;

import com.etc.pp.service.UserService;

public class Test {
	public static void main(String[] args) throws IOException {
		UserService us = new UserService();
       //us.reg();
		us.login();
		us.addFriend();
		//us.chakan();
		//us.deleteFri();
				
		//us.forget();
		//us.xiugai();
		
		//us.liuyan();
		//us.kanliuyan();
		
	}
}
