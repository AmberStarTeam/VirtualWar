package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Tank extends Robot {
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
        super(team, coordinates, board, Constant.TANK_ENERGY_REGEN,
				Constant.TANK_ENERGY_INITIAL);
    }

    @Override
    public int getCostAction() {
        return Constant.TANK_ENERGY_ACTION;
    }

    @Override
    public int getCostMoving() {
        return Constant.TANK_ENERGY_MOVE;
    }

    @Override
    public int getDamageTaken() {
        return Constant.TANK_DAMAGE_SUBMIT;
    }

    @Override
    public int getRange() {
        return Constant.TANK_RANGE;
    }

    @Override
    public String getType() {
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

}
