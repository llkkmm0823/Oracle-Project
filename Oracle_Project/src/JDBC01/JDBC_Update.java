package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_Update {

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
			
			Scanner sc = new Scanner(System.in);	
			System.out.print("수정할 회원의 번호를 입력하세요 : ");
			String num = sc.nextLine();
			
			System.out.print("수정할 항목의 번호를 입력하세요 (1.이메일 2. 전화번호) : ");
			String input = sc.nextLine();
			
			String sql = "";
			switch(input) {
			case"1" :
				sql = "update customer set email = ? where num= ? ";
				System.out.print("수정할 이메일를 입력하세요 : ");
				String email = sc.nextLine();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,email);
				pstmt.setInt(2,Integer.parseInt( num));
				break;
				
			case"2" :
				sql = "update customer set tel = ? where num= ? ";
				System.out.print("수정할 전화번호를 입력하세요 : ");
				String tel = sc.nextLine();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,tel);
				pstmt.setInt(2,Integer.parseInt( num));
				break;
			}
			int result = pstmt.executeUpdate();
			if(result==1)System.out.println("수정성공~");
			else System.out.println("수정실패ㅠ");
			
			
			
			
			
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
