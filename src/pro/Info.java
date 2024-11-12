package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Info extends JDialog{

	JPanel pp = new JPanel(null);
	
	JLabel title = new JLabel("");
	JLabel lb1 = new JLabel("À§Ä¡º¸±â");
	JLabel img1 = new JLabel();
	JLabel img2 = new JLabel();
	
	JLabel a1 = new JLabel();
	JLabel a2 = new JLabel();
	JLabel a3 = new JLabel();
	JLabel a4 = new JLabel();
	JLabel a5 = new JLabel();
	JLabel a6 = new JLabel();
	
	JScrollPane jsp=  new JScrollPane();
	JPanel all = new JPanel(null);
	JPanel b1[] = new JPanel[99];
	JLabel b2[] = new JLabel[99];
	JLabel b3[] = new JLabel[99];
	
	JLabel lb2=  new JLabel("¸®ºä");
	
	DBMgr mgr = new DBMgr();
	ArrayList<Bean>list;
	Bean bean;
	
	String no ="";
	
	public Info(String no ) {
		this.no = no;

		setLayout(null);
		
		setBackground(Color.white);
		
		mgr.sql="select r_name, img1, img2, round(avg(t.ti_star),1), count(t.ti_no), r_rest, time_format(r_open, '%p %h:%i'),time_format(r_close, '%p %h:%i'), r_type from timeline as t join restaurant as r on r.r_no = t.r_no where r.r_no ="+no+" group by r.r_no ";
		list=mgr.a(9);
		bean = list.get(0);
		
		String q[] = {"ÇÑ½Ä", "ÀÏ½Ä", "Áß½Ä", "¾ç½Ä", "Ä«Æä"};
		String q1[] = {"ÈÞ¹« ¾øÀ½", "¿ù", "È­", "¼ö", "¸ñ", "±Ý", "Åä", "ÀÏ"};
		
		title.setText(bean.getA().get(0)+"");
		img1.setIcon(new ImageIcon(new ImageIcon((byte[])bean.getA().get(1)).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		img2.setIcon(new ImageIcon(new ImageIcon((byte[])bean.getA().get(2)).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		a1.setText("º°Á¡ " +bean.getA().get(3)+"");
		a2.setText("Æò°¡ "+bean.getA().get(4)+" ¸í");
		String t = "";
		a3.setText(q1[(int)bean.getA().get(5)]);
		if((int)bean.getA().get(5) != 0) {
			a3.setText(q1[(int)bean.getA().get(5)]+"¿äÀÏ ÈÞ¹«");
		}
		 
		
		jsp.setBorder(null);
		pp.setBackground(Color.white);
		
		title.setFont(new Font("¸¼Àº °íµñ",1,30));
		a1.setFont(new Font("¸¼Àº °íµñ",1,12));
		a2.setFont(new Font("¸¼Àº °íµñ",1,12));
		a3.setFont(new Font("¸¼Àº °íµñ",1,12));
		a4.setFont(new Font("¸¼Àº °íµñ",1,12));
		a5.setFont(new Font("¸¼Àº °íµñ",1,12));
		a6.setFont(new Font("¸¼Àº °íµñ",1,12));
		lb1.setFont(new Font("¸¼Àº °íµñ",1,12));
		lb2.setFont(new Font("¸¼Àº °íµñ",1,12));
		
		lb1.setForeground(new Color(100, 200, 200));
		
		a4.setText("OPEN "+bean.getA().get(6)+"");
		a5.setText("CLOSE "+bean.getA().get(7)+"");
		a6.setText(q[(int)bean.getA().get(8)-1]+"");

		a6.setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		
		title.setFont(new Font("¸¼Àº °íµñ",1,30));
		
		title.setBounds(5,0, 380, 40);
		img1.setBounds(20, 50, 150, 150);
		img2.setBounds(180, 50, 150, 150);
		lb1.setBounds(385-110, 20, 100, 30);
		a1.setBounds(5, 210, 60, 30);
		a2.setBounds(70, 210, 60, 30);
		a3.setBounds(5, 240, 80, 30);
		a4.setBounds(90, 240, 100, 30);
		a5.setBounds(190, 240, 100, 30);
		a6.setBounds(5, 270, 100, 30);
		jsp.setBounds(0, 300, 384, 200);
		
		pp.setBounds(0,0, 385, 500);
		
		add(pp);
		
		pp.add(title);
		pp.add(img1);
		pp.add(img2);
		pp.add(lb1);
		pp.add(a1);
		pp.add(a2);
		pp.add(a3);
		pp.add(a4);
		pp.add(a5);
		pp.add(a6);
		pp.add(jsp);
		
		set();
	
		setSize(400, 540);
		setVisible(true);
		setTitle("À½½ÄÁ¡ Á¤º¸");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new Main();
				dispose();
			}
		});
	}
	
	void set() {
		all.removeAll();
		all.setBackground(Color.white);
		
		lb2.setBounds(10, 0, 100, 30);
		lb2.setForeground(new Color(100, 200, 200));
		
		all.add(lb2);
		
		int y = 30;
		mgr.sql="select u_name, ti_review from timeline as t join user as u on u.u_no = t.u_no where r_no = "+no;
		list=mgr.a(2);
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			b1[i]=new JPanel(null);
			b2[i]=new JLabel(bean.getA().get(0)+"");
			b3[i]=new JLabel(bean.getA().get(1)+ "");
			
			b1[i].setBounds(0, y, 385, 60);
			b2[i].setBounds(10, 0, 200, 30);
			b3[i].setBounds(10, 30, 385, 30);
			
			b1[i].setOpaque(false);
			b2[i].setFont(new Font("¸¼Àº °íµñ",1,12));
			b3[i].setFont(new Font("¸¼Àº °íµñ",1,12));
			
			b2[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/mypage.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			
			all.add(b1[i]);
			b1[i].add(b2[i]);
			b1[i].add(b3[i]);
			
			y+=60;
		}
		jsp.setViewportView(all);
		all.setPreferredSize(new Dimension(0, y));
		all.updateUI();
	}
	
	public static void main(String[] args) {
		new Info("1");
	}

}
