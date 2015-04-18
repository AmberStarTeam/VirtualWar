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
package org.virtualwar.robot;

import java.util.ArrayList;
import java.util.List;

import org.virtualwar.action.Action;
import org.virtualwar.action.Attack;
import org.virtualwar.action.Move;
import org.virtualwar.board.Board;
import org.virtualwar.board.Cell;
import org.virtualwar.config.Constant;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

/**
 * The abstract class of robots.
 *
 * @author amberstar
 */
public abstract class Robot {

	/** the current energy of the robot. */
	private int energy;

	/** the current team of the robot. */
	private int team;

	/** the current coordinates of the robot. */
	private Coordinates coordinates;

	/** the number of energy regenerated from idle in base. */
	private final int regenBase;

	/** the maximum energy it can holds. */
	private final int maxEng;

	/** the current board the robot is in. */
	private Board board;

	/**
	 * Create a robot.
	 *
	 * @param team
	 *            team of robot
	 * @param coordinates
	 *            coordinate of robot in board
	 * @param board
	 *            the board to work with
	 * @param regenBase
	 *            the amount of energy given when in base
	 * @param maxEng
	 *            the maximal amount of energy the robot can have
	 */
	public Robot(int team, Coordinates coordinates, Board board, int regenBase,
			int maxEng) {
		this.team = team;
		this.coordinates = coordinates;
		this.regenBase = regenBase;
		this.maxEng = maxEng;
		energy = maxEng;
		this.board = board;
	}

	/**
	 * Can attack.
	 *
	 * @return if robot has a valid target and can attack it
	 */
	public boolean canAttack() {
		return getAvailableAtacks() != null;
	}

	/**
	 * If can move.
	 *
	 * @return if robot can move
	 */
	public boolean canMove() {
		return getAvailableMove() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Robot other = (Robot) obj;
		if (board == null) {
			if (other.board != null) {
				return false;
			}
		} else if (!board.equals(other.board)) {
			return false;
		}
		if (coordinates == null) {
			if (other.coordinates != null) {
				return false;
			}
		} else if (!coordinates.equals(other.coordinates)) {
			return false;
		}
		if (energy != other.energy) {
			return false;
		}
		if (maxEng != other.maxEng) {
			return false;
		}
		if (regenBase != other.regenBase) {
			return false;
		}
		if (team != other.team) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the attack sound.
	 *
	 * @return the sound of attack
	 */
	public abstract String getAttackSound();

	/**
	 * Gets the available attacks as a list.
	 *
	 * @return the available attacks
	 */
	public List<Action> getAvailableAtacks() {
		List<Action> retVal = new ArrayList<Action>();
		for (Coordinates cords : Constant.CARDINAL_DIRECTION) {
			Action tmp = new Attack(this, cords);
			if (tmp.canDoIt()) {
				retVal.add(tmp);
			}
		}
		return (retVal.size() == 0) ? null : retVal;
	}

	/**
	 * Gets the the available attacks as a list.
	 *
	 * @return the available attacks
	 */
	public List<Action> getAvailableMove() {
		List<Action> retVal = new ArrayList<Action>();
		for (Coordinates cords : getMoving()) {
			Action tmp = new Move(this, cords);
			if (tmp.canDoIt()) {
				retVal.add(tmp);
			}
		}
		return (retVal.size() == 0) ? null : retVal;
	}

	/**
	 * Gets the current board the robot is in.
	 *
	 * @return board where the robot is in
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Get the cell where the robot is in
	 *
	 * @return the cell the robot is in
	 */
	public Cell getCell() {
		return board.getCell(coordinates);
	}

	/**
	 * Gets the current coordinates of the robot.
	 *
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * Gets the cost action.
	 *
	 * @return the cost of a action
	 */
	public abstract int getCostAction();

	/**
	 * Gets the cost of moving.
	 *
	 * @return the coast of moving
	 */
	public abstract int getCostMoving();

	/**
	 * Gets the damage it make.
	 *
	 * @return the amount of damage it takes
	 */
	public abstract int getDamageTaken();

	/**
	 * Gets the current energy of the robot.
	 *
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * Gets the maximum energy it can holds.
	 *
	 * @return the maximal amount of energy the robot can have
	 */
	public int getMaxEng() {
		return maxEng;
	}

	/**
	 * Gets the move sound.
	 *
	 * @return the sound of moving
	 */
	public abstract String getMoveSound();

	/**
	 * Gets the moving in relative position.
	 *
	 * @return the list of Coordinates it can moves
	 */
	public abstract List<Coordinates> getMoving();

	/**
	 * Gets the range.
	 *
	 * @return the range of action it can perform
	 */
	public abstract int getRange();

	/**
	 * Gets the number of energy regenerated from idle in base.
	 *
	 * @return the amount of energy gain per round in base
	 */
	public int getRegenBase() {
		return regenBase;
	}

	/**
	 * Gets the current team of the robot.
	 *
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type of robot
	 */
	public abstract String getType();

	/**
	 * submit damage from a mine.
	 */

	public void hasBeenMined() {
		setEnergy(energy - Constant.SCAVENGER_DAMAGE_SUBMIT);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(700);
				} catch (InterruptedException e) {

				}
				new ThreadSoundRun("sounds/boom.wav", 800).start();
			}
		}).start();
	}

	/**
	 * submit damage from a shoot.
	 *
	 * @param rob
	 *            the robot who's attacking
	 */
	public void hasBeenShoot(Robot rob) {
		setEnergy(energy - rob.getDamageTaken());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
		result = prime * result
				+ ((coordinates == null) ? 0 : coordinates.hashCode());
		result = prime * result + energy;
		result = prime * result + maxEng;
		result = prime * result + regenBase;
		result = prime * result + team;
		return result;
	}

	/**
	 * Checks if is in base.
	 *
	 * @return if the robot is in a base
	 */
	public boolean isInBase() {
		return board.isBase(coordinates) == team;
	}

	/**
	 * Checks if the coordinate are valid.
	 *
	 * @param cords
	 *            the coordinates of the cell to test
	 * @return not board || obstacle || mine || not empty || other base|| null
	 */
	public String isValid(Coordinates cords) {
		if (!board.isValid(cords)) {
			return "not board";
		}
		Cell tmp = board.getCell(cords);
		if (tmp.isObstacle()) {
			return "obstacle";
		}
		if (tmp.mineContains() == team) {
			return "mine";
		}
		if (tmp.getRobotIn() != null) {
			return "not empty";
		}
		if (board.isBase(cords) != 0 && (board.isBase(cords) != team)) {
			return "other base";
		}
		return null;
	}

	/**
	 * Removes the energy.
	 *
	 * @param eng
	 *            the energy to remove
	 */
	public void removeEnergy(int eng) {
		setEnergy(energy - eng);
	}

	/**
	 * Run the action when the robot is in his base.
	 */
	public abstract void runBaseAction();

	/**
	 * Sets the current coordinates of the robot.
	 *
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Sets the current energy of the robot.
	 *
	 * @param energy
	 *            the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
		if (energy > maxEng) {
			this.energy = maxEng;
		}
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getType() + "[energy=" + energy + ", team=" + team
				+ ", coordinates=" + coordinates + ", regenBase=" + regenBase
				+ ", maxEng=" + maxEng + ", moves=" + getMoving() + "]";
	}

}
