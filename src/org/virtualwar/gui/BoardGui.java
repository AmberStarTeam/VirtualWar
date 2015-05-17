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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.virtualwar.board.Board;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * The BoardGui Class.
 *
 * @author Nicolas Beaussart
 */
public class BoardGui extends JFrame {

	// A faire
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

	}

	/**
	 * Instantiates a new board gui.
	 *
	 * @param board
	 *            the board
	 */
	public BoardGui(Board board) {
		this.setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.add(initBoard(board));
		pack();
		setVisible(true);
	}

	// Creer un JPanel contenant un Gridbag de cases imagees pour representer le
	// plateau de jeu selon
	// un plateau entre en paramètre.
	/**
	 * Inits the board.
	 *
	 * @param board
	 *            the board
	 * @return the j panel
	 */
	public JPanel initBoard(Board board) {
		JPanel boardZone = new JPanel();
		boardZone.setLayout(new GridBagLayout());
		GridBagConstraints boardZoneC = new GridBagConstraints();

		boardZoneC.fill = GridBagConstraints.BOTH;
		boardZoneC.insets = new Insets(5, 5, 5, 5);
		boardZoneC.ipady = boardZoneC.anchor = GridBagConstraints.CENTER;
		boardZoneC.weightx = board.getHeight();
		boardZoneC.weighty = board.getWidth();
		boardZoneC.gridx = 0;
		boardZoneC.gridy = 0;

		for (int i = 0; i <= boardZoneC.weightx; i++) {
			boardZone.add(new CaseRobot(board.getCell(new Coordinates(i, 0))));
			boardZoneC.gridx = boardZoneC.gridx + 1;
		}
		for (int i = 0; i <= boardZoneC.weighty; i++) {
			boardZone.add(new CaseRobot(board.getCell(new Coordinates(0, i))));
			boardZoneC.gridy = boardZoneC.gridy + 1;
		}

		return boardZone;
	}
}
