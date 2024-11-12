package pro;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Template extends JFrame{
	
	DBMgr mgr = new DBMgr();
	ArrayList<Bean>list;;
	Bean bean;
	
	public Template() {
		setLayout(null);
		getContentPane().setBackground(Color.white);
	}
}
