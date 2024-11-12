package pro;

import javax.swing.JOptionPane;

public class Msg {
	public static void err(String a) {
		JOptionPane.showMessageDialog(null, a, "경고", JOptionPane.ERROR_MESSAGE);
	}
	public static void info(String a) {
		JOptionPane.showMessageDialog(null, a, "정보", JOptionPane.INFORMATION_MESSAGE);
	}
}
