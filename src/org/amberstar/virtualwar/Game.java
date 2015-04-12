package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	private Scanner sc = new Scanner(System.in);

	public Game() {

	}

	public void run() {
		game();
	}

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
		System.out.println(board.outGrindPlusLegend(-1));

		boolean end = false;
		while (end == false) {
			Action act;
			Robot robot;
			System.out
					.println("Team 1 :\nChoix de l'action:\n1-Se déplacemer\n2-Attaquer");
			int choiceOfAction = Integer.parseInt(sc.nextLine().toLowerCase());
			if (choiceOfAction == 1) {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r1));
				int choiceR = Integer.parseInt(sc.nextLine().toLowerCase());

				System.out.println("Choisissez une direction :\n"
						+ r1.get(choiceR - 1).getAvailableMove());

			} else {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r1));

			}

			System.out
					.println("Team 2 :\nChoix de l'action:\n1-Se déplacemer\n2-Attaquer");
			choiceOfAction = Integer.parseInt(sc.nextLine().toLowerCase());
			if (choiceOfAction == 1) {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r2));

			} else {
				System.out.println("Choisissez un robot :\n"
						+ displayOfRobotsByTeam(r2));
			}

			if (nbRobotTeam1 == 0 || nbRobotTeam2 == 0) {
				end = true;
			}
		}
	}

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

	public String displayOfRobotsByTeam(List<Robot> l) {
		String result = "";
		for (int i = 0; i < l.size(); i++) {
			result = result + (i + 1) + "-" + l.get(i) + "\n";
		}
		return result;
	}

}
