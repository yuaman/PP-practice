package com.etc.pp.service;

import java.awt.datatransfer.FlavorListener;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.etc.pp.common.Common;
import com.etc.pp.common.Contact;
import com.etc.pp.common.MyDateBase;
import com.etc.pp.entity.Fri_info;
import com.etc.pp.entity.Fri_info;
import com.etc.pp.entity.UserInfo;
import com.etc.pp.test.Test;

import Dao.FriendDao;
import Dao.UserDao;


public class UserService {
	 static Scanner iScanner = new Scanner(System.in);
	 
	 private  UserInfo userself = new UserInfo();
	 private FriendDao friendDao  = new FriendDao();
	 private UserDao userDao = new UserDao();
	 Logger logger =  Logger.getLogger(UserService.class);
	 Fri_info fri_info = new Fri_info();
	 
	//注册 
	  
	public void reg() {
	while(true){	
		
		System.out.println("欢迎注册");
		
		System.out.println("请输入账号");
		String loginName = iScanner.next();
		//判断账号是否由数字组成
		if(Common.isNotANumber(loginName)){
			System.out.println("登录名不是数字");
			continue;
		}
		userself.setLoginName(loginName);
		//TODO
		System.out.println("请输入密码");
		String loginPwd = iScanner.next();
		userself.setLoginPwd(loginPwd);
		
		logger.info("请输入昵称");
		String nick = iScanner.next();
		userself.setNick(nick);
		
		logger.info("请输入性别");
		String sex = iScanner.next();
		userself.setSex(sex);
		
		logger.info("请输入签名");
		String mark = iScanner.next();
		userself.setMark(mark);
		
		logger.info("请输入年龄");
		String age = iScanner.next();
		if (Common.isNotANumber(age)) {
			logger.info("年龄不是数字");
			continue;
		}
		userself.setAge(age);
		
		userDao.adduser(userself);
		logger.info("注册成功");
		break;
	}	
	
	}
	//登陆
		public boolean login () {
			
			logger.info("你已进入登陆界面");
			System.out.println("请输入账号");
			String loginName = iScanner.next();
			System.out.println("请输入密码");
			String loginPwd = iScanner.next();
			
			UserInfo userInfo1 = userDao.findNameandPwd(loginName, loginPwd);
			
			if (userInfo1 == null ) {
				logger.info("你输入错误");
				this.login();
			}else{
				userInfo1 = userself;
				System.out.println("欢迎你："+userself.getNick());
				
			}
			return true;
			
			
			
			}
		
		
	/*//写入用户信息
	public void writeUserInfo( String userInfoPath){
		try{
			
			String str = userself.getLoginName() +Contact.SPLIT+userself.getLoginPwd();
			Common.writeZifu(str, userInfoPath,true);
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/
	
	
	/*//读取用户信息
	public UserInfo readUserInfo(String userInfoPath){
		UserInfo userInfo = new UserInfo();
		try{
			
			String content = Common.readBuffer(userInfoPath);
			String[] nameAndPwd = content.split(Contact.SPLIT);
			userInfo.setLoginName(nameAndPwd[0]); 
			
			userInfo.setLoginPwd(nameAndPwd[1]);
			
			return userInfo;
			
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
		
	}*/
	
	//加好友
    public void addFriend () {

   		
    		System.out.println("查找你想添加的人 通过账号查询选择1或通过昵称模糊查询选择2");
    		if (1==iScanner.nextInt()) {
				System.out.println("输入此人qq账号");
				String  loginName = iScanner.next();
				UserInfo addman = userDao.findloginName(loginName);
				if (addman!=null) {
					System.out.println("此人为："+addman.getLoginName());
				}
				fri_info.setUser_name(userself);
				fri_info.setFri_name(addman);
				friendDao.addfriend(fri_info);
				
				
				
				
			}else if(2==iScanner.nextInt()) {
				System.out.println("输入此人的昵称或部分昵称");
				String nick =iScanner.next();
				List<UserInfo > list = userDao.findNick(nick);
				for(int i = 0 ;i<list.size();i++){
					
					System.out.println("i"+list.get(i).getLoginName()+list.get(i).getLoginPwd());
					
				}
				System.out.println("你要添加谁，输入序号");
				UserInfo friend = list.get(iScanner.nextInt());
				fri_info.setUser_name(userself);
				fri_info.setFri_name(friend);
				friendDao.addfriend(fri_info);
				
			}else {
				System.out.println("你个傻袍子，你他妈到底他要干几把啥");
			}
    		
       	
    		
    		/*String loginName = iScanner.next();
    		//判断文件是否存在来判断此人是否存在
    		
    			//得到此人名字
    			logger.info("此人为"+fri_info.getFri_name());
    			System.out.println("确定你要添加此人？y/n");
    			//
    			if ("y".equals(iScanner.next())) {
    				String  tempPath = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
    			
    				Common.writeZifu(loginName,tempPath,true);
    				logger.info("你已添加此人");
    				
				}else{
					logger.info("你并不想添加他");
					
				}
    		}else{
    			System.out.println("此人并不存在，放弃吧");
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	*/
    	
    	
}
    
	//查看好友信息                
    public void chakan() {
    	System.out.println("你要查看哪个好朋友的信息，输入他的账号吧");
    	String loginName = iScanner.next();
    	UserInfo friendinfo = userDao.findloginName(loginName);
    	System.out.println(friendinfo.getLoginName()+friendinfo.getNick()+friendinfo.getMark());
    	/*//（假设有两个以上的好友，需要分别读取他们所在文件夹userinfo并输出；
        // 首先到friendlist里面得到他们的号码，然后通过登陆号码调用其他信息）
    	try {
    		//得到路径
    		String path = Contact.BASE_PATH + userself.getLoginName() + "/friendlist.txt";
    		//读取信息
    		String userInfo = Common.readBuffer(path);
    		
    		String flist []= userInfo.split(Contact.SPLIT);
    		
    		//遍历输出每个人好友全部信息
    		for (String loginName  : flist) {
				   String temp = Contact.BASE_PATH + loginName + "/userInfo.txt";
				   UserInfo friend = this.readUserInfo(temp);
				   logger.info(friend.getLoginName());
				   
			}
    		
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
		}*/
    }
    
	//删除好友
	public void deleteFri(){
		System.out.println("你要删除哪个好朋友，输入他的账号吧");
    	String loginName = iScanner.next();
    	UserInfo friend = userDao.findloginName(loginName);
    	 userDao.deleteUser(userself,friend);
		/*//(从自己的好友列表中移除好友的名字)
		logger.info("输入你要删除的好友的账号");
		String loginName  =iScanner.next();
		
		String  tempPath = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
		String friendList;
		try {
			friendList = Common.readBuffer(tempPath);
			if (friendList.indexOf(loginName)!=-1) {
			friendList = friendList.replace(loginName, "");
			String  tempPath1 = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
			this.writeUserInfo(tempPath1);
			logger.info("恭喜你，你成功的删除了他");
			}else{
				logger.info("你没有此朋友");	
			}
	   	this.writeUserInfo(loginName);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	} 
		
	//修改个人信息
	public void  xiugai() {
		//（修改用户名与密码）
		logger.info("输入你的用户名");
			String loginName = iScanner.next();
			logger.info("输入你的密码");
			String loginPwd = iScanner.next();
		if (!userself.getLoginName().equals(loginName)||userself.getLoginPwd().equals(loginPwd)) {
			logger.info("用户名或密码错误");
			this.xiugai();
		}else{
			logger.info("修改用户名press‘1’/修改密码：press‘2’");
			
			if (1 == iScanner.nextInt()) {
				
				
					logger.info("你想要把用户名改成什么？说吧，哥会满足你的");
					userself.setLoginName(iScanner.next());
					
					logger.info("感谢哥吧，哥帮你修改成功了");
				
					logger.info("用户名或密码错误，请重新输入");
					this.xiugai();
				
				
				
			}else if (2==iScanner.nextInt()) {
				logger.info("输入你的用户名");
				String loginName1 = iScanner.next();
				logger.info("输入你的密码");
				String loginPwd1 = iScanner.next();
				
					logger.info("你想把密码改成什么，说吧，哥会满足你的");
					userself.setLoginPwd(iScanner.next());
					
					logger.info("感谢哥吧，哥已经帮你把密码改了");
				
					logger.info("用户名或密码错误，请重新输入");
					this.xiugai();
				
			}else {
				logger.info("你到底要做什么，你个傻叉！");
			}
			
		}
		
		
		
		
	}
	//忘记密码
	public void forget() {
		logger.info("如果忘记密码你要怎么办？");
		logger.info("输出你的用户名吧，哥来给你想想办法");
		if (userself.getLoginName().equals(iScanner.next())) {
			logger.info(userself.getLoginPwd());
			logger.info("喏，那就是密码咯");
		}else {
			logger.info("连用户名不对，哥也没有办法了");
		}
	}
	
	//进行留言
    public void liuyan(){
    //（往好友的留言文件中写入信息）
    	logger.info("你要给你的朋友留言吗？如果是，输入你好友的名字");
    	String loginName = iScanner.next();
    	UserInfo sig_to=userDao.findloginName(loginName);
    	System.out.println("你要给他留言什么");
    	String content = iScanner.next();
    	userDao.sig(userself, sig_to,content);
    	/*String friendListPath = Contact.BASE_PATH+loginName+"/Friendlist.txt";
    	 String friendList;
		try {
			friendList = Common.readBuffer(friendListPath);
			if ( friendList.indexOf(loginName)!=-1) {
				String Message = Contact.BASE_PATH+loginName+"/Message.txt";
				logger.info("输入你要留言的内容吧");
				String str = iScanner.next();
				Common.writeZifu(str, Message, true);
				logger.info("你成功的给他留言了"+str);
		}else {
			logger.info("你并没有这个朋友");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    }
    
	//看留言
    public void kanliuyan() {
		//进入到好友的留言列表.读取.输出
    	logger.info("sb，你想看谁的留言");
    	
    	String loginName = iScanner.next();
    	UserInfo sig_er = userDao.findloginName(loginName);
    	logger.info("sb，你想看此人给谁的留言");
    	String loginName1 = iScanner.next();
    	UserInfo sig_to = userDao.findloginName(loginName1);
    	userDao.seesig(sig_er, sig_to);
    }
}
    	/*String friendListPath = Contact.BASE_PATH+loginName+"/Friendlist.txt";
    	 String friendList;
		try {
			friendList = Common.readBuffer(friendListPath);
			if ( friendList.indexOf(loginName)!=-1) {
				String MessagePath = Contact.BASE_PATH+loginName+"/Message.txt";
				
				String Message=Common.readBuffer(MessagePath);
				logger.info("你给他de留言:"+ Message);
		}else {
			logger.info("你并没有这个朋友");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	