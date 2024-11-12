package pro;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Write extends PanelTemplate{

	PlaceHolder tf1= new PlaceHolder("�������� �˻��ϼ���.");
	JScrollPane jsp = new JScrollPane();
	String co[] = {"��ȣ", "������ �̸�", "rno"};
	Object ro[][];
	JTable tb = new JTable();
	
	PlaceArea ta = new PlaceArea("������ �Է��ϼ���.");
	
	JLabel lb1= new JLabel("����");
	JTextField tf2 = new JTextField();
	JLabel lb2=  new JLabel();
	 
	JLabel img1 = new JLabel("+", JLabel.CENTER);
	JLabel img2 = new JLabel("+", JLabel.CENTER);
	 
	JLabel icon1 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/add.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	JLabel icon2 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/delete.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
	JLabel icon3 = new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/write.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

	JFileChooser chooser1;
	JFileChooser chooser2;
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	int ck = 0;
	
	public Write() {
		Login.no = "1";
		
		img1.setFont(new Font("���� ���",1,20));
		img2.setFont(new Font("���� ���",1,20));
		lb2.setFont(new Font("���� ���",1,20));
		
		ta.setBorder(new MatteBorder(1,0,1,0,Color.black));
		lb1.setFont(new Font("���� ���",0, 15));
		
		img1.setBorder(new LineBorder(Color.black));
		img2.setBorder(new LineBorder(Color.black));
		
		tf1.setBounds(10,5, 465, 30);
		jsp.setBounds(10, 40, 465, 100);
		ta.setBounds(0, 150, 485, 100);
		lb1.setBounds(100, 260, 50, 30);
		tf2.setBounds(150, 260, 150, 30);
		lb2.setBounds(150, 260, 150, 30);
		img1.setBounds(100, 300, 150,150);
		img2.setBounds(260, 300, 150,150);
		icon1.setBounds(410-30-20, 460,20,20);
		icon2.setBounds(410-30, 460,20,20);
		icon3.setBounds(410-30-20, 460,20,20);

		icon3.setVisible(false);
		lb2.setVisible(false);
		
		add(tf1);
		add(jsp);
		add(ta);
		add(lb1);
		add(tf2);
		add(lb2);
		add(img1);
		add(img2);
		add(icon1);
		add(icon2);
		add(icon3);
		
		set();
		
		img1.setName("");
		img2.setName("");
		
		
		tf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					set();
					
				}
			}
		});
		icon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tf1.getText().equals("")||img1.getName().equals("")||img2.getName().equals("")) {
					Msg.err("��ĭ�� �����մϴ�.");
					return;
				}
				if(tb.getSelectedRow() == -1) {
					Msg.err("Ÿ�Ӷ����� �� �������� �������ּ���.");
					return;
				}
				if(ta.getText().length()<10 || ta.getText().length()>=60) {
					Msg.err("Ÿ�Ӷ����� 10�� �̻� 60�� �̳��� ���ּ���.");
					return;
				}
				Msg.info("Ÿ�Ӷ��� �ۼ��� �Ϸ�Ǿ����ϴ�.");
				mgr.sql="insert into timeline(r_no, ti_review, ti_date, u_no, ti_star) values('"+tb.getValueAt(tb.getSelectedRow(), 0)+"', '"+ta.getText()+"', '"+sf.format(new Date())+"','"+Login.no+"', '"+tf2.getText()+"')";
				mgr.update();
			}
		});
		icon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tb.getSelectedRow()!=-1) {
					if(tb.getValueAt(tb.getSelectedRow(), 2).equals("1")) {
						Msg.info("Ÿ�Ӷ����� ���� �Ǿ����ϴ�.");
						mgr.sql="delete from timeline where u_no ="+Login.no+" and r_no = "+tb.getValueAt(tb.getSelectedRow(), 0);
						mgr.update();
						set();
						set2();
					}else {
						set2();
					}
				}
			}
		});
		
		icon3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				mgr.sql="select * from timeline where r_no="+tb.getValueAt(tb.getSelectedRow(), 0)+" and u_no="+Login.no;
				list=mgr.a(3);
				bean = list.get(0);
				System.out.println(bean.getA().get(2));
				if(img1.getName().equals("")&&img2.getName().equals("")&&ta.getText().equals(bean.getA().get(2)+"")) {
					Msg.err("������ ������ �����ϴ�.");
					return;
				}
				if(ta.getText().equals("") ||ta.getText().length()<10 || ta.getText().length()>=60) {
					Msg.err("�Է� ����� �����ּ���.");
					return;
				}
				Msg.info("Ÿ�Ӷ��� ������ �Ϸ�Ǿ����ϴ�.");
				mgr.sql="update timeline set ti_date ='"+sf.format(new Date())+"', ti_review='"+ta.getText()+"' where r_no="+tb.getValueAt(tb.getSelectedRow(), 0)+" and u_no="+Login.no;
				System.out.println(mgr.sql);
				mgr.update();
				
				return;
			}
		});
		
		img1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				chooser1 = new JFileChooser();
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("JPG ", "jpg");
				 chooser1.setFileFilter(filter1);
				if(chooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					img1.setText("!");
					String path1 = chooser1.getSelectedFile().getPath();
					img1.setIcon(new ImageIcon(new ImageIcon(path1).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
				}
			}
		});
		img2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				chooser2 = new JFileChooser();
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JPG ", "jpg");
				chooser2.setFileFilter(filter2);
				if(chooser2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					img2.setText("!");
					String path2 = chooser2.getSelectedFile().getPath();
					img2.setIcon(new ImageIcon(new ImageIcon(path2).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
					
				}
			}
		});
		
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tb.getSelectedRow()>-1) {
					
					if(tb.getValueAt(tb.getSelectedRow(), 2).equals("1")) {
						set1();
						
					}else {
						set2();
					}
					ck = 1;
				}
			}
		});
	}
	
	void set2() {
		if(ck == 0) {
			return;
		}
		
		img1.setText("+");
		img2.setText("+");
		ta.setText("");
		tf2.setText("");
		img1.setIcon(null);
		img2.setIcon(null);
		
		tf2.setVisible(true);
		icon1.setVisible(true);
		lb2.setVisible(false);
		icon3.setVisible(false);
	}
	
	void set1() {
		
		tf2.setVisible(false);
		icon1.setVisible(false);
		lb2.setVisible(true);
		icon3.setVisible(true);
		
		img1.setText("");
		img2.setText("");
		
		mgr.sql="select ti_no, ti_review, ti_star from timeline where u_no="+Login.no +" and r_no="+tb.getValueAt(tb.getSelectedRow(), 0);
		list=mgr.a(3);
		bean=list.get(0);
		String t= "";
		for(int i=0; i<5; i++) {
			if(i < (int)(float)bean.getA().get(2)) {
				t+="��";
			}else {
				t+="��";
			}
		}
		lb2.setForeground(Color.red);
		lb2.setText(t);
		
		img1.setIcon(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean.getA().get(0)+"-1.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		img2.setIcon(new ImageIcon(new ImageIcon("./datafiles/review/tino"+bean.getA().get(0)+"-2.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		ta.setText(bean.getA().get(1)+"");
	}
	
	void set() {
		DefaultTableModel model = new DefaultTableModel(ro, co) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tb = new JTable(model) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				// TODO Auto-generated method stub
				JComponent c =  (JComponent)super.prepareRenderer(renderer, row, column);
				c.setForeground(Color.BLACK);
				if(tb.getValueAt(row, 2).equals("1")) {
					c.setForeground(Color.cyan);
				}
				return c;
			}
		};
		
		DefaultTableCellRenderer dtc = new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(SwingUtilities.CENTER);
		
		
		mgr.sql="select r_no, r_name, (select count(*) from timeline where u_no = "+Login.no+" and r_no = r.r_no) from restaurant as r";
		list=mgr.a(3);
		if(list.size() == 0) {
			Msg.err("�ش� ������ �������� �������� �ʽ��ϴ�.");
		}
		
		for(int i=0; i<list.size(); i++) {
			bean=list.get(i);
			model.addRow(new Object[] {bean.getA().get(0)+"", bean.getA().get(1)+"", bean.getA().get(2)+""});
		}
		int w[] = {10, 200, 0};
		for(int i=0; i<tb.getColumnCount(); i++) {
			tb.getColumn(co[i]).setPreferredWidth(w[i]);
			tb.getColumn(co[i]).setCellRenderer(dtc);
			
		}
		tb.getColumn(co[2]).setMinWidth(0);
		tb.getColumn(co[2]).setMaxWidth(0);
		
		jsp.setViewportView(tb);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
