package JDBC04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC03.BookDto;

public class MemberDao {
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
			} catch (SQLException e) { e.printStackTrace();} return con;}
	
	private void close() { 
		try {
			if(con !=null) con.close();
			if(pstmt != null)pstmt.close();
			if(rs != null)rs.close();
		}catch(SQLException e) { e.printStackTrace();}
	}

	public ArrayList<MemberDto> selectMember() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		
		con = getConnection(); 
		String sql = "select to_char(birth,'YYYY-MM-DD')as birthStr,"
				+ "membernum,name,phone,bpoint,gender,age "
				+ " from memberlist "
				+"order by membernum desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MemberDto mdto = new MemberDto();
				mdto.setMembernum(rs.getInt("membernum"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setBirth(rs.getString("birthStr"));
				mdto.setBpoint(rs.getInt("bpoint"));
				mdto.setGender(rs.getString("gender"));
				mdto.setAge(rs.getInt("age"));
				list.add(mdto);			
			}
		} catch (SQLException e) {e.printStackTrace();
		}
		close();
		return list;
	}

	public int insertMember(MemberDto mdto) {
		int result = 0;
		con = getConnection();
		
		String sql = "insert into memberlist(membernum,name,phone,birth,gender,age)"
		+"values(member_seq.nextVal,?,?,"
		+ "to_date(''||?||'', 'YYYY-MM-DD'),"  // 'YYYY-MM-DD' <- 이 형식의 날짜로 변환해달라
		+ "?,?)";
		
			try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setString(3, mdto.getBirth());
			pstmt.setString(4, mdto.getGender());
			pstmt.setInt(5, mdto.getAge());
			
			result = pstmt.executeUpdate();
			} catch (SQLException e) {e.printStackTrace();
			}
		close();
		return result;		
		}
		
	
	public MemberDto getMember(int membernum) {
		
		MemberDto mdto = null; 
		con = getConnection(); 
		String sql = "select memberlist.*, to_char(birth,'YYYY-MM-DD')as birthStr from memberlist where membernum=?";  
		//여기가 틀린것같음
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,membernum);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mdto = new MemberDto();
				mdto.setMembernum(rs.getInt("membernum"));
				mdto.setName(rs.getString("name"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setBirth(rs.getString("birthStr"));
				mdto.setBpoint(rs.getInt("bpoint"));
				mdto.setGender(rs.getString("gender"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return mdto;
	}

	public int updateMember(MemberDto mdto) {
		
		int result =0;
		con = getConnection(); 
		String sql = "update memberlist set name=?, phone = ? ,"
						+"birth = to_date(''||?||'', 'YYYY-MM-DD'),"
						+ " bpoint= ? , gender= ?, age=? where membernum=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mdto.getName());
			pstmt.setString(2, mdto.getPhone());
			pstmt.setString(3, mdto.getBirth());
			pstmt.setInt(4, mdto.getBpoint());
			pstmt.setString(5, mdto.getGender());
			pstmt.setInt(6, mdto.getAge());
			pstmt.setInt(7, mdto.getMembernum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {e1.printStackTrace();
		}
		close();
		return result;
	}

	
	
	public int deleteMember(int membernum) {
		
		int result =0;
		con = getConnection(); 
		String sql ="delete from Memberlist where Membernum=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,membernum);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}
		return result;
	}
}