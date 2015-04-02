package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Shooter extends Robot {

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
        super(team, coordinates, board, Constant.SHOOTER_ENERGY_REGEN,
				Constant.SHOOTER_ENERGY_INITIAL);
    }

    @Override
    public int getCostAction() {
        return Constant.SHOOTER_ENERGY_ACTION;
    }

    @Override
    public int getCostMoving() {
        return Constant.SHOOTER_ENERGY_MOVE;
    }

    @Override
    public int getDamageTaken() {
        return Constant.SHOOTER_DAMAGE_SUBMIT;
    }

    @Override
    public int getRange() {
        return Constant.SHOOTER_RANGE;
    }

    @Override
    public String getType() {
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

}
