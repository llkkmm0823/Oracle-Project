package JDBC01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBC_Select {

	public static void main(String[] args) {
		
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String driver = "oracle.jdbc.driver.OracleDriver";
				String id = "scott";
				String pw = "tiger";
	
				Connection con = null;	//데이터 베이스 연결을 위한 객체
				PreparedStatement pstmt = null; // con에서 SQL 실행해주는 객체
				ResultSet rs = null ; // SQL 실행결과를 저장하는 객체
						
				
			try {
				Class.forName( driver );
				con = DriverManager.getConnection(url,id,pw);		
				// System.out.println("데이터베이스에 연결 성공했습니다");
				
				// 데이터베이스 연결 후에는 SQL명령을 사용하기 위해 String 변수에 SQL명령 준비
				// 데이터베이스에 제공되어질 명령이므로 String형으로 준비
				
				String sql = "select *from customer";
				//오류 명 : table or view does not exist : 테이블이나 뷰 이름이 잘못되었거나 잘못생성된 것
				//오류 명 : 부적합한 열이름 : 읽어오다가 오류가 난 것으로, 읽긴 읽었는데 어디선가 오타가 있거나 읽어올 수 없는 상태가 되어 오류발생
				
				//  SQL에 담긴 SQL명령을 con을 통해 pstmt에 장착
				pstmt = con.prepareStatement(sql);
				// pstmt = con.prepareStatement( "select *from customer" ); 와 같음
				
				rs = pstmt.executeQuery();
				//SQL 실행결과를 저장하는 객체인 rs에 저장
				
				System.out.println("번호\t 이름\t\t 이메일\t\t\t전화번호");
				System.out.println("---------------------------------------------------------------------");
				
				
				// rs.next() : 최초 실행은 객체의 시작부분(데이터 없는 곳) 에서 첫 번째 레코드로 이동 & read하는 메서드
				// 그 다음부터는 다음 레코드로 이동해라~ 읽어라 라는 의미
				// rs.next() 메서드가 실행될 때 다음 레코드가 있으면 true, 없으면 false를 리턴
				// 즉, 읽을 내용이 계속 있으면 읽어오고, 없으면 종료된다는 뜻
				
				
				while(rs.next()) {
					//rs.getInt() : number형 필드값을 추출하는 메서드. 괄호안에 필드 이름을 정확히 기재
					// rs.getString() : varchar2형 필드값을 추출하는 메서드
					//모든 자료형에 대해 get~() 메서드가 모두 존재
					
					//필드명에 오타가 있거나 안맞으면 부적합한 열이름 이라는 에러가 발생
					int num = rs.getInt("num");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String tel = rs.getString("tel");
					
					System.out.printf("%d \t %s \t %s \t %s \n",num,name,email,tel);		
				}
				
				
			}catch (ClassNotFoundException e) { e.printStackTrace();
			} catch (SQLException e) { e.printStackTrace();
			}
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//*참고 
			//Connect에 있던 코드를 그대로 옮겨왔으나, 실행되지 않는 오류 발생. 오류 구글링.
			//project에 오른쪽 클릭, properties - Run/debugging setting에서 
			// 실행되고 있는 모든 것을 삭제한 뒤 재 실행해주었더니 정상 실행됨
	}
	
	
	

}
