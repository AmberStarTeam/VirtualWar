package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public abstract class Robot {
    /** the current energy of the robot */
    private int energy;
    /** the current team of the robot */
    private int team;
    /** the current coordinates of the robot */
    private Coordinates coordinates;
    /** the number of energy regenerated from idle in base */
    private final int regenBase;
    /** the maximum energy it can holds */
    private final int maxEng;
    /** the current board the robot is in */
    private Board board;

    /**
     * Create a robot
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
        this.energy = maxEng;
        this.board = board;
    }

    /**
     * @return board where the robot is in
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @return the amount of energy gain per round in base
     */
    public int getRegenBase() {
        return regenBase;
    }

    /**
     * @return the maximal amount of energy the robot can have
     */
    public int getMaxEng() {
        return maxEng;
    }

    /**
     * @return if the robot is in a base
     */
    public boolean isInBase() {
        return board.isBase(coordinates) == team;
    }

    /**
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     *            the coordinates to set
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @return the team
     */
    public int getTeam() {
        return team;
    }

    /**
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

    /** @return the cost of a action */
    public abstract int getCostAction();

    /** @return the coast of moving */
    public abstract int getCoastMoving();

    /** @return the amount of damage it takes */
    public abstract int getDamageTaken();

    /** @return the type of robot */
    public abstract String getType();

    /** @return the list of Coordinates it can moves */
    public abstract List<Coordinates> getMoving();

    /** @return the range of action it can perform */
    public abstract int getRange();

    /**
     * @return if robot has a valid target and can attack it
     */
    public boolean canAttack() {
        if (getEnergy() - getCostAction() < 0) {
            return false;
        }
        List<Coordinates> forDir = new ArrayList<Coordinates>();
        forDir.add(Constant.UP);
        forDir.add(Constant.DOWN);
        forDir.add(Constant.RIGHT);
        forDir.add(Constant.LEFT);

        for (Coordinates directions : forDir) {
            for (int i = 1; i <= getRange(); i++) {
                Coordinates tmp = directions.times(i).add(coordinates);
                if (!board.isValid(tmp)) {
                    break;
                }
                if (board.isObstacle(tmp)) {
                    break;
                }
                Robot other = board.getRobot(tmp);
                if (other == null) {
                    continue;
                }
                return (other.team == team);
            }
        }

        return false;
    }

    /**
     * submit damage from a shoot
     */
    public void hasBeenShoot() {
        energy -= getDamageTaken();
    }

    /**
     * submit damage from a mine
     */

    public void hasBeenMined() {
        energy -= getDamageTaken();
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Robot other = (Robot) obj;
        if (board == null) {
            if (other.board != null)
                return false;
        } else if (!board.equals(other.board))
            return false;
        if (coordinates == null) {
            if (other.coordinates != null)
                return false;
        } else if (!coordinates.equals(other.coordinates))
            return false;
        if (energy != other.energy)
            return false;
        if (maxEng != other.maxEng)
            return false;
        if (regenBase != other.regenBase)
            return false;
        if (team != other.team)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Robot [energy=" + energy + ", team=" + team + ", coordinates="
                + coordinates + ", regenBase=" + regenBase + ", maxEng="
                + maxEng + ", moves=" + getMoving() + "]";
    }

}
