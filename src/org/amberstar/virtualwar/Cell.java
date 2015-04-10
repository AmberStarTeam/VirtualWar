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

import java.util.List;

/**
 * The Cell Class.
 *
 * @author amberStar
 */
public abstract class Cell {

    /** mine contains. */
    private int mine;

    /** if is home. */
    private int home;

    /** the coordinate of the current Cell. */
    private Coordinates coordinates;

    /** the current robot inside. */
    private Robot robotIn;

    /** if is obstacle. */
    private boolean isObstacle;

    /**
     * default constructor.
     *
     * @param coordinates
     *            the coordinate of Cell
     */
    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * the constructor for bases.
     *
     * @param coordinates
     *            the coordinates of the Cell
     * @param bases
     *            the base number
     */
    public Cell(Coordinates coordinates, int bases) {
        this.coordinates = coordinates;
        this.home = bases;
    }

    /**
     * Checks if is obstacle.
     *
     * @return the isObstacle
     */
    public boolean isObstacle() {
        return isObstacle;
    }

    /**
     * set the Cell as obstacle.
     */
    public void setObstacle() {
        this.isObstacle = true;
    }

    /**
     * Removes the obstacle.
     */
    public void removeObstacle() {
        this.isObstacle = false;
    }

    /**
     * Mine contains.
     *
     * @return the number of mines
     */
    public int mineContains() {
        return mine;
    }

    /**
     * Sets the mine contains.
     *
     * @param mineIn
     *            the mine to set
     */
    public void setMine(int mineIn) {
        this.mine = mineIn;
    }

    /**
     * Checks if is base.
     *
     * @return if it's a base, 0 = no, else team
     */
    public int isBase() {
        return home;
    }

    /**
     * Gets the coordinate of the current Cell.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets the current robot inside.
     *
     * @return the robotIn
     */
    public Robot getRobotIn() {
        return robotIn;
    }

    /**
     * Sets the current robot inside.
     *
     * @param robot
     *            set the robot inside the case
     * @return if sucess
     */
    public boolean setRobotIn(Robot robot) {
        if (robotIn != null || isObstacle
                || (mine != 0 && robot.getTeam() == mine)) {
            return false;
        } else {
            robot.setCoordinates(coordinates);
            robotIn = robot;
            return true;
        }

    }

    /**
     * Sets the current robot inside.
     *
     * @param robot
     *            set the robot inside the case
     * @return if sucess
     */
    public boolean removeRobotIn(Robot robot) {
    	if (robot == null) {
			robotIn = null;
			return true;
		}
    	if (robotIn == null) {
            robotIn = null;
            return true;
		}
        if (robotIn.equals(robot)) {
            robotIn = null;
            robot.setCoordinates(null);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move on.
     *
     * @param robot
     *            the robot to get in
     * @return if success
     */
    public boolean moveOn(Robot robot) {
        if (robot.getCoordinates() == null) {
            return setRobotIn(robot);
        }
        return robot.getBoard().getCell(robot.getCoordinates())
                .removeRobotIn(robot)
                && setRobotIn(robot);
    }

    /**
     * Gets the contents.
     *
     * @return a list of robot
     */
    public abstract List<Robot> getContents();

    /**
     * set the mine inside.
     *
     * @param team
     *            the team number
     * @return if success
     */
    public abstract boolean addMine(int team);

    /**
     * clear the cell.
     */
    public abstract void clearBox();

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + home;
        result = prime * result + (isObstacle ? 1231 : 1237);
        result = prime * result + mine;
        result = prime * result + ((robotIn == null) ? 0 : robotIn.hashCode());
        return result;
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
        Cell other = (Cell) obj;
        if (coordinates == null) {
            if (other.coordinates != null) {
                return false;
            }
        } else if (!coordinates.equals(other.coordinates)) {
            return false;
        }
        if (home != other.home) {
            return false;
        }
        if (isObstacle != other.isObstacle) {
            return false;
        }
        if (mine != other.mine) {
            return false;
        }
        if (robotIn == null) {
            if (other.robotIn != null) {
                return false;
            }
        } else if (!robotIn.equals(other.robotIn)) {
            return false;
        }
        return true;
    }

}
