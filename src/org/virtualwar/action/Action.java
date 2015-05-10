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
package org.virtualwar.action;

import org.virtualwar.robot.Robot;
import org.virtualwar.util.Coordinates;

/**
 * The abstract class for actions.
 *
 * @author amberstar
 */
public abstract class Action {

	/** the robot source. */
	private static Robot robotIn;

	/** the direction of the action in relative position. */
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
		robotIn = robot;
		direction = dir;
	}

	/**
	 * run the action.
	 */
	public abstract void act();

	/**
	 * Can the action be run?
	 *
	 * @return if possible
	 */
	public abstract boolean canDoIt();

	/**
	 * Gets the direction of an action.
	 *
	 * @return the direction of the action
	 */
	public Coordinates getDirection() {
		return direction;
	}

	/**
	 * Gets the source robot.
	 *
	 * @return the source of the action
	 */
	public static Robot getRobotSource() {
		return robotIn;
	}

}
