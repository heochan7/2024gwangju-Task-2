package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Home extends PanelTemplate{
	
	JScrollPane jsp1 = new JScrollPane();
	JLabel map = new JLabel(new ImageIcon(new ImageIcon("./datafiles/map.jpg").getImage().getScaledInstance(660, 800, Image.SCALE_SMOOTH)));
	
	JLabel ico[] = new JLabel[99];
	
	JButton btn1 = new JButton("내 주변 맛집 찾기");
	
	JLabel person = new JLabel();
	
	JLabel icon = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/mainicon.png").getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH)));
	
	JScrollPane jsp2 = new JScrollPane();
	JPanel all = new JPanel(null);
	JPanel a1[] = new JPanel[99];
	JLabel a2[] = new JLabel[99];
	JLabel a3[] = new JLabel[99];
	JLabel a4[] = new JLabel[99];
	JLabel a5[] = new JLabel[99];
	JLabel a6[] = new JLabel[99];
	JLabel img1[] = new JLabel[99];
	JLabel img2[] = new JLabel[99];
	JLabel img3[] = new JLabel[99];
	JLabel img4[] = new JLabel[99];
	
	String q[] = {"전체","한식", "일식", "중식","양식", "카페"};
	JComboBox cb1=  new JComboBox(q);
	
	Timer timer = new Timer();
	
	int t= 0;
	
	public Home() {
		person = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/person.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))) {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.setColor(new Color(255, 0, 0, 100));
				g.fillArc(0, 0, 199, 199, 90, t);
				g.setColor(new Color(255, 0, 0, 200));
			}
		};
		
		btn1.setBackground(new Color(100, 200, 200));
		btn1.setForeground(Color.white);
		
		jsp1.setBounds(0,0,485, 500);
		btn1.setBounds(5, 500-50, 150, 30);
		icon.setBounds(485-90, 500-80, 70, 60);
		jsp2.setBounds(0, 200, 485, 300);
		
		set();
		
		jsp2.setVisible(false);
	
		add(btn1);
		add(icon);
		
		add(jsp1);
		add(jsp2);
		
		map.add(person);
		
		jsp1.setViewportView(map);
		
		AdjustmentListener li = e->{
			btn1.repaint();
			icon.repaint();
		};
		
		jsp1.getHorizontalScrollBar().addAdjustmentListener(li);
		jsp1.getVerticalScrollBar().addAdjustmentListener(li);
	
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(btn1.getText().equals("종료하기")) {
					person.setBounds(e.getX()-100, e.getY()-100, 200, 200);
					for(int i=0; i<ico.length; i++) {
						ico[i].setVisible(false);
					}
					
					person.setVisible(true);
					
					t = 0;
					if(timer!=null) {
						timer.cancel();
					}
					timer= new Timer();
					
					TimerTask task = new TimerTask() {
						@Override
						public void run() {
							if(t > -360) {
								t--;
								for(int i=0; i<ico.length; i++) {
									int x = ico[i].getX() - e.getX();
									int y = ico[i].getY() - e.getY();
									if(Math.sqrt(x*x + y*y) <= 100) {
										double an = Math.toDegrees(Math.atan2(y, x));
										if(an<=0) {
											an += 360;
										}
										if(t >= -90) {
											if(an >= 270 && an<=270 -t) {
												ico[i].setVisible(true);
											}
										} else {
											if((an>= 270 && an <= 270) ||an <= Math.abs(t+90)) {
												ico[i].setVisible(true);
											}
										}
									}
								}
								person.repaint();
							}else {
								timer .cancel();
							}
							
						}
					};
					timer.schedule(task, 0, 10);
				}
			}
		});
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btn1.getText().equals("내 주변 맛집 찾기")) {
					Msg.info("내 주변 맛집찾기를 시작합니다. 지도를 클릭해 위치를 선정해주세요!");
					btn1.setText("종료하기");
					for(int i=0; i<ico.length; i++) {
						ico[i].setVisible(false);
					}
					
				}else {
					Msg.info("맛집 찾기가 종료되었습니다.");
					btn1.setText("내 주변 맛집 찾기");
					person.setVisible(false);
					for(int i=0; i<ico.length; i++) {
						ico[i].setVisible(true);
					}
					
				}
			}
		});
		
		icon.setName("");
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(icon.getName().equals("")) {
					icon.setName("!");
					
					set1();
				}else {
					icon.setName("");
					set2();
				}
			}
		});
		cb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set3();
			}
		});
	}
	
	void set1() {
		int cnt = 0;
		for(int i=0; i<ico.length; i++) {
			if(ico[i].isVisible()) {
				cnt ++;
			}
		}
		if(cnt==0) {
			Msg.err("주위에 맛집이 없는 것 같아요. 다른곳으로 위치를 찍어주세요.");
			return;
		}
		
		jsp1.setBounds(0, 0, 485, 200);
		icon.setBounds(485-90, 200-80, 70, 60);
		
		jsp2.setVisible(true);
		btn1.setVisible(false);
		
		set3();
	}
	
	void set2() {
		
		jsp1.setBounds(0,0,485, 500);
		icon.setBounds(485-90, 500-80, 70, 60);
		
		jsp2.setVisible(false);
		btn1.setVisible(true);
	}
	
	void set3() {
		
		all.removeAll();
		
		all.add(cb1);
		cb1.setBounds(485-120, 5, 100, 30);
		
		String order = "";
		if(cb1.getSelectedIndex() != 0) {
			order = " and r_type ="+cb1.getSelectedIndex();
		}
		mgr.sql="select r.r_no, r_name, round(avg(ti_star),1), count(t.ti_no),r.r_type, if(time_format(now(), '%H:%i') > time_format(r_open, '%H:%i') and time_format(now(), '%H:%i') < time_format(r_close, '%H:%i'), '영업 중', '영업 종료') from timeline as t join restaurant as r on r.r_no = t.r_no "+order+" group by r.r_no ";
		list=mgr.a(6);
		
		int y = 0;
		
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			a1[i]=new JPanel(null);
			a2[i]=new JLabel(bean.getA().get(1) + "");
			a3[i]=new JLabel("별점 : "+bean.getA().get(2) + "");
			a4[i]=new JLabel("평가 : "+bean.getA().get(3) + "명");
			a5[i]=new JLabel(q[(int)bean.getA().get(4)] + "");
			a6[i]=new JLabel(bean.getA().get(5) + "");
			
			a5[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
			
			mgr.sql="select ti_no from timeline where r_no = "+bean.getA().get(0)+" order by ti_date desc limit 2";
			list1=mgr.a(1);
			if(list1.size() >0) {
				bean1 = list1.get(0);
				img1[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean1.getA().get(0)+"-1.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				img2[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean1.getA().get(0)+"-2.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				img1[i].setBounds(5, 110, 100, 100);
				img2[i].setBounds(120, 110, 100, 100);
				a1[i].add(img1[i]);
				a1[i].add(img2[i]);
			}
			if(list1.size() > 1) {
				bean1 = list1.get(1);
				img3[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean1.getA().get(0)+"-1.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				img4[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean1.getA().get(0)+"-2.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
				img3[i].setBounds(230, 110, 100, 100);
				img4[i].setBounds(340, 110, 100, 100);
				a1[i].add(img3[i]);
				a1[i].add(img4[i]);
			}
			
			a2[i].setFont(new Font("맑은 고딕", 1,12));
			a3[i].setFont(new Font("맑은 고딕", 1,12));
			a4[i].setFont(new Font("맑은 고딕", 1,12));
			a5[i].setFont(new Font("맑은 고딕", 1,12));
			
			a1[i].setBounds(0, y, 485, 200);
			a2[i].setBounds(5,5,200, 30);
			a3[i].setBounds(5, 40, 80,30);
			a4[i].setBounds(90, 40, 80,30);
			a5[i].setBounds(5, 70, 80,30);
			a6[i].setBounds(90, 70, 80,30);
			
			all.add(a1[i]);
			a1[i].add(a2[i]);
			a1[i].add(a3[i]);
			a1[i].add(a4[i]);
			a1[i].add(a5[i]);
			a1[i].add(a6[i]);
			if(ico[i].isVisible()) {
				y+=200;
			}
		}
		jsp2.setViewportView(all);
		all.setPreferredSize(new Dimension(0, y));
		all.setPreferredSize(new Dimension(0, y));
		all.updateUI();
	}
	
	void set() {
		mgr.sql="SELECT r_no, x, y FROM restaurant;";
		list=mgr.a(3);
		ico=new JLabel[list.size()];
		for(int i=0; i<list.size(); i++) {
			bean  = list.get(i);
			ico[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/icon.png").getImage().getScaledInstance(20, 20,Image.SCALE_SMOOTH)));
			ico[i].setName(bean.getA().get(0)+"");
			ico[i].setBounds((int)bean.getA().get(1), (int)bean.getA().get(2), 20,20);
			map.add(ico[i]);
			
			int n = i;
			ico[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					new Info(ico[n].getName());
					Main.main.dispose();
				}
			});
		}
	}
	
	public static void main(String [] args) {
		new Main();
	}
}
