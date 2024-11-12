package pro;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class Main extends Template{

	static Main main;
	
	JPanel p1 = new JPanel(new GridLayout(1,5));
	String q[] = {"home", "timeline", "write", "bell", "mypage"};
	JLabel lb[] = new JLabel[5];
	
	static JPanel all = new JPanel(null);
	
	int select = 0;
	
	public Main() {
		main = this;
		
		all.setOpaque(false);
		p1.setOpaque(false);
		
		all.setBounds(0,0, 485, 500);
		p1.setBounds(0, 500, 485, 40);
		
		for(int i=0; i<q.length; i++) {
			lb[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/"+q[i]+".png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			lb[i].setBorder(new MatteBorder(1,0,0,0,Color.black));
			p1.add(lb[i]);
			
			int n = i;
			lb[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					set(n);
				}
			});
		}
		
		add(all);
		add(p1);

		set(select);
		
		setSize(500, 580);
		setVisible(true);
	}
	
	void set(int n) {
		lb[select].setBorder(new MatteBorder(1,0,0,0,Color.black));
		select = n;
		lb[select].setBorder(new MatteBorder(1,0,0,0,new Color(100, 200,200)));
		
		all.removeAll();
		
		if(select == 0) {
			setTitle("메인");
			all.add(new Home());
		}else if(select == 1) {
			setTitle("타임라인");
			all.add(new TimeLine());
		}else if(select == 2) {
			setTitle("글쓰기");
			all.add(new Write());
		}else if(select ==3) {
			setTitle("알람");
		}else {
			setTitle("마이페이지");
			all.add(new MyPage());
		}
		all.updateUI();
	}
	
	
	public static void main(String[] args) {
		new Main();
		
	}

}
