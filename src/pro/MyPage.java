package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class MyPage extends PanelTemplate{

	JLabel icon = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/mypage.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
	JLabel lb1 = new JLabel("");
	JLabel lb2 = new JLabel("");
	JLabel lb3 = new JLabel("");
	
	JLabel lb4 = new JLabel("¾Æ¾Æµð º¯°æÇÏ±â");
	JLabel lb5 = new JLabel("ºñ¹Ð¹øÈ£ º¯°æÇÏ±â");
	JLabel lb6 = new JLabel("·Î±×¾Æ¿ô ÇÏ±â");
	
	JLabel icon1 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/next.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	JLabel icon2 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/next.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	JLabel icon3 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/signout.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	
	public MyPage() {
		Login.no = "1";
		
		lb1.setFont(new Font("¸¼Àº °íµñ",1,30));
		lb2.setFont(new Font("¸¼Àº °íµñ",0,20));
		lb3.setFont(new Font("¸¼Àº °íµñ",0,20));
		lb4.setFont(new Font("¸¼Àº °íµñ",0, 12));
		lb5.setFont(new Font("¸¼Àº °íµñ",0, 12));
		lb6.setFont(new Font("¸¼Àº °íµñ",0, 12));
		
		mgr.sql="select u_name,(select count(*) from user where concat(',',u_following,',') regexp(',"+Login.no+",')), u_following from user as u where u.u_no = "+Login.no;
		list=mgr.a(3);
		bean = list.get(0);
		lb1.setText(bean.getA().get(0) + "´Ô,");
		lb2.setText(bean.getA().get(1)+"ÆÈ·Î¿ö");
		lb3.setText((bean.getA().get(2).toString().split(",").length)+"ÆÈ·ÎÀ×");
		
		lb4.setBorder(new MatteBorder(0,0,1,0,Color.black));
		lb5.setBorder(new MatteBorder(0,0,1,0,Color.black));
		lb6.setBorder(new MatteBorder(0,0,1,0,Color.black));
		
		icon.setBounds(5,5,100,100);
		lb1.setBounds(110, 5, 150, 40);
		lb2.setBounds(110, 50, 80, 30);
		lb3.setBounds(200, 50, 80, 30);
		lb4.setBounds(0, 110 ,485, 30);
		lb5.setBounds(0, 150, 485, 30);
		lb6.setBounds(0, 190, 485, 30);
		icon1.setBounds(450, 110, 30,30);
		icon2.setBounds(450, 150, 30,30);
		icon3.setBounds(450, 190, 30,30);
		
		add(icon);
		add(lb1);
		add(lb2);
		add(lb3);
		add(lb4);
		add(lb5);
		add(lb6);
		add(icon1);
		add(icon2);
		add(icon3);
		
		lb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Main.all.removeAll();
				Main.all.add(new Follow(0));
				Main.all.updateUI();
			}
		});
		lb3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Main.all.removeAll();
				Main.all.add(new Follow(1));
				Main.all.updateUI();
			}
		});
		icon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				new Search(0);
				Main.main.setVisible(false);
				
			}
		});
		icon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				new Search(1);
				Main.main.setVisible(false);
			}
		});
		icon3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				new Login();
				Main.main.dispose();
			}
		});
	}
	
	public static void main(String[] args) {
		new Main();
		
	}

}
