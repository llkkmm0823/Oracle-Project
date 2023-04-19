package JDBC05;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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

class UpdateForm extends JFrame implements ActionListener{
	JTextField numseq;
	JTextField rentdate;
	JTextField bnum;
	JTextField mnum;
	JTextField discount;
	UpdateForm(){
		Font f = new Font("굴림",Font.BOLD,20);
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		
		
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		
		
		JLabel lb1 = new JLabel("  대 여 번 호 : ");
		JLabel lb2 = new JLabel("  대 여 일 자 : ");
		JLabel lb3 = new JLabel("  도 서 번 호 : ");
		JLabel lb4 = new JLabel("  회 원 번 호 : ");
		JLabel lb5 = new JLabel("  할 인 금 액 : ");
		
		JButton title = new JButton("★★★대 여 기 록 수 정★★★");
		lb1.setFont(f);lb2.setFont(f);lb3.setFont(f);lb4.setFont(f);lb5.setFont(f);
		
		
		jp1.add(lb1);jp1.add(lb2);jp1.add(lb3);jp1.add(lb4);jp1.add(lb5);
		
		con.add(jp1,BorderLayout.WEST);
		
		numseq = new JTextField(10);
		rentdate = new JTextField();
		bnum = new JTextField();
		mnum = new JTextField();
		discount = new JTextField();

		rentdate.setFont(f); numseq.setFont(f);
		bnum.setFont(f); mnum.setFont(f); discount.setFont(f);
		
		
		JPanel jp22=new JPanel();
		jp22.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton search = new JButton("조회");
		jp22.add(numseq); 
		jp22.add(search); 
		jp2.add(jp22); 

		jp2.add(rentdate);jp2.add(bnum);jp2.add(mnum);jp2.add(discount);
		con.add(jp2,BorderLayout.CENTER);
		
		
		JButton jb = new JButton("수정");
		jb.setFont(f);
		con.add(jb,BorderLayout.SOUTH);
		
		
		title.setFont(f);
		con.add(title,BorderLayout.NORTH);
		
		
		jb.addActionListener(this);
		search.addActionListener(this);
	
		setTitle ("레코드 추가");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		RentDto rdto = new RentDto();
		String s = e.getActionCommand();		
		RentDao rdao = RentDao.getInstance();

		switch(s) {
		case "조회" : 
			int nseq = Integer.parseInt(numseq.getText());
			rdto = rdao.getRent(nseq); // 메서드를 생성했는데도 계속 못찾아서 빡침. 혹시 몰라 ctrl+s 하여 저장했더니 갑자기 찾으심. 참고ㅡㅡ
			if(rdto==null) {
				System.out.println("조회한 대여기록이 없습니다");
				return;
			}else {
				//getRent를 RentDao에 생성
				//리턴받은 내용을 각 JTextField에 입력
				numseq.setText(rdto.getNumseq()+"");
				rentdate.setText(rdto.getRentdate());
				bnum.setText(rdto.getBooknum()+"");
				mnum.setText(rdto.getMembernum()+"");
				discount.setText(rdto.getDiscount()+"");
			}
			break;
		case "수정" :
			rdto.setNumseq(Integer.parseInt(numseq.getText()));
			rdto.setRentdate(rentdate.getText());
			rdto.setBooknum(Integer.parseInt(bnum.getText()));
			rdto.setMembernum(Integer.parseInt(mnum.getText()));
			rdto.setDiscount(Integer.parseInt(discount.getText()));
			int result = rdao.updateRent(rdto);
			if(result==1) {
				JOptionPane.showMessageDialog(null,"레코드 수정 성공");
				numseq.setText(""); rentdate.setText("");
				bnum.setText(""); mnum.setText(""); discount.setText("");
			}else{
			JOptionPane.showMessageDialog(null,"레코드 수정 실패");
			}
		}
		
	}
}
	
public class Rentlist_Update {

	public static void main(String[] args) {
			new UpdateForm();
	}

}
