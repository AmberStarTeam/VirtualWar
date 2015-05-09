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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * The RandomInteligence Class.
 */
public class RandomInteligence extends Inteligence {

	/** The random generator. */
	private Random ran = new Random();

	/**
	 * Instantiates a new random inteligence.
	 *
	 */
	public RandomInteligence() {
		super();
	}

	/**
	 * Instantiates a new random inteligence.
	 *
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public RandomInteligence(int team, Board board) {
		super(team, board);
	}

	/**
	 * Instantiates a new random inteligence.
	 *
	 * @param robots
	 *            the robots of the team
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public RandomInteligence(List<Robot> robots, int team, Board board) {
		super(robots, team, board);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.ia.Inteligence#getInitialRobots(int)
	 */
	@Override
	public List<Robot> getInitialRobots(int numberOfBots) {
		List<Robot> retVal = new ArrayList<Robot>();
		Coordinates cords = getBoard().getCoordsBase(getTeam());
		for (int i = 0; i < numberOfBots; i++) {
			switch (ran.nextInt(3)) {
			case 0:
				retVal.add(new Tank(getTeam(), cords, getBoard()));
				break;
			case 1:
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
				break;
			case 2:
				retVal.add(new Scavenger(getTeam(), cords, getBoard()));
				break;

			default:
				break;
			}
		}
		setLsRobot(retVal);
		return retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.ia.Inteligence#makeTurn()
	 */
	@Override
	public Action makeTurn() {
		List<Robot> lsRobot = super.getLsRobot();
		if (lsRobot == null) {
			return null;
		}
		List<Action> attacks = new ArrayList<Action>();
		for (Robot rob : lsRobot) {
			if (!(rob instanceof Scavenger) && rob.canAttack()) {
				attacks.addAll(rob.getAvailableAtacks());
			}
		}
		if (!attacks.isEmpty()) {
			return attacks.get(ran.nextInt(attacks.size()));
		}
		while (true) {
			Robot tmp = lsRobot.get(ran.nextInt(lsRobot.size()));
			Boolean state = true;
			if (tmp instanceof Scavenger) {
				state = ran.nextBoolean();
			}

			if (state && tmp.canAttack()) {
				List<Action> lsAct = tmp.getAvailableMove();
				return lsAct.get(ran.nextInt(lsAct.size()));
			} else if (tmp.canMove()) {
				List<Action> lsAct = tmp.getAvailableMove();
				return lsAct.get(ran.nextInt(lsAct.size()));
			}
		}

	}

}
