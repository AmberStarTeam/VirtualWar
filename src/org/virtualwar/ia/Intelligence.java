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

import java.util.List;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;

// TODO: Auto-generated Javadoc
/**
 * The Class Intelligence.
 *
 * @author Nicolas Beaussart
 */
public abstract class Intelligence {

	/** The list of robot. */
	private List<Robot> lsRobot;

	/** The team. */
	private int team;

	/** The board. */
	private Board board;

	/**
	 * Instantiates a new intelligence.
	 *
	 */
	public Intelligence() {
	}

	/**
	 * Instantiates a new intelligence.
	 *
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public Intelligence(int team, Board board) {
		this.team = team;
		this.board = board;
	}

	/**
	 * Instantiates a new intelligence.
	 *
	 * @param robots
	 *            the robots
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public Intelligence(List<Robot> robots, int team, Board board) {
		lsRobot = robots;
		this.team = team;
		this.board = board;
	}

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Gets the initial robots.
	 *
	 * @param numberOfBots
	 *            the number of robots
	 * @return the initial robots
	 */
	public abstract List<Robot> getInitialRobots(int numberOfBots);

	/**
	 * Gets the list of robot.
	 *
	 * @return the list of robot
	 */
	public List<Robot> getLsRobot() {
		return lsRobot;
	}

	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Make turn.
	 *
	 * @return the action
	 */
	public abstract Action makeTurn();

	/**
	 * Sets the board.
	 *
	 * @param board
	 *            the new board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * Sets the list of robot.
	 *
	 * @param lsRobot
	 *            the new list of robot
	 */
	public void setLsRobot(List<Robot> lsRobot) {
		this.lsRobot = lsRobot;
	}

	/**
	 * Sets the team.
	 *
	 * @param team
	 *            the new team
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Intelligence ";
	}
}
