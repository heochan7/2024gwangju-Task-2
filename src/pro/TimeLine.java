package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class TimeLine extends PanelTemplate{

	JScrollPane jsp = new JScrollPane();
	JPanel all = new JPanel(null);
	JPanel a1[] = new JPanel[99];
	JLabel a2[] = new JLabel[99];
	JLabel a3[] = new JLabel[99];
	JLabel img1[] = new JLabel[99];
	JLabel img2[] = new JLabel[99];
	JLabel a4[] = new JLabel[99];
	JLabel a5[] = new JLabel[99];
	JPanel bb1[] = new JPanel[99];
	JLabel bb2[] = new JLabel[99];
	JLabel bb3[] = new JLabel[99];
	
	public TimeLine() {
		jsp.setBounds(0,0, 485, 500);
		
		add(jsp);
		
		set();
	}
	
	void set() {
		Login.no = "1";
		
		all.setBackground(Color.white);
		
		mgr.sql="select u_following from user where u_no="+Login.no;
		list=mgr.a(1);
		bean  =list.get(0);
		
		mgr.sql="select t.ti_no, u_name, ti_review, datediff(now(), ti_date), r.r_no, r_name from timeline as t join restaurant as r on r.r_no = t.r_no join user as u on u.u_no = t.u_no where u.u_no in ("+bean.getA().get(0)+") order by ti_date desc";
		list=mgr.a(6);
		
		int y = 0;
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			a1[i]=new JPanel(null);
			a2[i]=new JLabel(bean.getA().get(1)+"");
			a3[i]=new JLabel(bean.getA().get(2)+"");
			img1[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean.getA().get(0)+"-1.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			img2[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean.getA().get(0)+"-2.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
			a4[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/noheart.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			a5[i]=new JLabel(bean.getA().get(3)+"ÀÏ Àü");
			bb1[i]=new JPanel(null);
			bb2[i]=new JLabel(bean.getA().get(5)+"");
			bb3[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/pinwhite.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))) {
				@Override
				public void paint(Graphics g) {
					// TODO Auto-generated method stub
					super.paint(g);
					g.setColor(Color.gray);
					g.drawArc(0, 0, 30, 30, 0, 360);
				}
			};
			mgr.sql="select * from alim where substring_index(a_heart, ',', 1) = "+Login.no+ " and ti_no="+bean.getA().get(0);
			list1= mgr.a(1);
			a4[i].setName("");
			
			if(list1.size() !=  0) {
				a4[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/heart.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
				a4[i].setName("!");
			}
			mgr.sql="select * from user where u_no= 1 and u_pin regexp (',"+bean.getA().get(4)+",')";
			list1=mgr.a(1);
			bb3[i].setName("");
			if(list1.size()!=0) {
				bb3[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/pinblack.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
				bb3[i].setName("!");
			}
			
			a2[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/mypage.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			bb2[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			
			a2[i].setFont(new Font("¸¼Àº °íµñ",1,12));
			a3[i].setFont(new Font("¸¼Àº °íµñ",1,12));
			
			a5[i].setFont(new Font("¸¼Àº °íµñ",1,12));
			
			a1[i].setOpaque(false);
			a1[i].setBorder(new MatteBorder(0,0,1,0,Color.black));
			
			a1[i].setBounds(0, y, 485, 350);
			a2[i].setBounds(5,5,100, 30);
			a3[i].setBounds(30, 40, 465, 30);
			img1[i].setBounds(5, 80, 200, 200);
			img2[i].setBounds(250, 80, 200, 200);
			a4[i].setBounds(485-120, 5, 30,30);
			a5[i].setBounds(485-80, 5, 70, 30);
			bb1[i].setBounds(5, 290, 450, 50);
			bb2[i].setBounds(5,5, 200, 30);
			bb3[i].setBounds(400,5, 30,30);
			
			all.add(a1[i]);
			a1[i].add(a2[i]);
			a1[i].add(a3[i]);
			a1[i].add(img1[i]);
			a1[i].add(img2[i]);
			a1[i].add(a4[i]);
			a1[i].add(a5[i]);
			a1[i].add(bb1[i]);
			bb1[i].add(bb2[i]);
			bb1[i].add(bb3[i]);
			
			y+=350;
			
			int n = i;
			a4[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					if(a4[n].getName().equals("")) {
						a4[n].setName("!");
						a4[n].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/heart.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
						
					}else {
						a4[n].setName("");
						a4[n].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/noheart.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
						
					}
				}
			});
			bb3[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					if(bb3[n].getName().equals("")) {
						bb3[n].setName("!");
						bb3[n].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/pinblack.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
					}else {
						bb3[n].setName("");
						bb3[n].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/pinwhite.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
					}
				}
			});
		}
		jsp.setViewportView(all);
		all.setPreferredSize(new Dimension(0, y));
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
