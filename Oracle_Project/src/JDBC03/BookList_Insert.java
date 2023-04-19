package JDBC03;

import java.util.Scanner;

public class BookList_Insert {

	public static void main(String[] args) {
		
		BookDto bdto = new BookDto();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("저장할 도서명을 입력하세요 : ");
		//String subject = sc.nextLine();
		bdto.setSubject(sc.nextLine());
		
		System.out.print("출판년도를 입력하세요 : ");
		//String makeyear = sc.nextLine();
		bdto.setMakeyear(Integer.parseInt(sc.nextLine()));
		
		System.out.print("입고가격을 입력하세요 : ");
		//String inprice = sc.nextLine();
		bdto.setInprice(Integer.parseInt(sc.nextLine()));
		
		System.out.print("대여료를 입력하세요 : ");
		//String rentprice = sc.nextLine();
		bdto.setRentprice(Integer.parseInt(sc.nextLine()));
		
		System.out.print("등급을 입력하세요 : ");
		//String grade = sc.nextLine();
		bdto.setGrade(sc.nextLine());
		
		BookDao bdao = new BookDao();
		
		
		// bdao.insertBook(subject,makeyear,inprice,rentprice,grade);
	
		int result = bdao.insertBook(bdto);
		
		if(result==1)System.out.println("추가 성공~");
		else System.out.println("추가 실패ㅠ");
		
	}

}
