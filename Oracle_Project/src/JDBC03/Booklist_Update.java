package JDBC03;

import java.util.Scanner;

public class Booklist_Update {

	public static void main(String[] args) {
		
		//1. 수정할 레코드의 도서번호를 입력받음
		Scanner sc= new Scanner(System.in);
		System.out.print("수정할 도서의 도서번호를 입력하세요 : ");
		String booknum = sc.nextLine();
		
		BookDao bdao = new BookDao();
		BookDto bdto = null;
		
		
		//2. 입력받은 도서번호로 도서를 조회하여 리턴
		bdto = bdao.getBook(booknum);
		
		if(bdto ==null) {//조회 시 존재하지 않는 도서번호라면
			System.out.println("입력한 도서가 없습니다");
			return;
		}
			String temp = "";
			System.out.printf("도서제목 : %s\n",bdto.getSubject());
			System.out.print("수정할 제목을 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))bdto.setSubject( temp);
			
			
			temp = "";
			System.out.printf("출판년도 : %d\n",bdto.getMakeyear());
			System.out.print("수정할 출판년도를 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))bdto.setMakeyear (Integer.parseInt(temp));
			
			
			temp = "";
			System.out.printf("입고가격 : %d\n",bdto.getInprice());
			System.out.print("수정할 입고가격을 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))bdto.setInprice (Integer.parseInt(temp));
			
			temp = "";
			System.out.printf("대여료 : %d\n",bdto.getRentprice());
			System.out.print("수정할 대여료를 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))bdto.setRentprice (Integer.parseInt(temp));
			
			temp = "";
			System.out.printf("등급 : %s\n",bdto.getGrade());
			System.out.print("수정할 등급을 입력하세요 (수정X -> 엔터입력):   ");
			temp = sc.nextLine();
			if(!temp.equals(""))bdto.setGrade(temp);
	
			int result = bdao.updateBook(bdto);
			if(result==1)System.out.println("수정완료");
			else System.out.println("수정실패");
	}

}
