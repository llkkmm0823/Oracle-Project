package JDBC05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String id ="scott";
	static String pw = "tiger";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,id,pw); 
			// static은 인스턴스에 접근이 불가하기 때문에 오류, 변수들을 static변수로 만들어주면 해결
			} catch (ClassNotFoundException e) { e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace();} 
		return con;
	}
	
	
	public static void close(Connection con,PreparedStatement pstmt,ResultSet rs ) { 
		try {
			if(con !=null) con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e) { e.printStackTrace();
		}
	}
	
	
	
}
