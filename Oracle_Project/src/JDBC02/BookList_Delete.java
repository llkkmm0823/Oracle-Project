package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Delete {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,id,pw);
			//삭제할 booknum 입력받아 해당 도서 삭제
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("삭제할 도서번호를 입력하세요 : ");
			String booknum = sc.nextLine();
			
			String sql ="delete from booklist where booknum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt( booknum));
		
			int result = pstmt.executeUpdate();
			
			if(result==1)System.out.println("삭제성공~");
			else System.out.println("삭제실패ㅠ");
		
			
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		}
		
		try {
			if(con !=null) con.close();
			if(pstmt != null)pstmt.close();
		}catch(SQLException e) { e.printStackTrace();
		}
	}

}
