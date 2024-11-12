package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Radar extends Template{
	
	JScrollPane jsp = new JScrollPane();
	JLabel map = new JLabel(new ImageIcon(new ImageIcon("./datafiles/map.jpg").getImage().getScaledInstance(660, 800, Image.SCALE_SMOOTH)));
	JLabel icon[] = new JLabel[99];
	
	JLabel person = new JLabel();
	
	JButton btn1 =new JButton("버튼 클릭");
	
	Timer timer =new Timer();
	
	
	int t = 0;
	public Radar() {
		person = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/person.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))) {
			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				g.setColor(new Color(255, 0,0, 100));
				g.fillArc(0, 0, 199, 199, 90, t);
				g.setColor(new Color(255, 0,0, 200));
				g.drawArc(0, 0, 199, 199, 90, t);
			}
		};

		
		jsp.setBounds(0, 0,485, 500);
		btn1.setBounds(5, 500, 100, 30);
		
		add(jsp);
		add(btn1);
		jsp.setViewportView(map);
		
		
		mgr.sql="select r_no, x, y  from restaurant";
		list=mgr.a(3);
		icon = new JLabel[list.size()];
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			icon[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
			icon[i].setName(bean.getA().get(0)+"");
			icon[i].setBounds(Integer.parseInt(bean.getA().get(1)+""), Integer.parseInt(bean.getA().get(2)+""), 20, 20);
			map.add(icon[i]);
		}
		
		map.add(person);
		person.setVisible(false);
		
		setSize(500, 600);
		setVisible(true);
		setTitle("레이더");
		
		map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				
				if(btn1.getText().equals("찾기")) {
					person.setVisible(true);
					person.setBounds(e.getX()-100, e.getY()-100, 200, 200);
					t= 0;
					for(int i=0; i<icon.length; i++) {
						icon[i].setVisible(false);
					}
					if(timer!=null) {
						timer.cancel();
					}
					
					timer =new Timer();
					TimerTask task = new TimerTask() {
						
						@Override
						public void run() {
							if(t  > -360) {
								t--;
								
								for(int i=0; i<icon.length; i++) {
									int x = icon[i].getX() - e.getX();
									int y = icon[i].getY() - e.getY();
									
									if(Math.sqrt(x*x + y*y)<=100) {
										double r = Math.toDegrees(Math.atan2(y, x));
										if(r<=0) {
											r+=360;
										}
										if(t>-90) {
											if((r>=270 && r<=270-t)) {
												icon[i].setVisible(true);
											}
										}else  {
											if((r>=270 && r<=270-t) || r <= Math.abs(t + 90)) {
												icon[i].setVisible(true);
											}
										}
									}
								}
								person.repaint();
							}else {
								timer.cancel();
							}
						}
					};
					timer .schedule(task, 0, 10);
					
				}
			}
		});
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(btn1.getText().equals("버튼 클릭")) {
					btn1.setText("찾기");
					for(int i=0; i<icon.length; i++) {
						icon[i].setVisible(false);
					}
				}else {
					btn1.setText("버튼 클릭");
					person.setVisible(false);
					for(int i=0; i<icon.length; i++) {
						icon[i].setVisible(true);
					}
					
				}
			}
		});
	}

	public static void main(String[] args) {
		new Radar();
	}

}
