package JDBC05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

class RentDetail_Form extends JFrame implements ActionListener{
	
	RentDao rdao = RentDao.getInstance();  //actionPerformed에서 써야됨
	String [][] data;
	JTable jtb;
	
	RentDetail_Form(){
		
		ArrayList<RentDetailDto>list = rdao.selectRentDetail();	
		 data= new String[list.size()][9];
		 
		for(int i=0;i<list.size();i++) {
			data[i][0] = String.valueOf(list.get(i).getNumseq());
			data[i][1] = list.get(i).getRentdate();
			data[i][2] = String.valueOf(list.get(i).getBooknum());
			data[i][3] = list.get(i).getSubject();
			data[i][4] = String.valueOf(list.get(i).getMembernum());
			data[i][5] = list.get(i).getName();
			data[i][6] = String.valueOf(list.get(i).getRentprice());
			data[i][7] = String.valueOf(list.get(i).getDiscount());
			data[i][8] = String.valueOf(list.get(i).getAmount());		
		}
		
		//String [] colHeads = { "대여번호", "대여일" , "도서번호" ,"도서명","회원번호" ,"회원성명","대여금액","할인금액","할인가" };
		//jtb = new JTable(data,colHeads);
		DefaultTableModel model = new DefaultTableModel();
		jtb = new JTable(model);
		model.addColumn("대여번호");
		model.addColumn("대여일");
		model.addColumn("도서번호");
		model.addColumn("도서명");
		model.addColumn("회원번호");
		model.addColumn("회원성명");
		model.addColumn("대여금액");
		model.addColumn("할인금액");
		model.addColumn("할인가");
		for(int i=0;i<list.size();i++) model.addRow(data[i]);
		
		JScrollPane jsp = new JScrollPane(jtb);
		JButton btn = new JButton("새로고침");
		
		TableColumnModel tcm = jtb.getColumnModel();

		DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer(); 
		dtcr1.setHorizontalAlignment(SwingConstants.CENTER);//데이터 가운데 정렬
		
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer(); 
		dtcr2.setHorizontalAlignment(SwingConstants.LEFT);//데이터 가운데 정렬
		
		
		tcm.getColumn(0).setCellRenderer(dtcr1);
		tcm.getColumn(1).setCellRenderer(dtcr1);
		tcm.getColumn(2).setCellRenderer(dtcr1);
		tcm.getColumn(3).setCellRenderer(dtcr2);
		tcm.getColumn(4).setCellRenderer(dtcr1);
		tcm.getColumn(5).setCellRenderer(dtcr1);
		tcm.getColumn(6).setCellRenderer(dtcr1);
		tcm.getColumn(7).setCellRenderer(dtcr1);
		tcm.getColumn(8).setCellRenderer(dtcr1);		

		 jtb.getColumnModel().getColumn(0).setMaxWidth(60);
		 jtb.getColumnModel().getColumn(0).setMinWidth(60);
		 jtb.getColumnModel().getColumn(0).setWidth(60);
		 
		 jtb.getColumnModel().getColumn(1).setMaxWidth(100);
		 jtb.getColumnModel().getColumn(1).setMinWidth(100);
		 jtb.getColumnModel().getColumn(1).setWidth(100);

		 jtb.getColumnModel().getColumn(2).setMaxWidth(60);
		 jtb.getColumnModel().getColumn(2).setMinWidth(60);
		 jtb.getColumnModel().getColumn(2).setWidth(60);

		 jtb.getColumnModel().getColumn(4).setMaxWidth(60);
		 jtb.getColumnModel().getColumn(4).setMinWidth(60);
		 jtb.getColumnModel().getColumn(4).setWidth(60);
		 
		 jtb.getColumnModel().getColumn(5).setMaxWidth(80);
		 jtb.getColumnModel().getColumn(5).setMinWidth(80);
		 jtb.getColumnModel().getColumn(5).setWidth(80);
		 
		 jtb.getColumnModel().getColumn(6).setMaxWidth(80);
		 jtb.getColumnModel().getColumn(6).setMinWidth(80);
		 jtb.getColumnModel().getColumn(6).setWidth(80);

		btn.addActionListener(this); //버튼 안넣어서 작동 안됐었음!!!!!!!! 꼭 기억하기!!!!!!!!!!!!!!

		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(jsp, BorderLayout.CENTER);
		container.add(btn, BorderLayout.SOUTH);
		
		setTitle ("테이블 박스 실습");
		setSize(800,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<RentDetailDto>list = rdao.selectRentDetail();	
		//data= new String[list.size()][9]; 
		
		DefaultTableModel model = (DefaultTableModel)jtb.getModel();
		if(list.size() != model.getRowCount()) {
			String [] record = new String[9];
			model.addRow(record);
		}
		
		for(int i=0;i<list.size();i++) {
			jtb.setValueAt(String.valueOf(list.get(i).getNumseq()),i,0);
			jtb.setValueAt(list.get(i).getRentdate(),i,1);
			jtb.setValueAt(String.valueOf(list.get(i).getBooknum()),i,2);
			jtb.setValueAt( list.get(i).getSubject(),i,3);
			jtb.setValueAt(String.valueOf(list.get(i).getMembernum()),i,4);
			jtb.setValueAt(list.get(i).getName(),i,5);
			jtb.setValueAt( String.valueOf(list.get(i).getRentprice()),i,6);
			jtb.setValueAt( String.valueOf(list.get(i).getDiscount()),i,7);
			jtb.setValueAt(String.valueOf(list.get(i).getAmount()),i,8);		
			
		}
		
	}
}

public class RentDetail_Select {

	public static void main(String[] args) {
		new RentDetail_Form();
	}

}
