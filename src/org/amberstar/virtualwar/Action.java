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
package org.amberstar.virtualwar;

// TODO: Auto-generated Javadoc
/**
 * The Action Class.
 * @author amberStar
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
    abstract void act();
    
    /** @return if possible */
    abstract boolean canDoIt();

}
