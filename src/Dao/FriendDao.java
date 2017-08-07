package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.etc.pp.entity.Fri_info;

public class FriendDao extends BaseDao {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public void addfriend(Fri_info fri_info){
		String sql = "UPDATE FRI_INFO SET U_NAME= ?,FRI_NAME=?,'≈Û”—',SYSDATE";
		try {
			con = this.getConnection();
			pstmt = con.prepareCall(sql);
			pstmt.setString(1, fri_info.getUser_name().getLoginName());
			pstmt.setString(2, fri_info.getFri_name().getLoginName());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
