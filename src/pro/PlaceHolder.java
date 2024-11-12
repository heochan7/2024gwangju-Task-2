package pro;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class PlaceHolder extends JTextField{
	String t;
	public PlaceHolder(String a) {
		t = a;
		repaint();
		
		addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				t = a;
				repaint();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				t = "";
				repaint();
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		if(getText().equals("")) {
			g.drawString(t, 5, 20);
		}
	}
}
