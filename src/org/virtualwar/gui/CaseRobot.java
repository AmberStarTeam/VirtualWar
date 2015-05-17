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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

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
 * The class caseRobot. Create a JFrame with a picture of the type of a cell.
 * 
 * @author amberstar
 *
 */

@SuppressWarnings("serial")
public class CaseRobot extends JFrame {

	/**
	 * The main function
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		// Test des differentes cases
		// Génération des robots et des cases
		Tank tank = new Tank(1, new Coordinates(1, 1), Board.newBoard(10, 10));
		Shooter shooter = new Shooter(1, new Coordinates(1, 1), Board.newBoard(
				10, 10));
		Scavenger scavenger = new Scavenger(1, new Coordinates(1, 1),
				Board.newBoard(10, 10));
		Cell test = new Case(new Coordinates(1, 1));

		System.out
				.println("Un robot scavenger avec énergie maximum et mines maximum");
		test.setRobotIn(scavenger);
		CaseRobot caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Un robot scavenger ayant perdu 20 énergie");
		scavenger.removeEnergy(20);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Un robot scavenger ayant déposé une mine");
		scavenger.dropMine();
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Un tank avec énergie maximum");
		test.removeRobotIn(scavenger);
		test.setRobotIn(tank);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Un tank ayant perdu la moitié de son énergie");
		tank.removeEnergy(30);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Un shooter avec énergie maximum");
		test.removeRobotIn(tank);
		test.setRobotIn(shooter);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out
				.println("Quand un robot est mort il laisse place à une case vide");
		test.removeRobotIn(shooter);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Case occupée par un obstacle");
		test.setObstacle();
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Case occupée par une mine");
		test.removeObstacle();
		test.addMine(1);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();

		System.out.println("Case étant une base");
		test = new Base(new Coordinates(1, 1), 1);
		caseTest = new CaseRobot(test);

		Thread.sleep(4000);
		caseTest.dispose();
	}

	/**
	 * Instantiates a new caseRobot.
	 * 
	 * @param cell
	 *            the cell
	 */
	public CaseRobot(Cell cell) {
		this.setSize(10, 10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.add(initCase(cell));
		pack();
		setVisible(true);
	}

	/**
	 * Create the minebar of a scavenger
	 * 
	 * @param robot
	 *            the robot
	 * @return JProgressBar
	 */
	public JProgressBar etatMine(Robot robot) {
		JProgressBar jMine = new JProgressBar(JProgressBar.VERTICAL, 0, 10);
		jMine.setForeground(Color.yellow);
		jMine.setValue(((Scavenger) robot).getStock());
		Dimension size = jMine.getPreferredSize();
		size.width = 10;
		size.height = 60;
		jMine.setPreferredSize(size);
		return jMine;
	}

	/**
	 * Create the lifebar of a robot
	 * 
	 * @param robot
	 *            the robot
	 * @return JProgressBar
	 */
	public JProgressBar etatVie(Robot robot) {
		JProgressBar jHP = new JProgressBar(JProgressBar.VERTICAL, 0,
				robot.getMaxEng());
		jHP.setForeground(Color.red);
		jHP.setValue(robot.getEnergy());
		Dimension size = jHP.getPreferredSize();
		size.width = 10;
		size.height = 60;
		jHP.setPreferredSize(size);
		return jHP;
	}

	/**
	 * Create a JPanel with a picture of the type of cell.
	 * 
	 * @param cell
	 *            the cell
	 * @return JPanel
	 */
	public JPanel initCase(Cell cell) {
		if (cell.isBase() > 0) {
			JPanel base = new JPanel();
			JLabel iBase = new JLabel(new ImageIcon(getClass().getResource(
					"/org/virtualwar/res/Base-icon.png")));
			base.add(iBase, BorderLayout.CENTER);
			return base;
		} else if (cell.isObstacle()) {
			JPanel obstacle = new JPanel();
			JLabel iObstacle = new JLabel(new ImageIcon(getClass().getResource(
					"/org/virtualwar/res/Obstacle-icon.png")));
			obstacle.add(iObstacle, BorderLayout.CENTER);
			return obstacle;
		} else if (cell.mineContains() > 0) {
			JPanel mine = new JPanel();
			JLabel iMine = new JLabel(new ImageIcon(getClass().getResource(
					"/org/virtualwar/res/Mine-icon.png")));
			mine.add(iMine, BorderLayout.CENTER);
			return mine;
		} else if (cell.getRobotIn() != null) {
			if (cell.getRobotIn() instanceof Tank) {
				JPanel tank = new JPanel();
				JLabel iTank = new JLabel(new ImageIcon(getClass().getResource(
						"/org/virtualwar/res/Tank-icon.png")));
				tank.add(iTank, BorderLayout.WEST);
				tank.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				return tank;
			} else if (cell.getRobotIn() instanceof Shooter) {
				JPanel shooter = new JPanel();
				JLabel iShooter = new JLabel(new ImageIcon(getClass().getResource(
						"/org/virtualwar/res/Shooter-icon.png")));
				shooter.add(iShooter, BorderLayout.WEST);
				shooter.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				return shooter;
			} else if (cell.getRobotIn() instanceof Scavenger) {
				JPanel scavenger = new JPanel();
				JLabel iScavenger = new JLabel(new ImageIcon(getClass().getResource(
						"/org/virtualwar/res/Scavenger-icon.png")));
				scavenger.add(iScavenger, BorderLayout.WEST);
				scavenger.add(etatVie(cell.getRobotIn()), BorderLayout.EAST);
				scavenger.add(etatMine(cell.getRobotIn()), BorderLayout.EAST);
				return scavenger;
			}
		} else {
			JPanel vide = new JPanel();
			JLabel iVide = new JLabel(new ImageIcon(getClass().getResource(
					"/org/virtualwar/res/Vide-icon.png")));
			vide.add(iVide, BorderLayout.CENTER);
			return vide;
		}
		return null;
	}
}
