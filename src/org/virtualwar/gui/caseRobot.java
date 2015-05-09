package org.virtualwar.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.virtualwar.board.Base;
import org.virtualwar.board.Board;
import org.virtualwar.board.Case;
import org.virtualwar.board.Cell;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

/**
* Classe qui créer un JPanel avec une image correspondant à un type de robot spécifié par le paramètre du constructeur ou un JPanel vide.
* A venir : Barre de vie.
* 
* @author OBLED Joël
**/


@SuppressWarnings("serial")
public class caseRobot extends JFrame {	
	
	//Constructeur utilisant un robot entré en paramètre
	public caseRobot(Robot robot) {
		this.setSize(10,10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(initRobot(robot));
		this.pack();
		this.setVisible(true);
	}
	
	public caseRobot(Cell cell) {
		this.setSize(10,10);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(initObstacle(cell));
		this.pack();
		this.setVisible(true);
	}
	
	//Création d'un JPanel contenant l'image correspondant au type de Robot en paramètre
	//ou, le cas échéant, création d'un JPanel vide.
	public JPanel initRobot(Robot type) {
		if(type.getType().equals("Tank")) {
			JPanel tank = new JPanel();
			JLabel iTank = new JLabel(new ImageIcon("pictures/Tank-icon.png"));
			tank.add(iTank, BorderLayout.CENTER);
			return tank;
		}else if(type.getType().equals("Shooter")) {
			JPanel shooter = new JPanel();
			JLabel iShooter = new JLabel(new ImageIcon("pictures/Shooter-icon.png"));
			shooter.add(iShooter, BorderLayout.CENTER);
			return shooter;
		}else if(type.getType().equals("Scavenger")) {
			JPanel scavenger = new JPanel();
			JLabel iScavenger = new JLabel(new ImageIcon("pictures/Scavenger-icon.png"));
			scavenger.add(iScavenger, BorderLayout.CENTER);
			return scavenger;
		}else{
			JPanel vide = new JPanel();
			return vide;
		}
	}
	
	//Création d'un JPanel contenant l'image correspondant au type de case en paramètre
	//ou, le cas échéant, création d'un JPanel vide.
	public JPanel initObstacle(Cell cell) {
		if(cell.isBase() > 0) {
			JPanel base = new JPanel();
			JLabel iBase = new JLabel(new ImageIcon("pictures/Base-icon.png"));
			base.add(iBase, BorderLayout.CENTER);
			return base;
		}else if(cell.isObstacle()) {
			JPanel obstacle = new JPanel();
			JLabel iObstacle = new JLabel(new ImageIcon("pictures/Obstacle-icon.png"));
			obstacle.add(iObstacle, BorderLayout.CENTER);
			return obstacle;
		}else if(cell.mineContains() > 0) {
			JPanel mine = new JPanel();
			JLabel iMine = new JLabel(new ImageIcon("pictures/Mine-icon.png"));
			mine.add(iMine, BorderLayout.CENTER);
			return mine;
		}else{
			JPanel vide = new JPanel();
			return vide;
		}
	}
	
	public static void main(String[] args) {
		
		//Test des différents JPanel robot
		Robot tank = new Tank(1, new Coordinates(1,1), Board.newBoard(10, 10));
		Robot shooter = new Shooter(1, new Coordinates(1,1), Board.newBoard(10,10));
		Robot scavenger = new Scavenger(1, new Coordinates(1,1), Board.newBoard(10,10));
		
		//A tester un par un
		//new caseRobot(tank);
		//new caseRobot(shooter);
		//new caseRobot(scavenger);
		
		//Test des différents JPanel case
		Cell mine = new Case(new Coordinates(1,1));
		mine.setMine(1);
		Cell obstacle = new Case(new Coordinates(1,1));
		obstacle.setObstacle();
		Cell base = new Base(new Coordinates(1,1),1);
		
		//A tester un par un
		//new caseRobot(mine);
		//new caseRobot(obstacle);
		//new caseRobot(base);
	}
}
