package org.virtualwar.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class InitGui {

	public static JComboBox<String> getComboFromInt(int start, int end){
		String[] lsInt = new String[end-start];
		for (int i = 0; i < end-start; i++) {
			lsInt[i] = "" + start+i;
		}
		
		return new JComboBox<String>(lsInt);
	}
	
	public static String getLangFromUser() {
		String[] options = { "OK" };
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("Language : ");

		String[] lsLang = { "Français", "English" };

		JComboBox<String> boxLang = new JComboBox<String>(lsLang);
		panel.add(lbl);
		panel.add(boxLang);
		int selectedOption = JOptionPane.showOptionDialog(null, panel,
				"Select your language", JOptionPane.NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (selectedOption == 0) {
			String text = (String) boxLang.getSelectedItem();
			return text.toLowerCase().substring(0, 2);
		}
		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(getLangFromUser());
				new InitGui();
			}
		});
	}

	public InitGui() {
		// TODO Auto-generated constructor stub
	}

}
