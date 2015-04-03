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

import java.util.ArrayList;
import java.util.List;

/**
 * The Case Class that contains mines or is obstacle or contains a robot
 *
 * @author amberStar
 */
public class Case extends Cell {

    /**
     * public constructor.
     *
     * @param coordinates
     *            Where the Cell is
     */
    public Case(Coordinates coordinates) {
        super(coordinates);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#getContents()
     */
    @Override
    public List<Robot> getContents() {
        List<Robot> tmp = new ArrayList<Robot>();
        tmp.add(getRobotIn());
        return tmp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#moveOn(org.amberstar.virtualwar.Robot)
     */
    @Override
    public boolean moveOn(Robot robot) {
        if (super.getRobotIn() != null || robot == null) {
            System.out.println("Can't print");
            return false;
        }
        // robot.getCoordinates();
        if (robot.getCoordinates() != null) {
            robot.getBoard().getCell(robot.getCoordinates()).setRobotIn(null);
        }
        robot.setCoordinates(super.getCoordinates());
        super.setRobotIn(robot);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#addMine(int)
     */
    @Override
    public boolean addMine(int team) {
        if (super.getRobotIn() != null || super.mineContains() != 0) {
            return false;
        }
        super.setMine(team);
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Cell#clearBox()
     */
    @Override
    public void clearBox() {
        super.setRobotIn(null);
    }

}
