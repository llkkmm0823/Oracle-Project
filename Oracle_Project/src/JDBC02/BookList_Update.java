package JDBC02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookList_Update {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,id,pw);
			
			
		//1. 수정할 레코드의 도서번호를 입력받음
		Scanner sc= new Scanner(System.in);
		System.out.print("수정할 도서의 도서번호를 입력하세요 : ");
		String booknum = sc.nextLine();
		
		//2. 입력받은 도서번호가 조회 가능한 (존재하는) 번호인지 확인 / 조회 시 존재하지 않는 도서라면 존재하지 않는다 알림 후 종료
		String sql = "select*from booklist where booknum=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,Integer.parseInt(booknum));
		rs=pstmt.executeQuery();
		
		String subject,grade;
		int makeyear,inprice,rentprice;
		
		if(rs.next()) {
			subject = rs.getString("subject");
			makeyear = rs.getInt("makeyear");
			inprice = rs.getInt("inprice");
			rentprice = rs.getInt("rentprice");
			grade = rs.getString("grade");		
		}else {
			System.out.println("해당 도서번호의 도서가 없습니다"); 
			return;
		}		
		//3. 존재하는 도서라면 수정할 내용을 입력받아 수정
		String temp = "";
		System.out.printf("도서제목 : %s\n",subject);
		System.out.print("수정할 제목을 입력하세요 (수정X -> 엔터입력):   ");
		temp = sc.nextLine();
		if(!temp.equals(""))subject = temp;
		
		
		temp = "";
		System.out.printf("출판년도 : %d\n",makeyear);
		System.out.print("수정할 출판년도를 입력하세요 (수정X -> 엔터입력):   ");
		temp = sc.nextLine();
		if(!temp.equals(""))makeyear = Integer.parseInt(temp);
		
		
		temp = "";
		System.out.printf("입고가격 : %d\n",inprice);
		System.out.print("수정할 입고가격을 입력하세요 (수정X -> 엔터입력):   ");
		temp = sc.nextLine();
		if(!temp.equals(""))inprice = Integer.parseInt(temp);
		
		temp = "";
		System.out.printf("대여료 : %d\n",rentprice);
		System.out.print("수정할 대여료를 입력하세요 (수정X -> 엔터입력):   ");
		temp = sc.nextLine();
		if(!temp.equals(""))rentprice = Integer.parseInt(temp);
		
		temp = "";
		System.out.printf("등급 : %s\n",grade);
		System.out.print("수정할 등급을 입력하세요 (수정X -> 엔터입력):   ");
		temp = sc.nextLine();
		if(!temp.equals(""))grade = temp;

		
		sql = "update booklist set subject=?,makeyear = ? , inprice=? , rentprice= ? , grade= ?"
				+ "where booknum=?";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1,subject);
		pstmt.setInt(2, makeyear);
		pstmt.setInt(3, inprice);
		pstmt.setInt(4, rentprice);
		pstmt.setString(5, grade);
		pstmt.setInt(6, Integer.parseInt(booknum));
		
		
		//4. 수정이 완료된 결과값을 int 변수로 받아 수정이 완료 되었는지 실패되었는지 출력
		int result = pstmt.executeUpdate();
		if(result==1)System.out.println("수정완료");
		else System.out.println("수정실패");

		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		}
		
		try {
			if(con !=null) con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e) { e.printStackTrace();
		}
	}

}
