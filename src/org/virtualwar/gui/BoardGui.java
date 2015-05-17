package org.virtualwar.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.virtualwar.board.Board;
import org.virtualwar.util.Coordinates;

public class BoardGui extends JFrame {
	
	public BoardGui(Board board) {
		this.setSize(400,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(initBoard(board));
		this.pack();
		this.setVisible(true);
	}
	
	//Creer un JPanel contenant un Gridbag de cases imagees pour representer le plateau de jeu selon
	//un plateau entre en paramètre.
	public JPanel initBoard(Board board) {
		JPanel boardZone = new JPanel();
		boardZone.setLayout(new GridBagLayout());
		GridBagConstraints boardZoneC = new GridBagConstraints();
		
		boardZoneC.fill = GridBagConstraints.BOTH;
		boardZoneC.insets = new Insets(5,5,5,5);
		boardZoneC.ipady = boardZoneC.anchor = GridBagConstraints.CENTER;
		boardZoneC.weightx = board.getHeight();
		boardZoneC.weighty = board.getWidth();
		boardZoneC.gridx = 0;
		boardZoneC.gridy = 0;
		
		for(int i = 0; i<=boardZoneC.weightx; i++) {
			boardZone.add(new CaseRobot(board.getCell(new Coordinates(i,0))));
			boardZoneC.gridx = boardZoneC.gridx + 1;
		}
		for(int i = 0; i<=boardZoneC.weighty; i++) {
			boardZone.add(new CaseRobot(board.getCell(new Coordinates(0,i))));
			boardZoneC.gridy = boardZoneC.gridy + 1;
		}
		
		return boardZone;
	}
	
	//A faire
	public static void main(String[] args) {
		
	}
}
