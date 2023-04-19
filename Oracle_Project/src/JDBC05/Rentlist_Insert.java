package JDBC05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class InsertForm extends JFrame implements ActionListener{
	JTextField rentdate;
	JTextField bnum;
	JTextField mnum;
	JTextField discount;
	
	InsertForm(){
		
		Font f = new Font("굴림",Font.BOLD,20);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.setLayout(new GridLayout(3,1));
		jp2.setLayout(new GridLayout(3,1));

		//JLabel lb1 = new JLabel("  대 여 일 자 : ");
		JLabel lb2 = new JLabel("  도 서 번 호 : ");
		JLabel lb3 = new JLabel("  회 원 번 호 : ");
		JLabel lb4 = new JLabel("  할 인 금 액 : ");
		JButton title = new JButton("★★★대 여 기 록 추 가★★★");
		
		//lb1.setFont(f);
		lb2.setFont(f);lb3.setFont(f);lb4.setFont(f);
		//jp1.add(lb1);
		jp1.add(lb2);jp1.add(lb3);jp1.add(lb4);
		con.add(jp1,BorderLayout.WEST);
		
		//rentdate = new JTextField();
		bnum = new JTextField();
		mnum = new JTextField();
		discount = new JTextField();

		//rentdate.setFont(f); 
		bnum.setFont(f); mnum.setFont(f); discount.setFont(f);
		//jp2.add(rentdate);
		jp2.add(bnum);jp2.add(mnum);jp2.add(discount);
		con.add(jp2,BorderLayout.CENTER);
		
		
		JButton jb = new JButton("추가");
		jb.setFont(f);
		con.add(jb,BorderLayout.SOUTH);
		
		
		title.setFont(f);
		con.add(title,BorderLayout.NORTH);
		
		
		jb.addActionListener(this);
	
		setTitle ("레코드 추가");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		RentDto rdto = new RentDto();
		
		if(bnum.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"도서번호를 입력하세요"); // 팝업메세지박스
			return;
		}
		if(mnum.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"회원번호를 입력하세요");
			return;
		}
		if(discount.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"할인금액을 입력하세요");
			return;
		}
		// 이 if문을 잘 빠져나와야 정상실행되는 것
		String booknum = bnum.getText();
		rdto.setBooknum(Integer.parseInt(booknum));
		
		rdto.setMembernum(Integer.parseInt(mnum.getText()));
		rdto.setDiscount(Integer.parseInt(discount.getText()));
		
		
		RentDao rdao = RentDao.getInstance();
		int result = rdao.insertRent(rdto);
		if(result==1) {
			JOptionPane.showMessageDialog(null,"레코드 추가 성공");
			bnum.setText(""); mnum.setText(""); discount.setText("");
		}else{
		JOptionPane.showMessageDialog(null,"레코드 추가 실패");
		}
	}
}

public class Rentlist_Insert {

	public static void main(String[] args) {
		new InsertForm();
	}

}
