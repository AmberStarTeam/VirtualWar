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

import org.amberstar.virtualwar.sound.ThreadSoundRun;

/**
 * The Move Class.
 *
 * @author beaussan
 */
public class Move extends Action {

    /**
     * default constructor.
     *
     * @param robotIn
     *            the robot from
     * @param direction
     *            the coordinate of movement
     */
    public Move(Robot robotIn, Coordinates direction) {
        super(robotIn, direction);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.amberstar.virtualwar.Action#act()
     */
    @Override
    void act() {
        Cell toAct = getObjectif();
        if (toAct == null) {
            return;
        }

        Robot rob = super.getRobotSource();

        if (rob.getBoard().setRobot(rob, toAct.getCoordinates())) {
            rob.setEnergy(rob.getEnergy() - rob.getCostMoving());
            new ThreadSoundRun(rob.getMoveSound(), 1000).start();
        }

        if (toAct.mineContains() != 0) {
            rob.hasBeenMined();
            toAct.setMine(0);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {

            }
            new ThreadSoundRun("sounds/boom.wav", 800).start();
        }
    }

    /**
     * Gets the objectif.
     *
     * @return the cell of objective
     */
    private Cell getObjectif() {
        Coordinates tmp;
        Cell cellOfTmp;
        Robot robSour = super.getRobotSource();

        if (robSour instanceof Tank) {
            tmp = robSour.getCoordinates().add(super.getDirection().times(2));
            if (!robSour.getBoard().isValid(tmp)
                    || robSour.getBoard().getCell(tmp).getRobotIn() != null
                    || robSour.getBoard().isObstacle(tmp)
                    || robSour.getBoard().getCell(tmp).mineContains() == robSour
                            .getTeam()
                    || (robSour.getBoard().isBase(tmp) != 0 && super
                            .getRobotSource().getBoard().isBase(tmp) != super
                            .getRobotSource().getTeam())) {
                tmp = tmp.minus(super.getDirection());
            }
        } else {
            tmp = robSour.getCoordinates().add(super.getDirection());
        }

        cellOfTmp = robSour.getBoard().getCell(tmp);

        if (cellOfTmp == null || cellOfTmp.getRobotIn() != null) {
            return null;
        }
        if (cellOfTmp.isObstacle()
                || cellOfTmp.mineContains() == robSour.getTeam()
                || (robSour.getBoard().isBase(tmp) != 0 && super
                        .getRobotSource().getBoard().isBase(tmp) != super
                        .getRobotSource().getTeam())) {
            return null;
        }

        return cellOfTmp;
    }
}
