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
package org.virtualwar.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.virtualwar.action.Action;
import org.virtualwar.action.Attack;
import org.virtualwar.action.Move;
import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 *
 * @author amberstar
 */
public class Game {

	/** The sc. */
	private Scanner sc;

	/** The contains tank. */
	private boolean containsTank = false;

	/** The pourcent. */
	private int pourcent;

	/** The t1. */
	private List<Robot> t1;

	/** The t2. */
	private List<Robot> t2;

	/** The name_team_1. */
	private String nameTeam1;

	/** The name_team_2. */
	private String nameTeam2;

	/** The width. */
	private int width;

	/** The height. */
	private int height;

	/** The board. */
	private Board board;

	/** The end. */
	private boolean end = false;

	/** The nmb robots. */
	private int nmbRobots;

	/** The b1. */
	private Coordinates b1 = new Coordinates(0, 0);

	/** The b2. */
	private Coordinates b2;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		sc = new Scanner(System.in);
	}

	/**
	 * Inits the team.
	 */
	private void initTeam() {
		System.out.println("Nom de la team 1 :");
		nameTeam1 = sc.nextLine();
		System.out.println("Nom de la team 2 :");
		nameTeam2 = sc.nextLine();
	}

	/**
	 * Inits the nmb robot.
	 */
	private void initNmbRobot() {
		// System.out.println(Rules.rules());
		System.out.println("Choisissez le nombre de robot pour les équipes :");
		nmbRobots = Integer.parseInt(sc.nextLine().toLowerCase());
		while (nmbRobots >= 6) {
			System.out.println("Choisissez un nouveau nombre de robot");
			nmbRobots = Integer.parseInt(sc.nextLine().toLowerCase());
		}
		// int nbRobotTeam2 = nbRobotTeam1;
	}

	/**
	 * Inits the board.
	 */
	private void initBoard() {
		System.out.println("Choisissez la taille du plateau"
				+ "(elle ne doit pas être inférieur à 3)\n Hauteur :");
		height = Integer.parseInt(sc.nextLine().toLowerCase());
		System.out.println("Largeur :");
		width = Integer.parseInt(sc.nextLine().toLowerCase());

		while (width < 3 || height < 3) { // A changer avec : factory
			System.out.println("Choisissez une nouvelle taille du plateau");
			height = Integer.parseInt(sc.nextLine().toLowerCase());
			width = Integer.parseInt(sc.nextLine().toLowerCase());
		}
		System.out.println("Poucentage d'obstacle : ");
		pourcent = Integer.parseInt(sc.nextLine().toLowerCase());
		board = Board.newBoard(height, width);
		b2 = new Coordinates(board.getHeight() - 1, board.getWidth() - 1);
	}

	/**
	 * Inits the robots.
	 */
	private void initRobots() {
		System.out.println("Choisissez le type de vos robots \nequipe 1 :");
		t1 = choiceOfRobots(nmbRobots, 1, board);

		System.out.println("equipe 2 :");
		t2 = choiceOfRobots(nmbRobots, 2, board);

		board.generate(pourcent, containsTank);

	}

	/**
	 * Start normal.
	 */
	private void startNormal() {
		initTeam();
		initNmbRobot();
		initBoard();
		initRobots();
	}

	/**
	 * Start debug.
	 */
	private void startDebug() {
		nameTeam1 = "team 1";
		nameTeam2 = "team 2";
		nmbRobots = 2;
		board = Board.newBoard(4, 4);
		b2 = new Coordinates(board.getHeight() - 1, board.getWidth() - 1);
		t1 = new ArrayList<Robot>();
		t1.add(new Tank(1, b1, board));
		t1.add(new Scavenger(1, b1, board));

		t2 = new ArrayList<Robot>();
		t2.add(new Tank(2, b2, board));
		t2.add(new Shooter(2, b2, board));
		containsTank = true;
		board.generate(10, containsTank);

	}

	/**
	 * Run.
	 */
	public void run() {
		System.out.println("Debug mode ?  :");
		String inp = sc.nextLine().toLowerCase();
		if (inp.equals("true")) {
			startDebug();
		} else {
			startNormal();
		}

		System.out.println(t1);
		System.out.println(t2);

		System.out.println(board.outGrindPlusLegend(1));
		// System.out.println(board.outGrindPlusLegend(2));

		Action actT1 = null;
		Action actT2 = null;
		while (!end) {
			System.out.println(board.outGrindPlusLegend(Constant.ID_TEAM_A));
			if (anyCanDo(t1)) {
				System.out.println("Au tour de " + nameTeam1);
				actT1 = getAction(t1);
				System.out.println("Asked !");
			}

			if (actT1 != null) {
				actT1.act();
				actT1 = null;
			}

			System.out.println(board.outGrindPlusLegend(Constant.ID_TEAM_B));
			if (anyCanDo(t2)) {
				System.out.println("Au tour de " + nameTeam2);
				actT2 = getAction(t2);
			}
			if (actT2 != null) {
				actT2.act();
				actT2 = null;
			}

			doBaseCheck(t1);
			doBaseCheck(t2);

			checkAlive(t1);
			checkAlive(t2);

			if (t1.isEmpty() || t2.isEmpty()
					|| (!anyCanDo(t1) || !anyCanDo(t2))) {
				end = true;
			}
		}
	}

	/**
	 * Do base check.
	 *
	 * @param lsRobot
	 *            the ls robot
	 */
	private void doBaseCheck(List<Robot> lsRobot) {
		for (Robot rob : lsRobot) {
			if (rob.isInBase()) {
				rob.runBaseAction();
			}
		}
	}

	/**
	 * Check alive.
	 *
	 * @param lsRobot
	 *            the ls robot
	 */
	private void checkAlive(List<Robot> lsRobot) {
		List<Robot> robToRemove = new ArrayList<Robot>();
		for (Robot rob : lsRobot) {
			if (rob.getEnergy() <= 0) {
				rob.getBoard().getCell(rob.getCoordinates()).removeRobotIn(rob);
				new ThreadSoundRun("sounds/deathOfRobots.wav", 1000).start();
				robToRemove.add(rob);
			}
		}
		for (Robot rob : robToRemove) {
			lsRobot.remove(rob);
		}
	}

	/**
	 * Any can do.
	 *
	 * @param lsRobot
	 *            the list robot
	 * @return true, if anything to do
	 */
	private boolean anyCanDo(List<Robot> lsRobot) {
		for (Robot rob : lsRobot) {
			if (rob.getAvailableAtacks() != null
					|| rob.getAvailableMove() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * get the input from the list.
	 *
	 * @param lsChoix
	 *            the inputs splited with \n
	 * @param txtToOut
	 *            the text to print
	 * @return the value of input
	 */
	private String getInputValue(String lsChoix, String txtToOut) {
		String[] lsChoixTab = lsChoix.split("\n");
		System.out.println(txtToOut);
		boolean inputGood = false;
		String input = "";
		do {
			input = sc.nextLine().toLowerCase();
			for (String string : lsChoixTab) {
				if (input.equals(string)) {
					inputGood = true;
				}
			}
			if (input.equals("h")) {
				System.out.println(TextData.LABEL_REGLE);
			}
			if (!inputGood) {
				System.out.println("Entrée non valide !");
			}
			if (input.equals("q")){
				System.exit(0);
			}

		} while (!inputGood);

		return input;
	}

	/**
	 * Gets the index val rob.
	 *
	 * @param ls
	 *            the ls
	 * @return the index val rob
	 */
	private int getIndexValRob(List<Robot> ls) {
		String strChoix = "";
		String txtToOut = "";
		Coordinates tmpCords = null;
		for (int i = 0; i < ls.size(); i++) {
			tmpCords = ls.get(i).getCoordinates();

			txtToOut += (i + 1) + " : " + ls.get(i).getType() + " en " + "(w"
					+ tmpCords.getWidth() + ": h" + tmpCords.getHeight() + ")"
					+ "\n";
			strChoix += (i + 1) + "\n";
		}

		return Integer.parseInt(getInputValue(strChoix, txtToOut)) - 1;
	}

	/**
	 * Gets the index val act.
	 *
	 * @param ls
	 *            the ls
	 * @return the index val act
	 */
	private int getIndexValAct(List<Action> ls) {
		String txtOut = "";
		String strChoix = "";
		for (int i = 0; i < ls.size(); i++) {
			txtOut += (i + 1) + " : ";
			Action tmp = ls.get(i);
			if (tmp instanceof Attack) {
				if (tmp.getRobotSource() instanceof Scavenger) {
					txtOut += "pieger : ";
				} else {
					txtOut += "attaquer : ";
				}
			} else if (tmp instanceof Move) {
				txtOut += "se déplacer : ";
			}
			txtOut += tmp.getDirection().toStr() + "\n";
			strChoix += (i + 1) + "\n";
		}

		return Integer.parseInt(getInputValue(strChoix + "0\n", txtOut
				+ "0 annuler\n")) - 1;
	}

	/**
	 * Gets the action.
	 *
	 * @param lsRobot
	 *            the ls robot
	 * @return the action
	 */
	private Action getAction(List<Robot> lsRobot) {
		while (true) {
			List<Robot> robotListFinal = new ArrayList<Robot>();
			for (Robot rob : lsRobot) {
				if (rob.getAvailableAtacks() != null
						|| rob.getAvailableMove() != null) {
					robotListFinal.add(rob);
				}
			}
			if (robotListFinal.isEmpty()) {
				// System.out.println("Get action null");
				return null;
			}
			// System.out.println("Asking robot");
			int idxRob = getIndexValRob(robotListFinal);
			if (idxRob == -1) {
				continue;
			}
			Robot robTmp = robotListFinal.get(idxRob);
			List<Action> lsAction = null;
			do {
				String type = getInputValue("1\n2", "(1)Déplacer ou (2)"
						+ ((robTmp instanceof Scavenger) ? "pieger"
								: "attaquer"));
				if (type.equals("1")) {
					lsAction = robTmp.getAvailableMove();
				} else {
					lsAction = robTmp.getAvailableAtacks();
				}
			} while (lsAction == null);
			int idxAct = getIndexValAct(lsAction);
			if (idxAct == -1) {
				continue;
			}
			return lsAction.get(idxAct);
		}

	}

	/**
	 * Choice of robots.
	 *
	 * @param nb
	 *            the nb
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 * @return the list
	 */
	private List<Robot> choiceOfRobots(int nb, int team, Board board) {
		List<Robot> l = new ArrayList<Robot>();
		Robot robot;

		for (int i = 0; i < nb; i++) {
			System.out.println("1-Tireur\n2-Piegeur\n3-Char");
			int r = Integer.parseInt(sc.nextLine().toLowerCase());
			while (0 > r || r > 3) {
				System.out
						.println("Tu as choisi un mauvais numéro ! Try again !");
				r = Integer.parseInt(sc.nextLine().toLowerCase());
			}
			if (team == 1) {
				if (r == 1) {
					robot = new Shooter(team, b1, board);
					l.add(robot);
				} else if (r == 2) {
					robot = new Scavenger(team, b1, board);
					l.add(robot);
				} else if (r == 3) {
					robot = new Tank(team, b1, board);
					containsTank = true;
					l.add(robot);
				}
			} else if (team == 2) {
				if (r == 1) {
					robot = new Shooter(team, b2, board);
					l.add(robot);
				} else if (r == 2) {
					robot = new Scavenger(team, b2, board);
					l.add(robot);
				} else if (r == 3) {
					robot = new Tank(team, b2, board);
					containsTank = true;
					l.add(robot);
				}
			}
		}
		return l;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new Game().run();
	}

}
