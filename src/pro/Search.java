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

	JLabel lb1 = new JLabel("���� ����ߴ� ���̵� �Է����ּ���.");
	JTextField tf1 = new JTextField();
	JButton btn1 = new JButton("Ȯ��");
	JLabel lb2 = new JLabel("������ ���̵� �Է����ּ���.");
	JTextField tf2 = new JTextField();
	JButton btn2 = new JButton("���̵� �����ϱ�");
	
	String t = "";
	
	int ck = 0;
	
	public Search(int n) {

		lb1.setFont(new Font("���� ���",1,20));
		lb2.setFont(new Font("���� ���",1,20));
		btn1.setFont(new Font("���� ���",1,20));
		btn2.setFont(new Font("���� ���",1,20));
		
		btn1.setForeground(Color.white);
		btn2.setForeground(Color.white);
		
		btn1.setBackground(new Color(100, 200, 200));
		btn2.setBackground(new Color(100, 200, 200));
		
		tf1.setBorder(new MatteBorder(0,0,1,0,Color.black));
		tf2.setBorder(new MatteBorder(0,0,1,0,Color.black));
		
		
		if(n == 0) {
			t = "���̵�";
		}else {
			t = "��й�ȣ";
		}
		
		lb1.setText("���� ����ߴ� "+t+"�� �Է����ּ���.");
		lb2.setText("������ "+t+"�� �Է����ּ���.");
		btn2.setText(t+" �����ϱ�");
		
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
		setTitle(t+" ã��");
		
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
					Msg.err("��ĭ�� �ֽ��ϴ�.");
					return;
				}
				if(t.equals("���̵�")) {
					mgr.sql="select * from user where id='"+tf1.getText()+"'";
				}else {
					mgr.sql="select * from user where pw='"+tf1.getText()+"'";
				}
				list=mgr.a(1);
				if(list.size() == 0) {
					Msg.err("����ϴ� "+t+"�� ��ġ���� �ʽ��ϴ�.");
					return;
				}
				ck = 1;
				Msg.info("Ȯ�εǾ����ϴ�. ������ "+t+"�� �����ּ���.");
				return;
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ck == 0) {
					Msg.err("Ȯ���� �Ϸ���� �ʾҽ��ϴ�.");
					return;
				}
				if(tf2.getText().equals("")) {
					Msg.err("��ĭ�� �ֽ��ϴ�.");
					return;
				}
				
				if(t == "���̵�") {
					mgr.sql="select * from user where id='"+tf2.getText()+"'";
					list=mgr.a(1);
					if(list.size() != 0) {
						Msg.err("�̹� �������� ����ϰ� �ִ� ���̵��Դϴ�.");
						return;
					}
					mgr.sql="update user set id='"+tf2.getText()+"' where u_no="+Login.no;
					mgr.update();
					dispose(); 
					Main.main.dispose();
					new Login();
				}else {
					if(tf2.getText().matches(".*[��-�R].*")||tf2.getText().length() < 5 || tf2.getText().length() >20||! tf2.getText().matches(".*[!@#$%].*")) {
						Msg.err("��й�ȣ ������ Ȯ�����ּ���.");
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
