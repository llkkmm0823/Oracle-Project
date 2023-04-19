package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {

	public static void main(String[] args) {
	
		// 자바에서 지원하는 데이터베이스 연결을 위한 구성요소들과 객체를 세팅
		// 접속에 필요한 정보들(데이터베이스 연결 객체의 멤버들에 저장될 내용들)을 String 변수에 저장
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String id = "scott";
		String pw = "tiger";
		
		//연결 순서
		//1. JDBC를 통한 데이터베이스 연결 클래스의 객체 : Connection 생성
		Connection con = null;
		
	try {
		//2. 드라이버 클래스 파일 지정
		Class.forName( driver );
		
		//3. 연결 드라이버 매니저가 데이터베이스 연결을 하고, 연결해준 연결 인스턴스를 con 변수에 저장
		con = DriverManager.getConnection(url,id,pw);
		
		System.out.println("데이터베이스에 연결 성공했습니다");
		// 데이터 베이스 작업
		
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
		System.out.println("데이터베이스에 연결 실패");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("데이터베이스에 연결 실패");

	}
	
	try {
		if(con != null) con.close();
		System.out.println("데이터베이스 종료");
	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("연결이 종료되지 않았습니다");
	}
	
	//ojdbc6.jar이 연결이 안되어있어서 쌩으로 실행하면 당연히 연결되지 않음  (class not found 오류)

	//지정을 따로 해줘야하는데, project위에서 properties -> java build path -> libraries -> module path에 마우스 클릭 
	// -> add external jars -> C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib --> 의 ojdbc6 파일 클릭
	//-> 열기 , 이 후 실행할 화면에서 Run 
	
	// 오류 시 String url이 잘못되었거나(오타) id pw를 잘못 입력했거나. 오류메세지 참고
	
	
		
		
		
		
}
}
