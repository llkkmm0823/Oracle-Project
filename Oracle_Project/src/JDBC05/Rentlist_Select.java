package JDBC05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

class SelectForm extends JFrame{
	SelectForm(){
		
		RentDao rdao = RentDao.getInstance();
		ArrayList<RentDto>list = rdao.selectRent();		
		
		String [] colHeads = { "대여번호", "대여일" , "도서번호" , "회원번호" , "할인금액" };
		String [][] data = new String[list.size()][5];
		for(int i=0;i<list.size();i++) {
			data[i][0] = String.valueOf(list.get(i).getNumseq());
			data[i][1] = list.get(i).getRentdate();
			data[i][2] = String.valueOf(list.get(i).getBooknum());
			data[i][3] = String.valueOf(list.get(i).getMembernum());
			data[i][4] = String.valueOf(list.get(i).getDiscount());
		}

		
		JTable jtb = new JTable(data,colHeads);
		JScrollPane jsp = new JScrollPane(jtb);
		//스크롤 패널을 만든 것
		JButton btn = new JButton("새로고침");
		
		
		TableColumnModel tcm = jtb.getColumnModel();//데이터 모델 

		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer(); 
		dtcr1.setHorizontalAlignment(SwingConstants.CENTER);//데이터 가운데 정렬
		tcm.getColumn(0).setCellRenderer(dtcr1);
		tcm.getColumn(1).setCellRenderer(dtcr1);
		tcm.getColumn(2).setCellRenderer(dtcr1);
		tcm.getColumn(3).setCellRenderer(dtcr1);

		
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);//데이터 오른쪽 정렬
		tcm.getColumn(4).setCellRenderer(dtcr2);

		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(jsp, BorderLayout.CENTER);
		container.add(btn, BorderLayout.SOUTH);
		
		setTitle ("테이블 박스 실습");
		setSize(800,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		
		
	}
}

public class Rentlist_Select {

	public static void main(String[] args) {
		new SelectForm();
	}

}
