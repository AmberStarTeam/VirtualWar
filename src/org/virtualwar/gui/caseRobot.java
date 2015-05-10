package org.virtualwar.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.virtualwar.board.Board;
import org.virtualwar.board.Case;
import org.virtualwar.board.Cell;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

/**
 * Classe qui créer l'affichage d'une case selon son type (Base, obstacle, mine, robots).
 * 
 * @author Joël OBLED
 *
 */

@SuppressWarnings("serial")
public class caseRobot extends JFrame {

	private int hp;

	// Constructeur utilisant une case entree en parametre
	public caseRobot(Cell cell) {
		this.setSize(10, 10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(initCase(cell));
		this.pack();
		this.setVisible(true);
	}

	// Creation d'un JPanel contenant l'image correspondante au type de case en 
	// parametre : base, obstacle, mine ou le type de robot qu'elle contient avec sa barre de vie et/
	// ou mine.
	public JPanel initCase(Cell cell) {
		if (cell.isBase() > 0) {
			JPanel base = new JPanel();
			JLabel iBase = new JLabel(new ImageIcon("pictures/Base-icon.png"));
			base.add(iBase, BorderLayout.CENTER);
			return base;
		} else if (cell.isObstacle()) {
			JPanel obstacle = new JPanel();
			JLabel iObstacle = new JLabel(new ImageIcon(
					"pictures/Obstacle-icon.png"));
			obstacle.add(iObstacle, BorderLayout.CENTER);
			return obstacle;
		} else if (cell.mineContains() > 0) {
			JPanel mine = new JPanel();
			JLabel iMine = new JLabel(new ImageIcon("pictures/Mine-icon.png"));
			mine.add(iMine, BorderLayout.CENTER);
			return mine;
		} else if (cell.getRobotIn() != null) {
			if (cell.getRobotIn() instanceof Tank) {
				JPanel tank = new JPanel();
				JLabel iTank = new JLabel(new ImageIcon(
						"pictures/Tank-icon.png"));
				tank.add(iTank, BorderLayout.WEST);
				tank.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				return tank;
			} else if (cell.getRobotIn() instanceof Shooter) {
				JPanel shooter = new JPanel();
				JLabel iShooter = new JLabel(new ImageIcon(
						"pictures/Shooter-icon.png"));
				shooter.add(iShooter, BorderLayout.WEST);
				shooter.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				return shooter;
			} else if (cell.getRobotIn() instanceof Scavenger) {
				JPanel scavenger = new JPanel();
				JLabel iScavenger = new JLabel(new ImageIcon(
						"pictures/Scavenger-icon.png"));
				scavenger.add(iScavenger, BorderLayout.WEST);
				scavenger.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				scavenger.add(etatMine(cell.getRobotIn()), BorderLayout.EAST);
				return scavenger;
			}
		} else {
			JPanel vide = new JPanel();
			return vide;
		}
		return null;
	}

	// Creation de la barre de vie selon l'energie du type de Robot
	public JProgressBar etatVie(Robot robot) {
		JProgressBar jHP = new JProgressBar(JProgressBar.VERTICAL, 0, robot.getMaxEng());
		jHP.setForeground(Color.red);
		jHP.setValue(robot.getMaxEng());
		Dimension size = jHP.getPreferredSize();
		size.width = 10;
		size.height = 60;
		jHP.setPreferredSize(size);
		return jHP;
	}

	// Creation de la barre de mine selon les mines actuelles du robot.
	public JProgressBar etatMine(Robot robot) {
		JProgressBar jMine = new JProgressBar(JProgressBar.VERTICAL, 0,
				((Scavenger) robot).getStock());
		jMine.setForeground(Color.yellow);
		jMine.setValue(((Scavenger) robot).getStock());
		Dimension size = jMine.getPreferredSize();
		size.width = 10;
		size.height = 60;
		jMine.setPreferredSize(size);
		return jMine;
	}

	public static void main(String[] args) {

		// Test des differentes cases
		//Robots
		Robot tank = new Tank(1, new Coordinates(1, 1), Board.newBoard(10, 10));
		Robot shooter = new Shooter(1, new Coordinates(1, 1), Board.newBoard(
				10, 10));
		Robot scavenger = new Scavenger(1, new Coordinates(1, 1),
				Board.newBoard(10, 10));
		Cell test = new Case(new Coordinates(1,1));
		
		//Pour tester un robot
		test.setRobotIn(scavenger);
		
		//Pour tester le reste
		//test.setMine(1);
		//test.setObstacle();
		
		new caseRobot(test);
	}
}
