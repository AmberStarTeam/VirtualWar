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
package org.virtualwar.action;

import org.virtualwar.board.Base;
import org.virtualwar.board.Cell;
import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

/**
 * The Attack Class.
 *
 * @author amberstar
 */
public class Attack extends Action {

	/**
	 * make a robot attack in a direction.
	 *
	 * @param robot
	 *            the robot from
	 * @param dir
	 *            the direction in 0 1 coordinates
	 */
	public Attack(Robot robot, Coordinates dir) {
		super(robot, dir);
	}

	/*
	 * . (non-Javadoc)
	 * 
	 * @see org.virtualwar.action.Action#act()
	 */
	@Override
	public void act() {
		if (super.getRobotSource().getEnergy()
				- super.getRobotSource().getCostAction() < 0) {
			System.out.println(TextData.ERROR_ATTACK_NOT_ENOUTH_ENERGY);
			return;
		}
		if (super.getRobotSource().getCell() instanceof Base) {
			return;
		}

		if (!(super.getRobotSource() instanceof Scavenger)) {

			Robot toAttack = getObjectif();

			if (toAttack == null) {
				System.out.println(TextData.ERROR_ATTACK_NOTHING_TO_ATTACK);
				return;
			}

			if (toAttack.getCell() instanceof Base) {
				System.out.println(TextData.ERROR_ATTACK_CANT_FROM_BASE);
				return;
			}

			super.getRobotSource().removeEnergy(
					super.getRobotSource().getCostAction());
			toAttack.hasBeenShoot(super.getRobotSource());
			System.out.println(toAttack.getType()
					+ TextData.ROBOT_HAS_BEEN_SHOT);
			new ThreadSoundRun(super.getRobotSource().getAttackSound()).start();

		} else {
			Scavenger loc = (Scavenger) super.getRobotSource();
			if (loc.getStock() <= 0) {
				System.out.println(TextData.ERROR_ATTACK_CANT_ATTACK_BASE);
				return;
			}
			Coordinates cords = loc.getCoordinates().add(super.getDirection());
			if (loc.getBoard().isMine(cords) != Constant.ID_TEAM_NULL) {
				System.out.println(TextData.ERROR_ATTACK_ALREADY_MINED);
				return;
			}
			if (loc.getBoard().isBase(cords) != Constant.ID_TEAM_NULL) {
				System.out.println(TextData.ERROR_ATTACK_CANT_MINE_BASE);
				return;
			}
			if (loc.getBoard().getRobot(cords) != null) {
				System.out.println(TextData.ERROR_ATTACK_CANT_MINE_ROBOT);
				return;
			}
			if (!loc.getBoard().isValid(cords)) {
				System.out.println(TextData.ERROR_ATTTACK_IS_OUT_OF_BOUND);
				return;
			}
			if (loc.getBoard().isObstacle(cords)) {
				System.out.println(TextData.ERROR_ATTACK_CANT_MINE_OBSTACLE);
				return;
			}
			loc.getBoard().setMine(cords, loc.getTeam());
			loc.removeEnergy(loc.getCostAction());
			loc.dropMine();
			System.out.println(TextData.ROBOT_MINE);
		}
	}

	/*
	 * . (non-Javadoc)
	 * 
	 * @see org.virtualwar.action.Action#canDoIt()
	 * 
	 * @return if it can run the action
	 */
	@Override
	public boolean canDoIt() {

		if (super.getRobotSource().getCell() instanceof Base) {
			return false;
		}
		if (super.getRobotSource().getEnergy()
				- super.getRobotSource().getCostAction() < 0) {
			return false;
		}

		if (!(super.getRobotSource() instanceof Scavenger)) {

			Robot toAttack = getObjectif();
			if (toAttack == null) {
				return false;
			}

			if (toAttack.getCell() instanceof Base) {
				return false;
			}
			if (super.getRobotSource() instanceof Tank
					&& toAttack.getCoordinates().minus(getDirection())
							.equals(super.getRobotSource().getCoordinates())) {
				return false;
			}

		} else {
			Scavenger loc = (Scavenger) super.getRobotSource();
			if (loc.getStock() <= 0) {
				return false;
			}
			Coordinates cords = loc.getCoordinates().add(super.getDirection());
			if (!loc.getBoard().isValid(cords)) {
				return false;
			}
			if (loc.getBoard().isMine(cords) != Constant.ID_TEAM_NULL) {
				return false;
			}
			if (loc.getBoard().isBase(cords) != Constant.ID_TEAM_NULL) {
				return false;
			}
			if (loc.getBoard().getRobot(cords) != null) {
				return false;
			}
			if (loc.getBoard().isObstacle(cords)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the objectif.
	 *
	 * @return the Robot of objective, if none then null
	 */
	private Robot getObjectif() {
		for (int i = 0; i < super.getRobotSource().getRange(); i++) {
			Coordinates tmp = super.getRobotSource().getCoordinates()
					.add(super.getDirection().times(i + 1));
			Cell cellOf = super.getRobotSource().getBoard().getCell(tmp);
			if (cellOf == null || cellOf.isObstacle() || cellOf.isBase() != 0) {
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
}
