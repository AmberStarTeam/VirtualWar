package org.virtualwar.action;

import org.virtualwar.robot.Robot;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
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

// TODO: Auto-generated Javadoc
/**
 * The Action Class.
 * 
 * @author amberstar
 */
public abstract class Action {

	/** the robot source. */
	private Robot robotIn;

	/** the direction of action. */
	private Coordinates direction;

	/**
	 * Instantiates a new action.
	 *
	 * @param robot
	 *            robot from action
	 * @param dir
	 *            direction to do act
	 */
	public Action(Robot robot, Coordinates dir) {
		this.robotIn = robot;
		this.direction = dir;
	}

	/**
	 * Gets the robot source.
	 *
	 * @return the source of the action
	 */
	public Robot getRobotSource() {
		return robotIn;
	}

	/**
	 * Gets the direction of action.
	 *
	 * @return the direction of the action
	 */
	public Coordinates getDirection() {
		return direction;
	}

	/**
	 * run the action.
	 */
	public abstract void act();

	/**
	 * Can do it.
	 *
	 * @return if possible
	 */
	public abstract boolean canDoIt();

}
