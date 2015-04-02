package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Scavenger extends Robot {
    /** the number of mine inside */
    private int nmbMine = 10;

    /** the number of energy regen in base */
    static final int REGEN_BASE = 2;
    /** the maximal ammout of energy */
    static final int MAX_ENERGY = 40;

    /**
     * Default constructor
     * 
     * @param team
     *            the team of the robot
     * @param coordinates
     *            the coordinate of the robot
     * @param board
     *            the board where the robot is in
     */
    public Scavenger(int team, Coordinates coordinates, Board board) {
        super(team, coordinates, board, REGEN_BASE, MAX_ENERGY);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * @return the number of mine in inventory
     */
    public int getStock() {
        return nmbMine;
    }

    @Override
    public int getCostAction() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCoastMoving() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDamageTaken() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Scavenger";
    }

    @Override
    public List<Coordinates> getMoving() {
        List<Coordinates> retVal = new ArrayList<Coordinates>();
        for (Coordinates toA : Constant.MOVE_LIGHT_ROBOT) {
            Coordinates tmp = super.getCoordinates().add(toA);
            if (getBoard().isValid(tmp) && !getBoard().isObstacle(tmp)
                    && getBoard().isMine(tmp) != super.getTeam()) {
                retVal.add(tmp);
            } else {
                System.out.println("Not valid : " + tmp);
            }
        }
        return (retVal.size() == 0) ? null : retVal;
    }

    @Override
    public int getRange() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public boolean canAttack() {
        if (super.getEnergy() - getCostAction() < 0) {
            return false;
        }
        List<Coordinates> forDir = new ArrayList<Coordinates>();
        forDir.add(Constant.UP);
        forDir.add(Constant.DOWN);
        forDir.add(Constant.RIGHT);
        forDir.add(Constant.LEFT);
        for (Coordinates dir : forDir) {
            Coordinates tmp = dir.add(getCoordinates());
            if (!getBoard().isValid(tmp)) {
                continue;
            }
            if (getBoard().isObstacle(tmp)) {
                continue;
            }
            if (getBoard().isMine(tmp) != 0) {
                continue;
            }
            if (getBoard().getCell(tmp).getRobotIn() != null) {
                continue;
            }
            if (getBoard().isMine(tmp) == 0) {
                return true;
            }
        }
        return false;
    }

}
