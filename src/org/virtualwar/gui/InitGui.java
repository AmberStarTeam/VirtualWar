/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.virtualwar.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// TODO: Auto-generated Javadoc
/**
 * The InitGui Class.
 *
 * @author Nicolas Beaussart
 */
public class InitGui {

	/**
	 * Gets the combo from int.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the combo from int
	 */
	public static JComboBox<String> getComboFromInt(int start, int end) {
		String[] lsInt = new String[end - start];
		for (int i = 0; i < end - start; i++) {
			lsInt[i] = "" + start + i;
		}

		return new JComboBox<String>(lsInt);
	}

	/**
	 * Gets the lang from user.
	 *
	 * @return the lang from user
	 */
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

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(getLangFromUser());
				new InitGui();
			}
		});
	}

	/**
	 * Instantiates a new inits the gui.
	 */
	public InitGui() {
		// TODO Auto-generated constructor stub
	}

}
