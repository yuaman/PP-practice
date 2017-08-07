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
	 
	//ע�� 
	  
	public void reg() {
	while(true){	
		
		System.out.println("��ӭע��");
		
		System.out.println("�������˺�");
		String loginName = iScanner.next();
		//�ж��˺��Ƿ����������
		if(Common.isNotANumber(loginName)){
			System.out.println("��¼����������");
			continue;
		}
		userself.setLoginName(loginName);
		//TODO
		System.out.println("����������");
		String loginPwd = iScanner.next();
		userself.setLoginPwd(loginPwd);
		
		logger.info("�������ǳ�");
		String nick = iScanner.next();
		userself.setNick(nick);
		
		logger.info("�������Ա�");
		String sex = iScanner.next();
		userself.setSex(sex);
		
		logger.info("������ǩ��");
		String mark = iScanner.next();
		userself.setMark(mark);
		
		logger.info("����������");
		String age = iScanner.next();
		if (Common.isNotANumber(age)) {
			logger.info("���䲻������");
			continue;
		}
		userself.setAge(age);
		
		userDao.adduser(userself);
		logger.info("ע��ɹ�");
		break;
	}	
	
	}
	//��½
		public boolean login () {
			
			logger.info("���ѽ����½����");
			System.out.println("�������˺�");
			String loginName = iScanner.next();
			System.out.println("����������");
			String loginPwd = iScanner.next();
			
			UserInfo userInfo1 = userDao.findNameandPwd(loginName, loginPwd);
			
			if (userInfo1 == null ) {
				logger.info("���������");
				this.login();
			}else{
				userInfo1 = userself;
				System.out.println("��ӭ�㣺"+userself.getNick());
				
			}
			return true;
			
			
			
			}
		
		
	/*//д���û���Ϣ
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
	
	
	/*//��ȡ�û���Ϣ
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
	
	//�Ӻ���
    public void addFriend () {

   		
    		System.out.println("����������ӵ��� ͨ���˺Ų�ѯѡ��1��ͨ���ǳ�ģ����ѯѡ��2");
    		if (1==iScanner.nextInt()) {
				System.out.println("�������qq�˺�");
				String  loginName = iScanner.next();
				UserInfo addman = userDao.findloginName(loginName);
				if (addman!=null) {
					System.out.println("����Ϊ��"+addman.getLoginName());
				}
				fri_info.setUser_name(userself);
				fri_info.setFri_name(addman);
				friendDao.addfriend(fri_info);
				
				
				
				
			}else if(2==iScanner.nextInt()) {
				System.out.println("������˵��ǳƻ򲿷��ǳ�");
				String nick =iScanner.next();
				List<UserInfo > list = userDao.findNick(nick);
				for(int i = 0 ;i<list.size();i++){
					
					System.out.println("i"+list.get(i).getLoginName()+list.get(i).getLoginPwd());
					
				}
				System.out.println("��Ҫ���˭���������");
				UserInfo friend = list.get(iScanner.nextInt());
				fri_info.setUser_name(userself);
				fri_info.setFri_name(friend);
				friendDao.addfriend(fri_info);
				
			}else {
				System.out.println("���ɵ���ӣ������赽����Ҫ�ɼ���ɶ");
			}
    		
       	
    		
    		/*String loginName = iScanner.next();
    		//�ж��ļ��Ƿ�������жϴ����Ƿ����
    		
    			//�õ���������
    			logger.info("����Ϊ"+fri_info.getFri_name());
    			System.out.println("ȷ����Ҫ��Ӵ��ˣ�y/n");
    			//
    			if ("y".equals(iScanner.next())) {
    				String  tempPath = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
    			
    				Common.writeZifu(loginName,tempPath,true);
    				logger.info("������Ӵ���");
    				
				}else{
					logger.info("�㲢���������");
					
				}
    		}else{
    			System.out.println("���˲������ڣ�������");
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	*/
    	
    	
}
    
	//�鿴������Ϣ                
    public void chakan() {
    	System.out.println("��Ҫ�鿴�ĸ������ѵ���Ϣ�����������˺Ű�");
    	String loginName = iScanner.next();
    	UserInfo friendinfo = userDao.findloginName(loginName);
    	System.out.println(friendinfo.getLoginName()+friendinfo.getNick()+friendinfo.getMark());
    	/*//���������������ϵĺ��ѣ���Ҫ�ֱ��ȡ���������ļ���userinfo�������
        // ���ȵ�friendlist����õ����ǵĺ��룬Ȼ��ͨ����½�������������Ϣ��
    	try {
    		//�õ�·��
    		String path = Contact.BASE_PATH + userself.getLoginName() + "/friendlist.txt";
    		//��ȡ��Ϣ
    		String userInfo = Common.readBuffer(path);
    		
    		String flist []= userInfo.split(Contact.SPLIT);
    		
    		//�������ÿ���˺���ȫ����Ϣ
    		for (String loginName  : flist) {
				   String temp = Contact.BASE_PATH + loginName + "/userInfo.txt";
				   UserInfo friend = this.readUserInfo(temp);
				   logger.info(friend.getLoginName());
				   
			}
    		
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
		}*/
    }
    
	//ɾ������
	public void deleteFri(){
		System.out.println("��Ҫɾ���ĸ������ѣ����������˺Ű�");
    	String loginName = iScanner.next();
    	UserInfo friend = userDao.findloginName(loginName);
    	 userDao.deleteUser(userself,friend);
		/*//(���Լ��ĺ����б����Ƴ����ѵ�����)
		logger.info("������Ҫɾ���ĺ��ѵ��˺�");
		String loginName  =iScanner.next();
		
		String  tempPath = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
		String friendList;
		try {
			friendList = Common.readBuffer(tempPath);
			if (friendList.indexOf(loginName)!=-1) {
			friendList = friendList.replace(loginName, "");
			String  tempPath1 = Contact.BASE_PATH + userself.getLoginName()+"/friendlist.txt";
			this.writeUserInfo(tempPath1);
			logger.info("��ϲ�㣬��ɹ���ɾ������");
			}else{
				logger.info("��û�д�����");	
			}
	   	this.writeUserInfo(loginName);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	} 
		
	//�޸ĸ�����Ϣ
	public void  xiugai() {
		//���޸��û��������룩
		logger.info("��������û���");
			String loginName = iScanner.next();
			logger.info("�����������");
			String loginPwd = iScanner.next();
		if (!userself.getLoginName().equals(loginName)||userself.getLoginPwd().equals(loginPwd)) {
			logger.info("�û������������");
			this.xiugai();
		}else{
			logger.info("�޸��û���press��1��/�޸����룺press��2��");
			
			if (1 == iScanner.nextInt()) {
				
				
					logger.info("����Ҫ���û����ĳ�ʲô��˵�ɣ�����������");
					userself.setLoginName(iScanner.next());
					
					logger.info("��л��ɣ�������޸ĳɹ���");
				
					logger.info("�û����������������������");
					this.xiugai();
				
				
				
			}else if (2==iScanner.nextInt()) {
				logger.info("��������û���");
				String loginName1 = iScanner.next();
				logger.info("�����������");
				String loginPwd1 = iScanner.next();
				
					logger.info("���������ĳ�ʲô��˵�ɣ�����������");
					userself.setLoginPwd(iScanner.next());
					
					logger.info("��л��ɣ����Ѿ�������������");
				
					logger.info("�û����������������������");
					this.xiugai();
				
			}else {
				logger.info("�㵽��Ҫ��ʲô�����ɵ�棡");
			}
			
		}
		
		
		
		
	}
	//��������
	public void forget() {
		logger.info("�������������Ҫ��ô�죿");
		logger.info("�������û����ɣ�������������취");
		if (userself.getLoginName().equals(iScanner.next())) {
			logger.info(userself.getLoginPwd());
			logger.info("�����Ǿ������뿩");
		}else {
			logger.info("���û������ԣ���Ҳû�а취��");
		}
	}
	
	//��������
    public void liuyan(){
    //�������ѵ������ļ���д����Ϣ��
    	logger.info("��Ҫ�������������������ǣ���������ѵ�����");
    	String loginName = iScanner.next();
    	UserInfo sig_to=userDao.findloginName(loginName);
    	System.out.println("��Ҫ��������ʲô");
    	String content = iScanner.next();
    	userDao.sig(userself, sig_to,content);
    	/*String friendListPath = Contact.BASE_PATH+loginName+"/Friendlist.txt";
    	 String friendList;
		try {
			friendList = Common.readBuffer(friendListPath);
			if ( friendList.indexOf(loginName)!=-1) {
				String Message = Contact.BASE_PATH+loginName+"/Message.txt";
				logger.info("������Ҫ���Ե����ݰ�");
				String str = iScanner.next();
				Common.writeZifu(str, Message, true);
				logger.info("��ɹ��ĸ���������"+str);
		}else {
			logger.info("�㲢û���������");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	*/
    }
    
	//������
    public void kanliuyan() {
		//���뵽���ѵ������б�.��ȡ.���
    	logger.info("sb�����뿴˭������");
    	
    	String loginName = iScanner.next();
    	UserInfo sig_er = userDao.findloginName(loginName);
    	logger.info("sb�����뿴���˸�˭������");
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
				logger.info("�����de����:"+ Message);
		}else {
			logger.info("�㲢û���������");
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	