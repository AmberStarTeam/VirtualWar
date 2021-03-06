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

import org.virtualwar.board.Board;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * The TestingFormater Class.
 * @author amberstar
 */
public class TestingFormater {
	
	/**
	 * Instantiates a new testing formater.
	 */
	private TestingFormater() {
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String str = String.format("%.17s", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String str6 = String.format("%.17s", "ABCDEFG");
		System.out.printf("|   %-17s|", str);
		System.out.printf("|   %-17s|", str6);
		for (int i = 0; i < 7; i++) {
			Board tmp = Board.newBoard(i, i);
			System.out.println((tmp == null) ? "NULL " + i : tmp
					.outGrindPlusLegend(1));
		}
		Board b = Board.newBoard(10, 10);
		Tank tank = new Tank(1, new Coordinates(5, 9), b);
		System.out.println(tank.getMoving().contains(new Coordinates(-2, 0)));
	}
}
