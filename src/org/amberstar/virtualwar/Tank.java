package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Tank extends Robot {

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
    public Tank(int team, Coordinates coordinates, Board board) {
        super(team, coordinates, board, REGEN_BASE, MAX_ENERGY);
        // TODO Auto-generated constructor stub
    }

    @Override
    public int getCostAction() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCoastMoving() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public int getDamageTaken() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return "Tank";
    }

    @Override
    public List<Coordinates> getMoving() {
        List<Coordinates> retVal = new ArrayList<Coordinates>();
        for (Coordinates toA : Constant.MOVE_TANK) {
            Coordinates tmp = toA.add(getCoordinates());
            if (!getBoard().isValid(tmp) || getBoard().isObstacle(tmp)
                    || getBoard().isMine(tmp) == super.getTeam()) {
                tmp = tmp.minus(toA.divide(2));
            }
            if (getBoard().isValid(tmp) && !getBoard().isObstacle(tmp)
                    && getBoard().isMine(tmp) != super.getTeam()) {
                retVal.add(tmp);
            }
        }
        return (retVal.size() == 0) ? null : retVal;
    }

    @Override
    public int getRange() {
        // TODO Auto-generated method stub
        return 10;
    }

}
