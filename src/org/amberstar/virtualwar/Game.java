package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author amberStar
 *
 */
public class Game {
	/** */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 */
	public Game() {

	}

	/**
	 * 
	 */
	public void run() {
		game();
	}

	/**
	 * 
	 */
	public void game() {

		// System.out.println(Rules.rules());
		System.out.println("Nom de la team 1 :");
		String t1 = sc.nextLine().toLowerCase();
		System.out.println("Nom de la team 2 :");
		String t2 = sc.nextLine().toLowerCase();

		System.out.println("Choisissez le nombre de robot pour  les équipes :");
		int nbRobotTeam1 = Integer.parseInt(sc.nextLine().toLowerCase());
		while (nbRobotTeam1 >= 6) {
			System.out.println("Choisissez un nouveau nombre de robot");
			nbRobotTeam1 = Integer.parseInt(sc.nextLine().toLowerCase());
		}
		int nbRobotTeam2 = nbRobotTeam1;

		System.out
				.println("Choisissez la taille du plateau (elle ne doit pas être inférieur à 3)\n Hauteur :");
		int height = Integer.parseInt(sc.nextLine().toLowerCase());
		System.out.println("Largeur :");
		int width = Integer.parseInt(sc.nextLine().toLowerCase());

		System.out.println("Poucentage d'obstacle : ");
		int pourcent = Integer.parseInt(sc.nextLine().toLowerCase());
		Board board = Board.newBoard(height, width);

		Coordinates b1 = new Coordinates(0, 0);
		Coordinates b2 = new Coordinates(board.getHeight() - 1,
				board.getWidth() - 1);

		System.out.println("Choisissez le type de vos robots \nequipe 1 :");
		List<Robot> r1 = choiceOfRobots(nbRobotTeam1, 1, board, b1);
		System.out.println("equipe 2 :");
		List<Robot> r2 = choiceOfRobots(nbRobotTeam2, 2, board, b2);

		board.generate(pourcent, containsTank(r1, r2));

		boolean end = false;

		while (end == false) {
			System.out.println(board.outGrindPlusLegend(-1));

			System.out
					.println("Team 1 :\nChoix de l'action:\n1-Se déplacemer\n2-Attaquer");
			int choiceOfAction = Integer.parseInt(sc.nextLine().toLowerCase());

			if (choiceOfAction == 1) {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r1));

				moveGame(r1);
			} else {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r1));
				attackGame(r1);
			}

			System.out.println(board.outGrindPlusLegend(-1));

			System.out
					.println("Team 2 :\nChoix de l'action:\n1-Se déplacemer\n2-Attaquer");
			choiceOfAction = Integer.parseInt(sc.nextLine().toLowerCase());

			if (choiceOfAction == 1) {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r2));

				moveGame(r2);

			} else {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r2));
				moveGame(r2);
			}

			if (nbRobotTeam1 == 0 || nbRobotTeam2 == 0) {
				end = true;
			}
		}
	}

	/**
	 * 
	 * @param nb
	 * @param team
	 * @param board
	 * @param b
	 * @return
	 */
	public List<Robot> choiceOfRobots(int nb, int team, Board board,
			Coordinates b) {
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

			if (r == 1) {
				robot = new Shooter(team, b, board);
				l.add(robot);
			} else if (r == 2) {
				robot = new Scavenger(team, b, board);
				l.add(robot);
			} else if (r == 3) {
				robot = new Tank(team, b, board);
				l.add(robot);
			}

		}
		return l;
	}

	/**
	 * 
	 * @param r1
	 * @param r2
	 * @return
	 */
	public boolean containsTank(List<Robot> r1, List<Robot> r2) {
		for (int i = 0; i < r1.size(); i++) {
			Robot robot1 = r1.get(i);
			Robot robot2 = r2.get(i);
			if (robot1 instanceof Tank || robot2 instanceof Tank) {
				return true;
			}

		}

		return false;
	}

	/**
	 * 
	 * @param l
	 * @return
	 */
	public String displayOfRobotsByTeam(List<Robot> l) {
		String result = "";
		for (int i = 0; i < l.size(); i++) {
			result = result + (i + 1) + "-" + l.get(i) + "\n";
		}
		return result;
	}

	/**
	 * 
	 * @param robot
	 * @return
	 */
	public String displayOfDirectionMove(Robot robot) {
		String result = "";
		if (robot instanceof Shooter || robot instanceof Scavenger) {
			result = TextData.UP + "\n" + TextData.DOWN + "\n" + TextData.RIGHT
					+ "\n" + TextData.LEFT + "\n" + TextData.DIAG_UP_RIGHT
					+ "\n" + TextData.DIAG_UP_LEFT + "\n"
					+ TextData.DIAG_DOWN_RIGHT + "\n" + TextData.DIAG_DOWN_LEFT
					+ "\n";
		} else {
			result = TextData.UP + "\n" + TextData.DOWN + "\n" + TextData.RIGHT
					+ "\n" + TextData.LEFT + "\n";
		}
		return result;
	}

	/**
	 * 
	 * @return 
	 */
	public String displayOfDirectionAttack() {
		String result = "";
		result = TextData.UP + "\n" + TextData.DOWN + "\n" + TextData.RIGHT
				+ "\n" + TextData.LEFT + "\n";
		return result;
	}

	/**
	 * 
	 * @param l
	 */
	public void moveGame(List<Robot> l) {
		Action action = null;
		String enter = "";
		String[] direction = null;
		int choiceR = Integer.parseInt(sc.nextLine().toLowerCase());
		Robot robot = l.get(choiceR - 1);

		if (robot instanceof Shooter || robot instanceof Scavenger) {
			if (robot.isInBase()) {
				robot.runBaseAction();
			}
			System.out.println("Choisissez une direction :");
			System.out.println(displayOfDirectionMove(robot));
			enter = sc.nextLine().toLowerCase();
			direction = enter.split(" ");
			switch (direction[0]) {
			case "haut":
				switch (direction[1]) {
				case "droite":
					action = new Move(robot, Constant.DIAG_UP_RIGHT);
					break;
				case "gauche":
					action = new Move(robot, Constant.DIAG_UP_LEFT);
					break;
				default:
					action = new Move(robot, Constant.UP);
					break;
				}
				break;

			case "bas":
				switch (direction[1]) {
				case "droite":
					action = new Move(robot, Constant.DIAG_DOWN_RIGHT);
					break;
				case "gauche":
					action = new Move(robot, Constant.DIAG_DOWN_LEFT);
					break;
				default:
					action = new Move(robot, Constant.DOWN);
					break;
				}
				break;

			case "droite":
				action = new Move(robot, Constant.RIGHT);
				break;

			case "gauche":
				action = new Move(robot, Constant.LEFT);
				break;
			default:
				break;
			}

		} else if (robot instanceof Tank) {
			if (robot.isInBase()) {
				robot.runBaseAction();
			}
			System.out.println("Choisissez une direction :");
			System.out.println(displayOfDirectionMove(robot));
			enter = sc.nextLine().toLowerCase();
			direction = enter.split(" ");

			switch (direction[0]) {
			case "haut":
				action = new Move(robot, Constant.MOVE_TANK.get(0));
				break;
			case "bas":
				action = new Move(robot, Constant.MOVE_TANK.get(1));
				break;
			case "droite":
				action = new Move(robot, Constant.MOVE_TANK.get(2));
				break;

			case "gauche":
				action = new Move(robot, Constant.MOVE_TANK.get(3));
				break;
			default:
				break;
			}
		}

		if (action != null) {
			action.act();
			action = null;
		}
	}

	/**
	 * 
	 * @param l
	 */
	public void attackGame(List<Robot> l) {
		Action action = null;
		String enter = "";
		String[] direction = null;
		int choiceR = Integer.parseInt(sc.nextLine().toLowerCase());
		Robot robot = l.get(choiceR - 1);

		if (robot.isInBase()) {
			robot.runBaseAction();
		}
		System.out.println("Choisissez une direction :");
		System.out.println(displayOfDirectionAttack());
		enter = sc.nextLine().toLowerCase();
		direction = enter.split(" ");

		switch (direction[1]) {
		case "haut":
			action = new Attack(robot, Constant.UP);
			break;

		case "bas":
			action = new Attack(robot, Constant.DOWN);
			break;

		case "droite":
			action = new Attack(robot, Constant.RIGHT);
			break;

		case "gauche":
			action = new Attack(robot, Constant.LEFT);
			break;
		default:
			break;
		}

		if (action != null) {
			action.act();
			action = null;
		}
	}
}
