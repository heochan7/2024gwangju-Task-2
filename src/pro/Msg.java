package pro;

import javax.swing.JOptionPane;

public class Msg {
	public static void err(String a) {
		JOptionPane.showMessageDialog(null, a, "���", JOptionPane.ERROR_MESSAGE);
	}
	public static void info(String a) {
		JOptionPane.showMessageDialog(null, a, "����", JOptionPane.INFORMATION_MESSAGE);
	}
}
