package JDBC04;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import JDBC03.BookDao;
import JDBC03.BookDto;

public class MemberMain {

	public static void main(String[] args) {

while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n---메뉴선택---");
			System.out.printf("1.데이터열람");
			System.out.printf(" 2.데이터추가");
			System.out.printf(" 3.데이터수정");
			System.out.printf(" 4.데이터삭제");
			System.out.println(" 5.프로그램 종료");
			System.out.print(">>메뉴선택 : ");
			String choice = sc.nextLine();
			
			if(choice.equals("5"))break;
			
			switch(choice) {
			case"1" : select();  break;
			case"2" : insert();  break;
			case"3" : update();  break;
			case"4" : delete();  break;
			default : System.out.println("메뉴 선택이 잘못되었습니다");
			}
	}
System.out.println("프로그램을 종료합니다.");
}

	
	
	private static void select() {
		MemberDao mdao = new MemberDao();
		ArrayList<MemberDto> list = mdao.selectMember();
		
		System.out.println("회원번호\t이름\t\t전화번호\t\t\t생년월일\t\t사은포인트\t성별\t나이");
		System.out.println("------------------------------------------------------------------------------------------");
		for(MemberDto dto : list) { 
			System.out.printf("%5d\t\t %3s\t%13s\t\t%10s\t %5d\t  %1s\t%2d\n",
					dto.getMembernum(),dto.getName(),dto.getPhone(),dto.getBirth(),
					dto.getBpoint(),dto.getGender(),dto.getAge() );
		}
		
	}
	
	private static void insert() {
		
		MemberDto mdto = new MemberDto();
		MemberDao mdao = new MemberDao();
		
		 

	      
	      Scanner sc = new Scanner(System.in);
	      
	      System.out.print("저장할 회원명을 입력하세요 : ");
	      mdto.setName(sc.nextLine());
	      
	      System.out.print("전화번호를 입력하세요 : ");
	      mdto.setPhone(sc.nextLine());
	      
	      
	      
	      
	      //생일 입력 - java.util.Date()형식을 입력받은 후 java.sql.Date()로의 변환이 필요
	      // java.util.Date()의 입력을 위해선 SimpleDateFormat 의 parse 루틴필요
	      System.out.print("생년월일을 입력하세요(YYYY-MM-DD) : ");	    
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      String temp;
	      java.util.Date d=null;
	      while(true) {
		      try {
		    	temp= sc.nextLine();
				d=sdf.parse(temp);
				break;
		      } catch (ParseException e) {
				System.out.print("다시 입력 ( 입력 예 : 2015-12-31) : " );
		      }
	      }
	      mdto.setBirth(temp);
	       
	       /*
	      //java.util.Date를 java.sql.Date로 변환하는 법. (처음엔 date 자료형으로 했어서 필요했으나, 이 후 String으로 자료형을 바꾸면서 필요없어짐
	      //d.getTime()을 java.sql.Date의 생성자의 전달인수로 넣음
	      java.sql.Date birth = new  java.sql.Date(d.getTime());
	      //mdto.setBirth(birth);
	      */
	      
	      
	      //사은포인트는 디폴트값이 있기 때문에 입력 생략
	  
	      System.out.print("성별을 입력하세요 : ");
	      mdto.setGender(sc.nextLine());
	      
	      
	      // 나이는 입력받지 않고 계산
	      Calendar c = Calendar.getInstance();
	      Calendar today = Calendar.getInstance();
	      c.setTime(d); // c.setTime(birth); Date자료를 Calendar 자료로 변환//이었으나 string -> calendar로 변환이 되었다
	      int age = today.get(Calendar.YEAR)-c.get(Calendar.YEAR)+1;
	      mdto.setAge(age);
	      
	      
	      
	      int result =mdao.insertMember(mdto);	
	      if(result==1)System.out.println("추가 성공~");
			else System.out.println("추가 실패ㅠ");
	}
	
	
	private static void update() {
		MemberDao mdao = new MemberDao();
		MemberDto mdto = null;
		Scanner sc= new Scanner(System.in);
		

		
		String membernum;
		while(true){
		System.out.print("수정할 회원의 회원번호를 입력하세요 : ");
		membernum = sc.nextLine();
		if(membernum.equals(""))System.out.println("회원번호 입력은 필수입니다");
		else break;
		}

		
		mdto = mdao.getMember(Integer.parseInt(membernum));
		
		
		if(mdto ==null) {
			System.out.println("해당회원이 존재하지 않습니다.");
			return;
		}
		//조회된 기존값을 먼저 출력하고 수정할 내용을 입력받음 각항목들 수정하지 않으려면 엔터만 입력
		//이름, 전화번호, 성별, 사은포인트, 생년월일&나이
				
			String temp = "";
			System.out.printf("회원명 : %s\n",mdto.getName());
			System.out.print("수정할 회원명을 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))mdto.setName( temp);
			
			
			temp = "";
			System.out.printf("전화번호 : %s\n",mdto.getPhone());
			System.out.print("수정할 전화번호를 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))mdto.setPhone (temp);
			
			
			System.out.printf("생년월일 : %s\n",mdto.getBirth());
			System.out.print("수정할 생년월일을 입력하세요 (수정X -> 엔터입력):   ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		      java.util.Date d=null;
		      java.sql.Date birth=null;
		      while(true) {
			      try {
			    	  temp=sc.nextLine();
			    	  if(temp.equals("")) break; //수정을 위해 입력한 날짜가 없다면 break
					d=sdf.parse(temp);//String->java.util.Date로 변환
					//birth = new java.sql.Date(d.getTime()); // java.sql.Date로 변환
					//mdto.setBirth(birth); // 입력받은 날짜를 Dto에 저장
					mdto.setBirth(temp);
					break;
			      } catch (ParseException e) {
					System.out.print("다시 입력 ( 입력 예 : 2015-12-31) : " );
			      }
		      }
			      if(!temp.equals("")) { //수정을 위해 입력한 날짜가 있다면
			    	  Calendar c = Calendar.getInstance();
				      Calendar today = Calendar.getInstance();
				      c.setTime(d); // c.setTime(birth); Date자료를 Calendar 자료로 변환
				      int age = today.get(Calendar.YEAR)-c.get(Calendar.YEAR) +1;
				      mdto.setAge(age);
			      }
			
			temp = "";
			System.out.printf("사은포인트 : %d\n",mdto.getBpoint());
			System.out.print("수정할 사은포인트를 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))mdto.setBpoint (Integer.parseInt(temp));
			
			temp = "";
			System.out.printf("성별 : %s\n",mdto.getGender());
			System.out.print("수정할 성별을 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))mdto.setGender(temp);
			
			
			int result = mdao.updateMember(mdto);
			if(result==1)System.out.println("수정완료~");
			else System.out.println("수정실패ㅠ");
	}
	
	
	
	private static void delete() {
		
		//삭제할 회원번호 입력받되, 없는 회원번호를 입력할 시 "존재하지 않는 회원번호입니다"라고 출력하고 메서드 종료하기
		// 회원이 있으면 레코드 삭제
		Scanner sc = new Scanner(System.in);
		MemberDao mdao = new MemberDao();
		MemberDto mdto = null;
		String membernum;
		
		while(true){
			System.out.print("삭제할 회원번호를 입력하세요 : ");
			membernum = sc.nextLine();
			if(membernum.equals(""))System.out.println("회원번호 입력은 필수입니다");
			else break;
			}
			mdto = mdao.getMember(Integer.parseInt(membernum));
			
			if(mdto ==null) {
				System.out.println("해당회원이 존재하지 않습니다.");
				return;
			}
		
		int result = mdao.deleteMember(Integer.parseInt(membernum));
		
		if(result==1)System.out.println("삭제 성공~");
		else System.out.println("삭제 실패ㅠ");
	}	
}
