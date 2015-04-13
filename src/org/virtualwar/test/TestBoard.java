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
package org.virtualwar.test;

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

import java.util.HashMap;
import java.util.Scanner;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * The TestBoard Class.
 *
 * @author Nicolas Beaussart
 */
public final class TestBoard {
	
	/** The scaner. */
	private static Scanner sc = new Scanner(System.in);

	/**
	 * Instantiates a new test board.
	 */
	private TestBoard() {
	}

	/**
	 * Test gen.
	 */
	public static void testGen() {
		String input = "";
		Long timeBef, timeAft;
		HashMap<Integer, Long> dataGen = new HashMap<Integer, Long>();

		System.out.println("Veuillez entrer la taille du plateau (h w)");
		Board b = Board.newBoard(sc.nextInt(), sc.nextInt());

		boolean tankIn = false;
		if (input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o")
				|| input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
			tankIn = true;
		}

		boolean showBoard = false;
		if (input.equalsIgnoreCase("oui") || input.equalsIgnoreCase("o")
				|| input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
			tankIn = true;
		}
		for (int i = 0; i < 100; i++) {
			timeBef = System.currentTimeMillis();
			b.generate(i, tankIn);
			timeAft = System.currentTimeMillis();
			dataGen.put(i, (timeAft - timeBef));
			if (showBoard) {
				System.out.println(b);
			}
			if (i % 5 == 0) {
				System.out.print(" .");
			}

		}
		System.out.println();
		for (int i : dataGen.keySet()) {
			System.out.println("For " + i + " % of obstacle it took "
					+ dataGen.get(i) + "ms to generate on a " + b.getHeight()
					+ "/" + b.getWidth() + " board ("
					+ (b.getHeight() * b.getWidth()) + " cells !!)");
		}
		sc.close();

	}

	/**
	 * debug command.
	 *
	 * @param args
	 *            input stream parameters
	 */
	public static void main(String[] args) {
		Board b = Board.newBoard(5, 8);
		String input = "";
		Robot top = null;
		Coordinates topCop = new Coordinates(0, 0);

		while (top == null) {
			System.out.println("Class to test : ");
			input = sc.nextLine().toLowerCase();
			switch (input) {
			case "tank":
				top = new Tank(1, topCop, b);
				break;
			case "piegeur":
			case "scavenger":
				top = new Scavenger(1, topCop, b);
				break;
			case "tireur":
			case "shooter":
				top = new Shooter(1, topCop, b);
				break;
			case "gen":
				testGen();
				System.exit(0);
			default:
				break;
			}
		}
		Coordinates tanCop = new Coordinates(2, 2);
		Coordinates otherCop = new Coordinates(7, 0);
		Robot tan = new Tank(1, tanCop, b);
		Robot other = new Shooter(2, otherCop, b);
		b.setRobot(tan, tanCop);
		b.setRobot(top, topCop);
		b.setRobot(other, otherCop);
		Coordinates min2 = new Coordinates(2, 1);
		b.setMine(min2, 2);
		b.setMine(new Coordinates(0, 6), 1);
		Action ac = null;
		while (!input.equals("stop null")) {
			if (top.isInBase()) {
				top.runBaseAction();
			}
			System.out.println(' ' + b.outGrindPlusLegend(-1).replaceAll("\n",
					"\n "));

			input = sc.nextLine() + " null";

			if (ac != null) {
				ac.act();
				ac = null;
			}
		}
		sc.close();

	}
}
