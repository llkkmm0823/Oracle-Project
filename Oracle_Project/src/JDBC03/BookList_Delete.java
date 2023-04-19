package JDBC03;

import java.util.Scanner;

public class BookList_Delete {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 도서번호를 입력하세요 : ");
		int num = Integer.parseInt(sc.nextLine());
		
		BookDao bdao = new BookDao();
		int result = bdao.deleteBook(num);
		
		if(result==1)System.out.println("삭제 성공~");
		else System.out.println("삭제 실패ㅠ");
	}

}
