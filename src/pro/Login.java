package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class Login extends Template{
	
	static String no  ="";
	
	
	JLabel title= new JLabel("LOGIN", JLabel.CENTER);
	PlaceHolder tf1 = new PlaceHolder("Id");
	PlaceHolder tf2 = new PlaceHolder("Password");
	JLabel lb1 = new JLabel("�α���", JLabel.RIGHT);

	public Login() {

		title.setFont(new Font("���� ���",1,20));
		lb1.setFont(new Font("���� ���",1,12));
		
		title.setForeground(new Color(100, 200, 200));
		lb1.setForeground(new Color(100, 200, 200));
		
		tf1.setBorder(new MatteBorder(0,0,1,0,Color.black));
		tf2.setBorder(new MatteBorder(0,0,1,0,Color.black));
		
		
		title.setBounds(0, 10, 385, 30);
		tf1.setBounds(30, 60, 325, 30);
		tf2.setBounds(30, 100, 325, 30);
		lb1.setBounds(355-100, 140, 100, 30);
		
		add(title);
		add(tf1);
		add(tf2);
		add(lb1);
		
		setSize(400, 300);
		setVisible(true);
		setTitle("�α���");
		
		lb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tf1.getText().equals("")||tf2.getText().equals("")) {
					Msg.err("��ĭ�� �����մϴ�.");
					return;
				}
				
				mgr.sql="select u_no, u_name from user where id='"+tf1.getText()+"' and pw='"+tf2.getText()+"'";
				list=mgr.a(2);
				if(list.size() == 0) {
					Msg.err("�����ϴ� ȸ���� �����ϴ�.");
					return;
				}
				bean = list.get(0);
				no = bean.getA().get(0)+"";
				Msg.info(bean.getA().get(1) + "��, �α��εǾ����ϴ�.");
				new Main();
				dispose();
			}
		});
	}
	
	
	public static void main(String[] args) {
		new Login();
	}
}
