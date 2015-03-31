package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Case extends Cell {

    /**
     * public constructor
     * 
     * @param coordinates
     *            Where the Cell is
     */
    public Case(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public List<Robot> getContents() {
        List<Robot> tmp = new ArrayList<Robot>();
        tmp.add(getRobotIn());
        return tmp;
    }

    @Override
    boolean moveOn(Robot robot) {
        if (super.getRobotIn() != null) {
            return false;
        }
        // robot.getCoordinates();
        robot.setCoordinates(super.getCoordinates());
        super.setRobotIn(robot);
        return true;
    }

    @Override
    public boolean addMine(int team) {
        if (super.getRobotIn() != null || super.mineContains() != 0) {
            return false;
        }
        super.setMine(team);
        return true;
    }

    @Override
    void clearBox() {
        super.setRobotIn(null);
    }

}
