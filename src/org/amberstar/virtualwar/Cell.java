package org.amberstar.virtualwar;

import java.util.List;

/**
 * @author Asus UX302L
 *
 */
public abstract class Cell {
    /** mine contains */
    private int mine;
    /** if is home */
    private int home;
    /** the coordinate of the current Cell */
    private Coordinates coordinates;
    /** the current robot inside */
    private Robot robotIn;
    /** if is obstacle */
    private boolean isObstacle;

    /**
     * default constructor
     * 
     * @param coordinates
     *            the coordinate of Cell
     */
    public Cell(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * the constructor for bases
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
     * @return the isObstacle
     */
    public boolean isObstacle() {
        return isObstacle;
    }

    /**
     * set the Cell as obstacle
     */
    public void setObstacle() {
        this.isObstacle = true;
    }

    /**
	 * 
	 */
    public void removeObstacle() {
        this.isObstacle = false;
    }

    /**
     * @return the number of mines
     */
    public int mineContains() {
        return mine;
    }

    /**
     * @param mineIn
     *            the mine to set
     */
    public void setMine(int mineIn) {
        this.mine = mineIn;
    }

    /**
     * @return if it's a base, 0 = no, else team
     */
    public int isBase() {
        return home;
    }

    /**
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return the robotIn
     */
    public Robot getRobotIn() {
        return robotIn;
    }

    /**
     * @param robot
     *            set the robot inside the case
     */
    public void setRobotIn(Robot robot) {
        this.robotIn = robot;
    }

    /** @return a list of robot */
    public abstract List<Robot> getContents();

    /**
     * @param robot
     *            the robot to get in
     * @return if success
     */
    abstract boolean moveOn(Robot robot);

    /**
     * set the mine inside
     * 
     * @param team
     *            the team number
     * @return if success
     */
    abstract boolean addMine(int team);

    /** clear the cell */
    abstract void clearBox();

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
