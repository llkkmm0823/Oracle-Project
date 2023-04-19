package JDBC05;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC04.MemberDto;

public class RentDao {

	//생성자를 private으로 만들어서 외부에서 생성자를 사용할 수 없게 만듬
	private RentDao() {}
	
	//클래스 내부에서 딱 한개 유일한 객체를 생성 private static으로
	//private 요소는 클래스 내부에서 제한없이 사용이 가능
	private static RentDao itc = new RentDao();
	
	//외부에서 itc를 리턴받아 쓸 수 있게 해주는 public static 메서드를 생성
	public static RentDao getInstance() {return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null ;
	
	// DBManager dbm = new DBManager(); 객체를 만드는 것은 주석처리하고
	/*
	public void insert() {
		con = DBManager.getConnection(); //연결 받아쓰기		
		DBManager.close(con,pstmt,rs); // 전달인수로 con, pstmt,rs를 보내주는 것
	}
	*/
	
	
	public ArrayList<RentDto> selectRent() {
		ArrayList<RentDto>list = new ArrayList<RentDto>();
		
		con = DBManager.getConnection();
		String sql = "select rentlist.*,to_char(rentdate,'YYYY-MM-DD') as rentdateStr from rentlist order by numseq desc";
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RentDto rdto = new RentDto();
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setRentdate(rs.getString("rentdateStr"));
				rdto.setBooknum(rs.getInt("bnum"));
				rdto.setMembernum(rs.getInt("mnum"));
				rdto.setDiscount(rs.getInt("discount"));
				list.add(rdto);			
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {DBManager.close(con, pstmt, rs);
		}
		return list;
	}

	public ArrayList<RentDetailDto> selectRentDetail() {
		ArrayList<RentDetailDto> list = new ArrayList<RentDetailDto>();
		con=DBManager.getConnection();
		String sql = "select *from rentDetail order by numseq desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RentDetailDto rdto = new RentDetailDto();
				rdto.setRentdate(rs.getString("rentdateStr"));
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setBooknum(rs.getInt("bnum"));
				rdto.setSubject(rs.getString("subject"));
				rdto.setMembernum(rs.getInt("mnum"));
				rdto.setName(rs.getString("name"));
				rdto.setRentprice(rs.getInt("rentprice"));
				rdto.setDiscount(rs.getInt("discount"));
				rdto.setAmount(rs.getInt("amount"));
				list.add(rdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertRent(RentDto rdto) {
		int result=0;
		
		con = DBManager.getConnection();
		String sql = "insert into rentlist (numseq,rentdate,bnum,mnum,discount)"
				+"values(rent_seq.nextVal,sysdate,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rdto.getBooknum());
			pstmt.setInt(2, rdto.getMembernum());
			pstmt.setInt(3, rdto.getDiscount());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		}finally {DBManager.close(con, pstmt, rs);}
		return result;
	}

	public int updateRent(RentDto rdto) {

		int result=0;
		con = DBManager.getConnection();
		String sql = "update rentlist set rentdate=to_date(''||?||'','YYYY-MM-DD'),"
				+"bnum=?,mnum=?,discount=? where numseq=?";
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, rdto.getRentdate());
			pstmt.setInt(2, rdto.getBooknum());
			pstmt.setInt(3, rdto.getMembernum());
			pstmt.setInt(4, rdto.getDiscount());
			pstmt.setInt(5, rdto.getNumseq());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e1) {e1.printStackTrace();
		}finally {DBManager.close(con, pstmt, rs);}
		return result;
	}

	public RentDto getRent(int nseq) {
		RentDto rdto = null; 
		con = DBManager.getConnection();
		String sql = "select to_char(rentdate,'YYYY_MM_DD') as rentdateStr, rentlist.* "
				+ "from rentlist where numseq=?";  
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,nseq);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rdto = new RentDto();
				rdto.setNumseq(rs.getInt("numseq"));
				rdto.setRentdate(rs.getString("rentdateStr"));
				rdto.setBooknum(rs.getInt("bnum"));
				rdto.setMembernum(rs.getInt("mnum"));
				rdto.setDiscount(rs.getInt("discount"));
			}				
		} catch (SQLException e) {e.printStackTrace();
		}finally {DBManager.close(con, pstmt, rs);}
		return rdto;
	}

}
	


