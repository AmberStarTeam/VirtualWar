package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Shooter extends Robot {

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
    public Shooter(int team, Coordinates coordinates, Board board) {
        super(team, coordinates, board, REGEN_BASE, MAX_ENERGY);
    }

    @Override
    public int getCostAction() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCoastMoving() {
        // TODO Auto-generated method stub
        return 11;
    }

    @Override
    public int getDamageTaken() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Shooter";
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
        return 4;
    }

}
