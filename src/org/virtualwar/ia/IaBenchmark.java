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
import org.virtualwar.config.Config;
import org.virtualwar.config.Constant;
import org.virtualwar.robot.Robot;
import org.virtualwar.util.sound.ThreadSoundRun;

// TODO: Auto-generated Javadoc
/**
 * The IaBenchmark Class.
 *
 * @author Nicolas Beaussart
 */
public class IaBenchmark {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Long timeBef = System.currentTimeMillis();
		int[] dat = new IaBenchmark(new RandomInteligence(),
				new IAThomas(), 100).run();
		Long timeAft = System.currentTimeMillis();
		System.out.println(Arrays.toString(dat) + " , took "
				+ (timeAft - timeBef));
	}

	/** The it1. */
	private Intelligence it1;

	/** The it2. */
	private Intelligence it2;

	/** The nmb. */
	private int nmb;

	/**
	 * Instantiates a new ia benchmark.
	 *
	 * @param it1
	 *            the it1
	 * @param it2
	 *            the it2
	 */
	public IaBenchmark(Intelligence it1, Intelligence it2) {
		this.it1 = it1;
		this.it2 = it2;
		nmb = 1_000;
	}

	/**
	 * Instantiates a new ia benchmark.
	 *
	 * @param it1
	 *            the it1
	 * @param it2
	 *            the it2
	 * @param nmb
	 *            the nmb
	 */
	public IaBenchmark(Intelligence it1, Intelligence it2, int nmb) {
		this.it1 = it1;
		this.it2 = it2;
		this.nmb = nmb;
	}

	/**
	 * Check if any robots can do something.
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

	/**
	 * Run.
	 *
	 * @return the int[] result
	 */
	public int[] run() {
		int[] res = new int[] { 0, 0 };
		PrintStream out = System.out;

		System.setOut(new PrintStream(new OutputStream() {
			@Override
			public void write(int b) {
				// so we don't have outputs to slow us down :)
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
					try {
						actT1 = it1.makeTurn();
					} catch (Exception e) {
						new ThreadSoundRun(getClass().getResource(
								"/org/virtualwar/res/deathOfRobots.wav").getPath(), true)
								.start();
						System.err.println("Error : ia 1 breaking the game...");
						System.err.println(e.toString());
						e.printStackTrace(System.err);
						break;
					}
					if (actT1 != null) {
						actT1.act();
						actT1 = null;
					} else {
						new ThreadSoundRun(getClass().getResource(
								"/org/virtualwar/res/deathOfRobots.wav").getPath(), true)
								.start();
						System.err.println("Error : diden't chose (ia 1)");
						break;
					}
				}

				checkAlive(t1);
				checkAlive(t2);

				if (anyCanDo(t2)) {
					try {
						actT2 = it2.makeTurn();
					} catch (Exception e) {
						Config.soundOn = true;
						new ThreadSoundRun(getClass().getResource(
								"/org/virtualwar/res/deathOfRobots.wav").getPath(), true)
								.start();
						Config.soundOn = false;
						System.err.println("Error : ia 2 breaking the game...");
						System.err.println(e.toString());
						e.printStackTrace(System.err);
						break;
					}
					if (actT2 != null) {
						actT2.act();
						actT2 = null;
					} else {
						Config.soundOn = true;
						new ThreadSoundRun(getClass().getResource(
								"/org/virtualwar/res/deathOfRobots.wav").getPath(), true)
								.start();
						Config.soundOn = false;
						System.err.println("Error : diden't chose (ia 2)");
						break;
					}
				}

				doBaseCheck(t1);
				doBaseCheck(t2);

				checkAlive(t1);
				checkAlive(t2);
			}
			if (t1.isEmpty() || !anyCanDo(t1)) {
				System.err.println("Team 2 won  " + it2.toString());
				res[1]++;
			} else if (t2.isEmpty() || !anyCanDo(t2)) {
				System.err.println("Team 1 won  " + it1.toString());
				res[0]++;
			}
			System.err.println("" + i);
		}
		System.setOut(out);
		return res;
	}

}
