package Dao;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	//声明常量
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USERNAME = "scott";
	public static final String PASSWORD = "12345";
	//建立连接
	public Connection getConnection () throws SQLException, ClassNotFoundException{
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	//关闭全部接口
	public void  closeAll(Connection con,PreparedStatement pstmt,ResultSet rs) throws SQLException {
		if (con!=null) {
			con.close();
		}
		if (pstmt!=null) {
			pstmt.close();
		}
		if (rs!=null) {
			rs.close();
		}
	}
}
