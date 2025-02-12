package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Search extends Template{

	JLabel lb1 = new JLabel("원래 사용했던 아이디를 입력해주세요.");
	JTextField tf1 = new JTextField();
	JButton btn1 = new JButton("확인");
	JLabel lb2 = new JLabel("변경할 아이디를 입력해주세요.");
	JTextField tf2 = new JTextField();
	JButton btn2 = new JButton("아이디 변경하기");
	
	String t = "";
	
	int ck = 0;
	
	public Search(int n) {

		lb1.setFont(new Font("맑은 고딕",1,20));
		lb2.setFont(new Font("맑은 고딕",1,20));
		btn1.setFont(new Font("맑은 고딕",1,20));
		btn2.setFont(new Font("맑은 고딕",1,20));
		
		btn1.setForeground(Color.white);
		btn2.setForeground(Color.white);
		
		btn1.setBackground(new Color(100, 200, 200));
		btn2.setBackground(new Color(100, 200, 200));
		
		tf1.setBorder(new MatteBorder(0,0,1,0,Color.black));
		tf2.setBorder(new MatteBorder(0,0,1,0,Color.black));
		
		
		if(n == 0) {
			t = "아이디";
		}else {
			t = "비밀번호";
		}
		
		lb1.setText("원래 사용했던 "+t+"를 입력해주세요.");
		lb2.setText("변경할 "+t+"를 입력해주세요.");
		btn2.setText(t+" 변경하기");
		
		lb1.setBounds(10, 5, 435, 30);
		tf1.setBounds(10, 50, 295, 30);
		btn1.setBounds(315, 40, 110, 40);
		lb2.setBounds(10, 90, 435, 30);
		tf2.setBounds(10, 130, 415, 30);
		btn2.setBounds(10 ,170, 415, 40);
		
		add(lb1);
		add(lb2);
		add(tf1);
		add(tf2);
		add(btn1);
		add(btn2);
		
		setSize(450, 300);
		setVisible(true);
		setTitle(t+" 찾기");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				Main.main.setVisible(true);
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tf1.getText().equals("")) {
					Msg.err("빈칸이 있습니다.");
					return;
				}
				if(t.equals("아이디")) {
					mgr.sql="select * from user where id='"+tf1.getText()+"'";
				}else {
					mgr.sql="select * from user where pw='"+tf1.getText()+"'";
				}
				list=mgr.a(1);
				if(list.size() == 0) {
					Msg.err("사용하던 "+t+"와 일치하지 않습니다.");
					return;
				}
				ck = 1;
				Msg.info("확인되었습니다. 변경할 "+t+"를 적어주세요.");
				return;
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ck == 0) {
					Msg.err("확인이 완료되지 않았습니다.");
					return;
				}
				if(tf2.getText().equals("")) {
					Msg.err("빈칸이 있습니다.");
					return;
				}
				
				if(t == "아이디") {
					mgr.sql="select * from user where id='"+tf2.getText()+"'";
					list=mgr.a(1);
					if(list.size() != 0) {
						Msg.err("이미 누군가가 사용하고 있는 아이디입니다.");
						return;
					}
					mgr.sql="update user set id='"+tf2.getText()+"' where u_no="+Login.no;
					mgr.update();
					dispose(); 
					Main.main.dispose();
					new Login();
				}else {
					if(tf2.getText().matches(".*[ㄱ-힣].*")||tf2.getText().length() < 5 || tf2.getText().length() >20||! tf2.getText().matches(".*[!@#$%].*")) {
						Msg.err("비밀번호 형식을 확인해주세요.");
						return;
					}
					mgr.sql="update user set pw='"+tf2.getText()+"' where u_no="+Login.no;
					mgr.update();
					dispose();
					Main.main.dispose();
					new Login();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new Search(0);
	}

}
