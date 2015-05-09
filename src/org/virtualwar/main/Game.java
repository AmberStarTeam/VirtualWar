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
import org.virtualwar.config.Config;
import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;
import org.virtualwar.ia.Inteligence;
import org.virtualwar.ia.RandomInteligence;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

/**
 * The Class Game where the magic hapends !
 *
 * @author amberstar
 */
public class Game {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new Game().run();
	}

	/** The sc. */
	private Scanner sc;

	/** If contains tank. */
	private boolean containsTank = false;

	/** The pourcent of obstacle. */
	private int pourcent;

	/** The robots of team 1. */
	private List<Robot> t1;

	/** The The robots of team 2. */
	private List<Robot> t2;

	/** The name of team 1. */
	private String nameTeam1;

	/** The name of team 2. */
	private String nameTeam2;

	/** The width of the board. */
	private int width;

	/** The height of the board. */
	private int height;

	/** The board. */
	private Board board;

	/** If it is the end. */
	private boolean end = false;

	/** The nmb robots. */
	private int nmbRobots;

	/** The coordinate of the base 1. */
	private Coordinates b1 = new Coordinates(0, 0);

	/** The The coordinate of the base 2. */
	private Coordinates b2;

	/** The is player one human. */
	private boolean isPlayerOneHuman = true;

	/** The is player two human. */
	private boolean isPlayerTwoHuman = true;

	private Inteligence playerOne;
	private Inteligence playerTwo;

	/**
	 * Instantiates a new game.
	 */
	public Game() {
		sc = new Scanner(System.in);
	}

	/**
	 * Check if any robots can do somthing
	 *
	 * @param lsRobot
	 *            the list robot
	 * @return true, if anything to do
	 */
	public boolean anyCanDo(List<Robot> lsRobot) {
		for (Robot rob : lsRobot) {
			if (rob.getAvailableAtacks() != null
					|| rob.getAvailableMove() != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check every robot if it should die, then if it does, make the sound and
	 * remove it.
	 *
	 * @param lsRobot
	 *            the ls robot
	 */
	public void checkAlive(List<Robot> lsRobot) {
		List<Robot> robToRemove = new ArrayList<Robot>();
		for (Robot rob : lsRobot) {
			if (rob.getEnergy() <= 0) {
				rob.getBoard().getCell(rob.getCoordinates()).removeRobotIn(rob);
				new ThreadSoundRun(Constant.ROBOT_DEATH_SOUND, 1000).start();
				robToRemove.add(rob);
			}
		}
		for (Robot rob : robToRemove) {
			lsRobot.remove(rob);
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
	public List<Robot> choiceOfRobots(int nb, int team, Board board) {
		List<Robot> l = new ArrayList<Robot>();
		Robot robot;

		for (int i = 0; i < nb; i++) {
			System.out.println("1-" + TextData.SHOOTER_NAME + "\n2-"
					+ TextData.SCAVENGER_NAME + "\n3-" + TextData.TANK_NAME);
			int r = readSafeInt();
			while (0 > r || r > 3) {
				System.out.println(TextData.GAME_INVALID_INPUT);
				r = readSafeInt();
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
	 * If any robot are in base, make them do the base action.
	 *
	 * @param lsRobot
	 *            the ls robot
	 */
	public void doBaseCheck(List<Robot> lsRobot) {
		for (Robot rob : lsRobot) {
			if (rob.isInBase()) {
				rob.runBaseAction();
			}
		}
	}

	/**
	 * Gets the action for the team.
	 *
	 * @param lsRobot
	 *            the ls robot
	 * @return the action
	 */
	public Action getAction(List<Robot> lsRobot) {
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
				String inData = "";
				String outData = "";
				outData += "(0) " + TextData.GAME_CANCEL + ", ";
				inData += "0\n";
				if (robTmp.canMove()) {
					inData += "1\n";
					outData += "(1) Déplacer";
				}
				if (robTmp.canAttack()) {
					inData += "2\n";
					if (!outData.equals("(0) " + TextData.GAME_CANCEL + ", ")) {
						outData += " " + TextData.GAME_OR + " ";
					}
					outData += ((robTmp instanceof Scavenger) ? "(2) "
							+ TextData.GAME_MINE : "(2) "
							+ TextData.GAME_ATTACK);

				}

				String type = getInputValue(inData, outData);
				if (type.equals("0")) {
					return getAction(lsRobot);
				}
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
	 * Gets the index value of the action.
	 *
	 * @param ls
	 *            the ls
	 * @return the index val act
	 */
	public int getIndexValAct(List<Action> ls) {
		String txtOut = "";
		String strChoix = "";
		for (int i = 0; i < ls.size(); i++) {
			txtOut += (i + 1) + " : ";
			Action tmp = ls.get(i);
			if (tmp instanceof Attack) {
				if (tmp.getRobotSource() instanceof Scavenger) {
					txtOut += TextData.GAME_MINE + " : ";
				} else {
					txtOut += TextData.GAME_ATTACK + " : ";
				}
			} else if (tmp instanceof Move) {
				txtOut += TextData.GAME_MOVE + " : ";
			}
			txtOut += tmp.getDirection().toStr() + "\n";
			strChoix += (i + 1) + "\n";
		}

		return Integer.parseInt(getInputValue(strChoix + "0\n", txtOut + "0 "
				+ TextData.GAME_CANCEL + "\n")) - 1;
	}

	/**
	 * Gets the index value of the robot.
	 *
	 * @param ls
	 *            the ls
	 * @return the index val rob
	 */
	public int getIndexValRob(List<Robot> ls) {
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
	 * get the input from the list.
	 *
	 * @param lsChoix
	 *            the inputs splited with \n
	 * @param txtToOut
	 *            the text to print
	 * @return the value of input
	 */
	public String getInputValue(String lsChoix, String txtToOut) {
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
			} else if (input.equals("q")) {
				System.exit(0);
			} else if (!inputGood) {
				System.out.println(TextData.GAME_INVALID_INPUT);
			}

		} while (!inputGood);

		return input;
	}

	/**
	 * Init the board.
	 */
	public void initBoard() {
		System.out.println(TextData.GAME_BOARD_SIZE);
		System.out.println(TextData.GAME_HEIGHT + " :");
		height = readSafeInt();
		System.out.println(TextData.GAME_WIDTH + " :");
		width = readSafeInt();

		while (width < 3 || height < 3) { // A changer avec : factory
			System.out.println(TextData.GAME_INVALID_INPUT);
			height = readSafeInt();
			width = readSafeInt();
		}
		System.out.println(TextData.GAME_OBSTACLE);
		pourcent = readSafeInt();
		board = Board.newBoard(height, width);

		b2 = board.getCoordsBase(Constant.ID_TEAM_B);
	}

	/**
	 * Init the config.
	 */
	public void initConfig() {
		String left = TextData.GAME_LANG_INPUT.toString().split("" + '\u9999')[0];
		String right = TextData.GAME_LANG_INPUT.toString().split("" + '\u9999')[1];
		Config.language = getInputValue(left, right);

		left = TextData.GAME_CHOSE_SOUND_INPUT.toString().split("" + '\u9999')[0];
		right = TextData.GAME_CHOSE_SOUND_INPUT.toString().split("" + '\u9999')[1];
		System.out.println(TextData.GAME_CHOSE_SOUND);
		Config.soundOn = getInputValue(left, right).equals(""+left.charAt(0));

	}

	/**
	 * Init the ia.
	 */
	public void initIa() {
		String left = TextData.GAME_CHOSE_IA_INPUT.toString().split(
				"" + '\u9999')[0];
		String right = TextData.GAME_CHOSE_IA_INPUT.toString().split(
				"" + '\u9999')[1];

		System.out.println(TextData.GAME_IS_PLAYER_X_IA.toString().replaceAll(
				"X", "1"));
		String in = getInputValue(left, right);
		if (in.equals(left.charAt(0))) {
			isPlayerOneHuman = false;
			playerOne = new RandomInteligence(Constant.ID_TEAM_A, null);
		}
		System.out.println(TextData.GAME_IS_PLAYER_X_IA.toString().replaceAll(
				"X", "2"));
		in = getInputValue(left, right);
		if (in.equals(left.charAt(0))) {
			isPlayerTwoHuman = false;
			playerTwo = new RandomInteligence(Constant.ID_TEAM_B, null);
		}
	}

	/**
	 * Inits the number of robot.
	 */
	public void initNmbRobot() {
		System.out.println(TextData.GAME_CHOSE_NMB_BOTS);
		nmbRobots = readSafeInt();
		while (nmbRobots >= 6 || nmbRobots <= 0) {
			System.out.println(TextData.GAME_INVALID_INPUT);
			nmbRobots = readSafeInt();
		}
	}

	/**
	 * Inits the robots.
	 */
	public void initRobots() {
		if (isPlayerOneHuman) {
			System.out.println(TextData.GAME_CHOSE_ROBOTS_TYPE.toString()
					.replaceAll("X", nameTeam1));
			t1 = choiceOfRobots(nmbRobots, Constant.ID_TEAM_A, board);
		} else {
			playerOne.setBoard(board);
			t1 = playerOne.getInitialRobots(nmbRobots);
			for (Robot robot : t1) {
				if (robot instanceof Tank) {
					containsTank = true;
				}
			}
		}

		if (isPlayerTwoHuman) {
			System.out.println(TextData.GAME_CHOSE_ROBOTS_TYPE.toString()
					.replaceAll("X", nameTeam2));
			t2 = choiceOfRobots(nmbRobots, Constant.ID_TEAM_B, board);
		} else {
			playerTwo.setBoard(board);
			t2 = playerTwo.getInitialRobots(nmbRobots);
			for (Robot robot : t2) {
				if (robot instanceof Tank) {
					containsTank = true;
				}
			}
		}

		board.generate(pourcent, containsTank);

	}

	/**
	 * Inits the team.
	 */
	public void initTeam() {
		System.out.println(TextData.GAME_NAME_TEAM_X.toString().replaceAll("X",
				"1"));
		nameTeam1 = sc.nextLine();
		System.out.println(TextData.GAME_NAME_TEAM_X.toString().replaceAll("X",
				"1"));
		do {
			nameTeam2 = sc.nextLine();
			if (nameTeam1.equals(nameTeam2)) {
				System.out.println(TextData.ERROR_TEAM_SAME_NAME);
			}
		} while (nameTeam1.equals(nameTeam2));
	}

	/**
	 * Safely read a int.
	 *
	 * @return the value
	 */
	public int readSafeInt() {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(TextData.ERROR_NOT_NUMBER);
				continue;
			}
		}
	}

	/**
	 * Run the game.
	 */
	public void run() {
		initConfig();
		System.out.println("(ia) / (debug) / (normal) ?  :");
		String inp = sc.nextLine().toLowerCase();
		if (inp.equals("true")) {
			startDebug();
		} else if (inp.equals("ia")) {
			startDebugIa();
		} else {
			startNormal();
		}

		Action actT1 = null;
		Action actT2 = null;

		while (!end) {
			System.out.println(board.outGrindPlusLegend(Constant.ID_TEAM_A));
			if (anyCanDo(t1)) {
				if (isPlayerOneHuman) {
					System.out.println(TextData.GAME_TURN_PLAYER + nameTeam1);
					actT1 = getAction(t1);
				} else {
					actT1 = playerOne.makeTurn();
					try {
						Thread.sleep(Config.sleep_time_ia);
					} catch (InterruptedException e) {
					}
				}
			}

			if (actT1 != null) {
				actT1.act();
				actT1 = null;
			}

			checkAlive(t1);
			checkAlive(t2);

			System.out.println(board.outGrindPlusLegend(Constant.ID_TEAM_B));
			if (anyCanDo(t2)) {
				if (isPlayerTwoHuman) {
					System.out.println(TextData.GAME_TURN_PLAYER + nameTeam2);
					actT2 = getAction(t2);
				} else {
					actT2 = playerTwo.makeTurn();
					try {
						Thread.sleep(Config.sleep_time_ia);
					} catch (InterruptedException e) {
					}
				}
			}
			if (actT2 != null) {
				actT2.act();
				actT2 = null;
			}

			doBaseCheck(t1);
			doBaseCheck(t2);

			checkAlive(t1);
			checkAlive(t2);

			if (t1.isEmpty() || t2.isEmpty() || !(anyCanDo(t1) && anyCanDo(t2))) {
				end = true;
			}
		}
		System.out.println(nameTeam1);
		for (Robot robot : t1) {
			System.out.println(robot);
		}
		System.out.println();
		System.out.println(nameTeam2);
		for (Robot robot : t2) {
			System.out.println(robot);
		}

		if (t1.isEmpty() || !anyCanDo(t1)) {
			System.out.println(TextData.GAME_WIN_TEAM.toString().replaceAll(
					"XXX", nameTeam2));
		} else {
			System.out.println(TextData.GAME_WIN_TEAM.toString().replaceAll(
					"XXX", nameTeam1));
		}
		System.out.println(TextData.GAME_END);
		sc.nextLine();
		sc.close();
	}

	/**
	 * Start the debug mode.
	 */
	public void startDebug() {
		nameTeam1 = "team 1";
		nameTeam2 = "team 2";
		nmbRobots = 2;
		board = Board.newBoard(4, 6);

		b2 = new Coordinates(board.getWidth() - 1, board.getHeight() - 1);

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
	 * Start the ia debug mode.
	 */
	public void startDebugIa() {
		nameTeam1 = "team 1";
		nameTeam2 = "team 2";
		nmbRobots = 4;
		board = Board.newBoard(4, 6);

		b2 = new Coordinates(board.getWidth() - 1, board.getHeight() - 1);

		isPlayerOneHuman = false;
		playerOne = new RandomInteligence(Constant.ID_TEAM_A, board);

		t1 = playerOne.getInitialRobots(nmbRobots);

		isPlayerTwoHuman = false;
		playerTwo = new RandomInteligence(Constant.ID_TEAM_B, board);

		t2 = playerTwo.getInitialRobots(nmbRobots);

		containsTank = true;

		board.generate(10, containsTank);
	}

	/**
	 * Start the normal game.
	 */
	public void startNormal() {
		initTeam();
		initNmbRobot();
		initIa();
		initBoard();
		initRobots();
	}

}
