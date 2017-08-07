package com.etc.pp.common;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDateBase {
	
	
	
	public void getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con= null;
			
			try {
			 con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
						"scott","12345");
			
				System.err.println("连接成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (con!=null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public  static void Insert(String sql){
		try {
			Statement stmt = null;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con= null;
			
			try {
			 con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
						"scott","12345");
			stmt = con.createStatement();
			 stmt.executeUpdate(sql);
				System.out.println("添加成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (con!=null) {
					try {
						con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void update(){
		try {
			Statement stmt = null;
			String sql = "UPDATE USERINFO SET   AGE = 19 WHERE U_NAME = 1";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection con= null;
			
			try {
			 con  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
						"scott","12345");
			stmt = con.createStatement();
			 stmt.executeUpdate(sql);
			 
				System.out.println("添加成功");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (con!=null) {
					try {
						con.close();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
}
