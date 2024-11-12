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

	JLabel lb1 = new JLabel("¿ø·¡ »ç¿ëÇß´ø ¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	JTextField tf1 = new JTextField();
	JButton btn1 = new JButton("È®ÀÎ");
	JLabel lb2 = new JLabel("º¯°æÇÒ ¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
	JTextField tf2 = new JTextField();
	JButton btn2 = new JButton("¾ÆÀÌµğ º¯°æÇÏ±â");
	
	String t = "";
	
	int ck = 0;
	
	public Search(int n) {

		lb1.setFont(new Font("¸¼Àº °íµñ",1,20));
		lb2.setFont(new Font("¸¼Àº °íµñ",1,20));
		btn1.setFont(new Font("¸¼Àº °íµñ",1,20));
		btn2.setFont(new Font("¸¼Àº °íµñ",1,20));
		
		btn1.setForeground(Color.white);
		btn2.setForeground(Color.white);
		
		btn1.setBackground(new Color(100, 200, 200));
		btn2.setBackground(new Color(100, 200, 200));
		
		tf1.setBorder(new MatteBorder(0,0,1,0,Color.black));
		tf2.setBorder(new MatteBorder(0,0,1,0,Color.black));
		
		
		if(n == 0) {
			t = "¾ÆÀÌµğ";
		}else {
			t = "ºñ¹Ğ¹øÈ£";
		}
		
		lb1.setText("¿ø·¡ »ç¿ëÇß´ø "+t+"¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		lb2.setText("º¯°æÇÒ "+t+"¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		btn2.setText(t+" º¯°æÇÏ±â");
		
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
		setTitle(t+" Ã£±â");
		
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
					Msg.err("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
					return;
				}
				if(t.equals("¾ÆÀÌµğ")) {
					mgr.sql="select * from user where id='"+tf1.getText()+"'";
				}else {
					mgr.sql="select * from user where pw='"+tf1.getText()+"'";
				}
				list=mgr.a(1);
				if(list.size() == 0) {
					Msg.err("»ç¿ëÇÏ´ø "+t+"¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
					return;
				}
				ck = 1;
				Msg.info("È®ÀÎµÇ¾ú½À´Ï´Ù. º¯°æÇÒ "+t+"¸¦ Àû¾îÁÖ¼¼¿ä.");
				return;
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ck == 0) {
					Msg.err("È®ÀÎÀÌ ¿Ï·áµÇÁö ¾Ê¾Ò½À´Ï´Ù.");
					return;
				}
				if(tf2.getText().equals("")) {
					Msg.err("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
					return;
				}
				
				if(t == "¾ÆÀÌµğ") {
					mgr.sql="select * from user where id='"+tf2.getText()+"'";
					list=mgr.a(1);
					if(list.size() != 0) {
						Msg.err("ÀÌ¹Ì ´©±º°¡°¡ »ç¿ëÇÏ°í ÀÖ´Â ¾ÆÀÌµğÀÔ´Ï´Ù.");
						return;
					}
					mgr.sql="update user set id='"+tf2.getText()+"' where u_no="+Login.no;
					mgr.update();
					dispose(); 
					Main.main.dispose();
					new Login();
				}else {
					if(tf2.getText().matches(".*[¤¡-ÆR].*")||tf2.getText().length() < 5 || tf2.getText().length() >20||! tf2.getText().matches(".*[!@#$%].*")) {
						Msg.err("ºñ¹Ğ¹øÈ£ Çü½ÄÀ» È®ÀÎÇØÁÖ¼¼¿ä.");
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
