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

/**
 * The AdvancedIntelligence Class.
 *
 * @author Nicolas Beaussart
 */
public class AdvancedIntelligence extends Inteligence {

	/** The ran. */
	private Random ran = new Random();

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 */
	public AdvancedIntelligence() {
		super();
	}

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public AdvancedIntelligence(int team, Board board) {
		super(team, board);
	}

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 * @param robots
	 *            the robots of the team
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public AdvancedIntelligence(List<Robot> robots, int team, Board board) {
		super(robots, team, board);
	}

	/**
	 * Detect robot height.
	 *
	 * @param r
	 *            the r
	 * @return true, if successful
	 */
	public boolean detectRobotHeight(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		Coordinates detectShooter = new Coordinates(c.getWidth(),
				c.getHeight() + 1);
		if (getBoard().getRobot(detectShooter) instanceof Shooter
				&& getBoard().getRobot(detectShooter).getTeam() != t) {
			return true;
		}

		detectShooter = new Coordinates(c.getWidth(), c.getHeight() - 1);
		if (getBoard().getRobot(detectShooter) instanceof Shooter
				&& getBoard().getRobot(detectShooter).getTeam() != t) {
			return true;
		}

		for (int i = c.getHeight(); i < getBoard().getHeight(); i++) {
			Coordinates detectTank = new Coordinates(c.getWidth(), i);
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectShooter).getTeam() != t) {
				return true;
			}
		}

		for (int i = c.getHeight(); i > -1; i--) {
			Coordinates detectTank = new Coordinates(c.getWidth(), i);
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectShooter).getTeam() != t) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Detect robot width.
	 *
	 * @param r
	 *            the r
	 * @return true, if successful
	 */
	public boolean detectRobotWidth(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		Coordinates detectShooter = new Coordinates(c.getWidth() + 1,
				c.getHeight());
		if (getBoard().getRobot(detectShooter) instanceof Shooter
				&& getBoard().getRobot(detectShooter).getTeam() != t) {
			return true;
		}

		detectShooter = new Coordinates(c.getWidth() - 1, c.getHeight());
		if (getBoard().getRobot(detectShooter) instanceof Shooter
				&& getBoard().getRobot(detectShooter).getTeam() != t) {
			return true;
		}

		for (int i = c.getWidth(); i < getBoard().getWidth(); i++) {
			Coordinates detectTank = new Coordinates(i, c.getHeight());
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectShooter).getTeam() != t) {
				return true;
			}
		}

		for (int i = c.getWidth(); i > -1; i--) {
			Coordinates detectTank = new Coordinates(i, c.getHeight());
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectShooter).getTeam() != t) {
				return true;
			}
		}

		return false;
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
			switch (i) {
			case 0:
				retVal.add(new Tank(getTeam(), cords, getBoard()));
				break;
			case 1:
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
				break;
			case 2:
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
				break;
			case 3:
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

				// Priorit� aux attaques de tank
				for (Action att : attacks) {
					if (att.getRobotSource() instanceof Tank) {
						return att;
					}
				}

				// Ensuite les shooter /!\ pas fini /!\ si les deux shooter
				// peuvent effectuer l'action, ce sera le premier shooter qui
				// pourra effectuer l'action
				for (Action att : attacks) {
					if (att.getRobotSource() instanceof Shooter) {
						return att;
					}
				}
			}
		}

		Robot tmp = lsRobot.get(ran.nextInt(lsRobot.size()));
		if (tmp instanceof Scavenger) {

		}

		if (tmp instanceof Shooter) {
			List<Action> lsAct = tmp.getAvailableMove();
			return lsAct.get(ran.nextInt(lsAct.size()));
		} else if (tmp.canMove()) {
			List<Action> lsAct = tmp.getAvailableMove();
			return lsAct.get(ran.nextInt(lsAct.size()));
		}
		return null;

	}
}
