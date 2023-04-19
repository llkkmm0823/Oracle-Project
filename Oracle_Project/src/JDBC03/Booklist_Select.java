package JDBC03;

import java.util.ArrayList;

public class Booklist_Select {

	public static void main(String[] args) {
		
		//클래스를 생성하여 클래스 객체 생성
		BookDao bdao = new BookDao();
		
		//내가 하고자 하는 내용 작성
		
		// 현재 위치에서 필요한 것은 데이터베이스에서 booklist를 조회한 후 그 결과를 가져오는 것
		// BookDao의 selectBook()메서드에 맡기고 그 결과를 리턴받을 예정
		// 데이터베이스의 결과는 ResultSet 객체에 저장되지만 
		// 하나하나의 값들을 Arraylist에 넣어 리턴받기 위해 selectBook 메서드룰 생성
		// 다만 필드값들은 레코드 단위의 저장소에 담고, 그 레코드 단위의 저장소를 Arraylist에 담는 원리
		// 그 값이 담긴 레코드 단위의 저장소를 클래스로 정의할 것
		
		ArrayList<BookDto> list = bdao.selectBook();
		//<> 괄호 안의 내용으로 된 자료형만 이 리스트에 저장하겠다는 뜻
		
		System.out.println("도서번호\t출판년도\t입고가격\t출고가격\t등급\t제목");
		System.out.println("-----------------------------------------------------------------");
		for(BookDto dto : list) { // 레코드 단위로 하나씩 꺼내짐
			System.out.printf("%d\t\t %d \t %d \t %d \t  %s \t %s\n",
					dto.getBooknum(),dto.getMakeyear(),dto.getInprice(),
					dto.getRentprice(),dto.getGrade(),dto.getSubject());
		}
		
		

	}

}
