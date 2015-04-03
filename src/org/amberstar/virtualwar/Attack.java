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
 * The Attack Class.
 *
 * @author amberStar
 */
public class Attack extends Action {
    /**
     * make a robot attack in a direction
     * 
     * @param robot
     *            the robot from
     * @param dir
     *            the direction in 0 1 coordinates
     */
    public Attack(Robot robot, Coordinates dir) {
        super(robot, dir);
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @return the Robot of objective, if none then null
     */
    private Robot getObjectif() {
        for (int i = 0; i < super.getRobotSource().getRange(); i++) {
            Coordinates tmp = super.getRobotSource().getCoordinates()
                    .add(super.getDirection().times(i + 1));
            Cell cellOf = super.getRobotSource().getBoard().getCell(tmp);
            if (cellOf.isObstacle() || cellOf.isBase() != 0) {
                return null;
            } else if (cellOf.getRobotIn() != null) {
                if (cellOf.getRobotIn().getTeam() == super.getRobotSource()
                        .getTeam()) {
                    return null;
                }
                return cellOf.getRobotIn();
            }
        }
        return null;
    }

    @Override
    void act() {
        if (super.getRobotSource().getEnergy()
                - super.getRobotSource().getCostAction() < 0) {
            return;
        }

        if (!(super.getRobotSource() instanceof Scavenger)) {

            Robot toAttack = getObjectif();
            if (toAttack == null
                    || (super.getRobotSource() instanceof Tank && toAttack
                            .getCoordinates().minus(getDirection())
                            .equals(super.getRobotSource().getCoordinates()))) {
                System.out.println("Nothing to attack");
                return;
            }
            super.getRobotSource().removeEnergy(
                    super.getRobotSource().getCostAction());
            toAttack.hasBeenShoot();
            new ThreadSoundRun(super.getRobotSource().getAttackSound())
                    .start();

        } else {
            Scavenger loc = (Scavenger) super.getRobotSource();
            if (loc.getStock() <= 0) {
                return;
            }
            Coordinates cords = loc.getCoordinates().add(super.getDirection());
            if (loc.getBoard().isMine(cords) != 0) {
                System.out.println("Il y a déjà une mine !");
                return;
            }
            if (loc.getBoard().isBase(cords) != 0) {
                System.out.println("Tu ne peut pas miner une base !");
                return;
            }
            if (loc.getBoard().getRobot(cords) != null) {
                System.out.println("Tu ne peut pas miner un robot !");
                return;
            }
            loc.getBoard().setMine(cords, loc.getTeam());
            loc.removeEnergy(loc.getCostAction());
            loc.dropMine();

        }
    }
}
