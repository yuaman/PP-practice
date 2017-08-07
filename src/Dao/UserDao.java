package Dao;

import java.awt.RadialGradientPaint;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import com.etc.pp.entity.Fri_info;
import com.etc.pp.entity.UserInfo;

public class UserDao extends BaseDao{
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//添加一个用户
	public void adduser(UserInfo userInfo) {
		String sql = "INSERT INTO USERINFO VALUES(?,?,?,?,?,?,SYSDATE)";
		
		try {
			con = this.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userInfo.getLoginName());
			pstmt.setString(2, userInfo.getLoginPwd());
			pstmt.setString(3, userInfo.getNick());
			pstmt.setString(4, userInfo.getSex());
			pstmt.setString(5, userInfo.getMark());
			pstmt.setString(6, userInfo.getAge());
			
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				this.closeAll(con, pstmt, rs);
			} catch (SQLException e) {
			
				e.printStackTrace();
			
		}
		}
	}
		//根据QQ号查找好友
		public  UserInfo findloginName(String loginName){
			UserInfo userInfo  = null;
			String sql = "SELECT * FROM USERINFO WHERE U_NAME IS ?";
			try {
				try {
					con = this.getConnection();
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, loginName);
					rs = pstmt.executeQuery();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (rs.next()) {
					userInfo = new UserInfo();
					userInfo.setAge(rs.getString("Age"));
					userInfo.setLoginName(rs.getString("U_Name"));
					userInfo.setLoginPwd(rs.getString("Pwd"));
					userInfo.setMark(rs.getString("MARK"));
					userInfo.setNick(rs.getString("NICK"));
					userInfo.setSex(rs.getString("SEX"));
					userInfo.setU_time(rs.getString("U_TIME"));;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
					try {
						this.closeAll(con, pstmt, rs);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
									   return userInfo;
		}		
		//根据qq号和密码查询用户信息
		public  UserInfo findNameandPwd(String loginName,String loginPwd){
			UserInfo userInfo  = null;
			String sql = "SELECT * FROM USERINFO WHERE U_NAME = ? AND PWD = ?";
			try {
				try {
					con=this.getConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, loginName);
				pstmt.setString(2, loginPwd);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					userInfo = new UserInfo();
					userInfo.setAge(rs.getString("Age"));
					userInfo.setLoginName(rs.getString("U_Name"));
					userInfo.setLoginPwd(rs.getString("Pwd"));
					userInfo.setMark(rs.getString("MARK"));
					userInfo.setNick(rs.getString("NICK"));
					userInfo.setSex(rs.getString("SEX"));
					userInfo.setU_time(rs.getString("U_TIME"));;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
					try {
						this.closeAll(con, pstmt, rs);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
									   return userInfo;
		}		
	    //修改用户信息
		public void modifyUserInfo(UserInfo userInfo) {
			String sql = "UPDATE USERINFO SET U_NAME = ?,PWD =? NICK=? ,SEX=?,MARK =?,AGE=?,U_TIME =?";
			try {
				con = this.getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userInfo.getLoginName());
				pstmt.setString(2, userInfo.getLoginPwd());
				pstmt.setString(3, userInfo.getNick());
				pstmt.setString(4, userInfo.getSex());
				pstmt.setString(5, userInfo.getMark());
				pstmt.setString(6, userInfo.getAge());
				pstmt.setString(7, userInfo.getU_time());
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					this.closeAll(con, pstmt, rs);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	    //根据昵称查询用户	
		public List<UserInfo> findNick(String NICK){
			
			List<UserInfo> list =new ArrayList<UserInfo>();
			UserInfo userInfo = null;
			String sql = "SELECT * FROM USERINFO WHERE U_NAMW like ? AND U_NAME NOT IN(SELECT FRI_NAME FROM FRI_INFO WHERE U_NAME =? ) ";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				pstmt.setString(1, "%"+NICK+"%");
				
				pstmt.setLong(2, 1);
				if (rs.next()) {
					userInfo = new UserInfo();
					userInfo.setAge(rs.getString("Age"));
					userInfo.setLoginName(rs.getString("U_Name"));
					userInfo.setLoginPwd(rs.getString("Pwd"));
					userInfo.setMark(rs.getString("MARK"));
					userInfo.setNick(rs.getString("NICK"));
					userInfo.setSex(rs.getString("SEX"));
					userInfo.setU_time(rs.getString("U_TIME"));;
					
					list.add(userInfo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
					try {
						this.closeAll(con, pstmt, rs);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
									   return list;
		}	
		public void deleteUser(UserInfo userInfo,UserInfo userInfo1) {
			String sql = "DELETE FROM FRI_INFO  WHERE U_NAME = ?AND FRI_NAME =?)";
			
			try {
				con = this.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, userInfo.getLoginName());
				pstmt.setString(2,userInfo1.getLoginName());
				
				
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					this.closeAll(con, pstmt, rs);
				} catch (SQLException e) {
				
					e.printStackTrace();
				
			}
			}
		}
		public void sig(UserInfo userInfo,UserInfo userInfo1,String content) {
			String sql = "INSERT INTO SIG_INFO VALUES(6,?,?,?,SYSDATE)";
			
			try {
				con = this.getConnection();
				pstmt = con.prepareStatement(sql);
				
			
				pstmt.setString(2,userInfo.getLoginName());
				pstmt.setString(2,userInfo1.getLoginName());
				pstmt.setString(2,content);
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					this.closeAll(con, pstmt, rs);
				} catch (SQLException e) {
				
					e.printStackTrace();
				
			}
			}
		}
		public void seesig(UserInfo userInfo,UserInfo userInfo1) {
			String sql = "SELECT SIG_CONTENT FROM SIG_INFO WHERE SIG_ER =?,SIG_TO = ?";
			
			try {
				con = this.getConnection();
				pstmt = con.prepareStatement(sql);
				
			
				pstmt.setString(1,userInfo.getLoginName());
				pstmt.setString(2,userInfo1.getLoginName());
				
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				try {
					this.closeAll(con, pstmt, rs);
				} catch (SQLException e) {
				
					e.printStackTrace();
				
			}
			}
		}
}
