package pro;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelTemplate extends JPanel{
	
	DBMgr mgr= new DBMgr();
	ArrayList<Bean >list, list1;
	Bean bean, bean1;
	
	public PanelTemplate() {
		
		setLayout(null);
		setSize(485, 500);
		setVisible(true);
		setBackground(Color.white);
	}
}
