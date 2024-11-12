package pro;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Follow extends PanelTemplate{
	
	JLabel lb1 = new JLabel();
	
	JScrollPane jsp = new JScrollPane();
	String co[] = {"¹øÈ£", "ÀÌ¸§","ID"};
	Object ro[][];
	JTable tb = new JTable();
	JLabel icon = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/next.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
	
	int n ;
	
	public Follow(int n) {
		
		this.n = n;
		
		lb1.setFont(new Font("¸¼Àº °íµñ",1,20));
		
		if(n==0) {
			mgr.sql="select count(*) from user where concat(',',u_following,',') regexp(',"+Login.no+",')";
			list=mgr.a(1);
			bean =list.get(0);
			
			lb1.setText("ÆÈ·Î¿ö"+bean.getA().get(0)+"¸í");
		}else {
			mgr.sql="select u_following from user where u_no="+Login.no;
			list=mgr.a(1);
			bean = list.get(0);
			
			lb1.setText("ÆÈ·ÎÀ×"+bean.getA().toString().split(",").length +"¸í");
		}
		
		lb1.setBounds(10,10,100, 30);
		icon.setBounds(485-40, 20, 30,30);
		jsp.setBounds(10, 60, 465, 400);
		
		add(lb1);
		add(icon);
		add(jsp);
		
		set();
		
		icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				Main.all.removeAll();
				Main.all.add(new MyPage());
				Main.all.updateUI();
			}
		});
	}
	
	void set() {
		DefaultTableModel model = new DefaultTableModel(ro, co) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(SwingUtilities.CENTER);
		
		tb = new JTable(model);
		
		if(n == 0) {
			mgr.sql="select u_name, id from user where concat(',',u_following,',') regexp(',"+Login.no+",')";
		}else {
			mgr.sql="select u_following from user where u_no="+Login.no;
			list=mgr.a(1);
			bean = list.get(0);
			mgr.sql="select u_name, id from user where u_no in ("+bean.getA().get(0)+")";
		}
		list=mgr.a(2);
		for(int i=0; i<list.size(); i++) {
			bean = list.get(i);
			model.addRow(new Object[] {(i+1), bean.getA().get(0)+"", bean.getA().get(1)+""});
		}
		int w[] = {10, 100, 100};
		
		tb.setRowHeight(30);
		
		for(int i=0; i<tb.getColumnCount(); i++) {
			tb.getColumn(co[i]).setPreferredWidth(w[i]);
			tb.getColumn(co[i]).setCellRenderer(dtc);
		}
		jsp.setViewportView(tb);
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
