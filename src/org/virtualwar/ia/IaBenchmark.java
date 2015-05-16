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
package org.virtualwar.ia;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.robot.Robot;
import org.virtualwar.util.sound.ThreadSoundRun;

public class IaBenchmark {

	public static void main(String[] args) {
		Long timeBef = System.currentTimeMillis();
		int[] dat = new IaBenchmark(new RandomInteligence(),
				new AdvancedIntelligence(), 1_000).run();
		Long timeAft = System.currentTimeMillis();
		System.out.println(Arrays.toString(dat) + " , took "
				+ (timeAft - timeBef));
	}

	private Inteligence it1;
	private Inteligence it2;

	private int nmb;

	public IaBenchmark(Inteligence it1, Inteligence it2) {
		this.it1 = it1;
		this.it2 = it2;
		nmb = 1_000;
	}

	public IaBenchmark(Inteligence it1, Inteligence it2, int nmb) {
		this.it1 = it1;
		this.it2 = it2;
		this.nmb = nmb;
	}

	/**
	 * Check if any robots can do somthing
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

	public int[] run() {
		int[] res = new int[] { 0, 0 };
		PrintStream out = System.out;

		System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// DO NOTHING
			}
		}));
		for (int i = 0; i < nmb; i++) {
			int nmbRobots = 5;
			Board board = Board.newBoard(8, 8);
			board.generate(10, true);

			it1.setBoard(board);
			it1.setTeam(Constant.ID_TEAM_A);
			List<Robot> t1 = it1.getInitialRobots(nmbRobots);

			it2.setBoard(board);
			it2.setTeam(Constant.ID_TEAM_B);
			List<Robot> t2 = it2.getInitialRobots(nmbRobots);

			Action actT1 = null;
			Action actT2 = null;
			while (!(t1.isEmpty() || t2.isEmpty() || !(anyCanDo(t1) && anyCanDo(t2)))) {

				if (anyCanDo(t1)) {
					actT1 = it1.makeTurn();
					if (actT1 != null) {
						actT1.act();
						actT1 = null;
					} else {
						System.err.println("Error : diden't chose (ia 1)");
					}
				}

				checkAlive(t1);
				checkAlive(t2);

				if (anyCanDo(t2)) {
					actT2 = it2.makeTurn();
					if (actT2 != null) {
						actT2.act();
						actT2 = null;
					} else {
						System.err.println("Error : diden't chose (ia 2)");
					}
				}

				doBaseCheck(t1);
				doBaseCheck(t2);

				checkAlive(t1);
				checkAlive(t2);
			}
			if (t1.isEmpty() || !anyCanDo(t1)) {
				res[1]++;
			} else {
				res[0]++;
			}
			System.err.println("" + i);
		}
		System.setOut(out);
		return res;
	}

}
