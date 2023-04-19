package JDBC03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Dao : Database Access Object -> 데이터베이스 접근 및 작업 전용 클래스

public class BookDao {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null ;
	
	private Connection getConnection() { // 반복될만한 내용들을 메서드로 만들어 호출하여 사용할 수 있도록 함
		Connection con = null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,"scott","tiger"); // 지역변수이기 때문에 이 안에서만 적용되는 변수
						
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		}//예외처리가 필요한 코드들이기에 try catch로 
		
		return con;
	}
	
	private void close() { // 마찬가지로  반복될만한 내용들을 메서드로 만들어 호출하여 사용할 수 있도록 함
		try {
			if(con !=null) con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e) { e.printStackTrace();
		}
	}

	
	//select book
	public ArrayList<BookDto> selectBook() {
		ArrayList<BookDto> list = new ArrayList<BookDto>();
		//데이터베이스에서 booklist 테이블 조회 후 결과를 list에 담아 return
		
		//조회를 위해 연결
		con = getConnection();  //만들어둔 메서드를 멤버변수로 호출
		String sql = "select *from booklist order by booknum desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				/*
				int booknum = rs.getInt("booknum");
				String subject = rs.getString("subject");
				int makeyear = rs.getInt("makeyear");
				int inprice = rs.getInt("inprice");
				int rentprice = rs.getInt("rentprice");
				String grade = rs.getString("grade");
				
				BookDto bdto = new BookDto();
				bdto.setBooknum(booknum);
				bdto.setSubject(subject);
				bdto.setMakeyear(makeyear);
				bdto.setInprice(inprice);
				bdto.setRentprice(rentprice);
				bdto.setGrade(grade);
				*/ // 보통 이렇게 작성하지 않고, 아래의 코드처럼 작성함
				
				BookDto bdto = new BookDto();
				bdto.setBooknum(rs.getInt("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setMakeyear(rs.getInt("makeyear"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("rentprice"));
				bdto.setGrade(rs.getString("grade"));
				//반복이 실행될 때마다 new BookDto()로 만들어진 객체가 다음 반복으로 인해 값이 없어지기 전, list에 값을 담아 저장
				list.add(bdto);								
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//예외처리를 품고있으므로 SURROUND WITH TRY/CATCH		
		close();
		return list;
	}

	
	//insert book
	public int insertBook(BookDto bdto) {
		int result = 0;
		
		//1. DB에 연결
		con = getConnection();
		
		//2. SQL 문 설정
		String sql = "insert into booklist values(book_seq.nextVal,?,?,?,?,?)";
		
		//3. SQ:을 con과 함께 pstmt에 장착
		try {
			pstmt = con.prepareStatement(sql);
		
		
		//4. sql의 ?자리에 적정값을 배치
		pstmt.setString(1, bdto.getSubject());
		pstmt.setInt(2, bdto.getMakeyear());
		pstmt.setInt(3, bdto.getInprice());
		pstmt.setInt(4, bdto.getRentprice());
		pstmt.setString(5, bdto.getGrade());
		result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}
		//2. DB 연결 종료
		close();
		return result;		
		
		
	}
	//delete book
	public int deleteBook(int num) {
		int result =0;
		con = getConnection(); 
		String sql ="delete from booklist where booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}
		
		return result;
	}

	public BookDto getBook(String booknum) {
		BookDto bdto = null; // 여기에 널값이 들어가는 것이 중요
		con = getConnection(); 
		String sql = "select*from booklist where booknum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(booknum));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bdto = new BookDto();
				bdto.setBooknum(rs.getInt("booknum"));
				bdto.setSubject(rs.getString("subject"));
				bdto.setInprice(rs.getInt("inprice"));
				bdto.setRentprice(rs.getInt("rentprice"));
				bdto.setGrade(rs.getString("grade"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return bdto;
	}

	public int updateBook(BookDto bdto) {
		int result =0;
		con = getConnection(); 
		String sql = "update booklist set subject=?,makeyear = ? ,"
						+"inprice=? , rentprice= ? , grade= ? where booknum=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bdto.getSubject());
			pstmt.setInt(2, bdto.getMakeyear());
			pstmt.setInt(3, bdto.getInprice());
			pstmt.setInt(4, bdto.getRentprice());
			pstmt.setString(5, bdto.getGrade());
			pstmt.setInt(6, bdto.getBooknum());
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {e1.printStackTrace();
		}
		close();
		return result;
	}
}