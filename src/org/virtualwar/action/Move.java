package org.virtualwar.action;

import org.virtualwar.board.Cell;
import org.virtualwar.config.TextData;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

// TODO: Auto-generated Javadoc
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

/**
 * The Move Class.
 *
 * @author amberstar
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

	/**
	 * . (non-Javadoc)
	 * @see org.virtualwar.action.Action#act()
	 */
	@Override
	public void act() {
		if (super.getRobotSource().getEnergy()
				- super.getRobotSource().getCostMoving() < 0) {
			System.out.println(TextData.ERROR_MOVE_NOT_ENOUTH_ENERGY);
			return;
		}
		Cell toAct = getObjectif();
		if (toAct == null) {
			return;
		}

		Robot rob = super.getRobotSource();
		System.out.println(toAct.getCoordinates().minus(rob.getCoordinates()));
		if (rob instanceof Tank
				&& rob.getMoving().contains(
						toAct.getCoordinates().minus(rob.getCoordinates()))) {
			Cell middle = rob.getBoard().getCell(rob.getCoordinates().add(super.getDirection().divide(2)));
			if (middle != null && middle.mineContains() != 0) {
				System.out.println(super.getRobotSource().getType()+TextData.ROBOT_HAS_BEEN_MINE);
				rob.hasBeenMined();
				middle.setMine(0);
			}

		}
		rob.getBoard().setRobot(rob, toAct.getCoordinates());
		rob.setEnergy(rob.getEnergy() - rob.getCostMoving());
		new ThreadSoundRun(rob.getMoveSound(), 1000).start();

		if (toAct.mineContains() != 0) {
			System.out.println(super.getRobotSource().getType()+TextData.ROBOT_HAS_BEEN_MINE);
			rob.hasBeenMined();
			toAct.setMine(0);
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
			// TANK CASE
			Coordinates time1 = super.getDirection().divide(2);
			Coordinates time2 = super.getDirection();
			Coordinates robCords = robSour.getCoordinates();
			if (robSour.isValid(robCords.add(time2)) == null
					&& robSour.isValid(robCords.add(time1)) == null) {
				tmp = robCords.add(time2);
			} else if (robSour.isValid(robCords.add(time1)) == null) {
				tmp = robCords.add(time1);
			} else {
				return null;
			}

		} else {
			tmp = robSour.getCoordinates().add(super.getDirection());
		}

		cellOfTmp = robSour.getBoard().getCell(tmp);

		if (cellOfTmp == null || cellOfTmp.getRobotIn() != null) {
			return null;
		}
		if (robSour.isValid(tmp) != null) {
			return null;
		}

		return cellOfTmp;
	}

	/**
	 * . (non-Javadoc)
	 * 
	 * @see org.virtualwar.action.Action#canDoIt()
	 */
	@Override
	public boolean canDoIt() {
		if (super.getRobotSource().getEnergy()
				- super.getRobotSource().getCostMoving() < 0) {
			return false;
		}
		Cell toAct = getObjectif();
		if (toAct == null) {
			return false;
		}
		return true;
	}
}
