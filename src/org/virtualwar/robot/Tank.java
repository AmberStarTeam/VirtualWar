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
package org.virtualwar.robot;

import java.util.List;

import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;
import org.virtualwar.util.Coordinates;

/**
 * The Tank Class.
 *
 * @author amberstar
 */
public class Tank extends Robot {

	/**
	 * Default constructor.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getAttackSound()
	 */
	@Override
	public String getAttackSound() {
		return getClass().getResource("/org/virtualwar/res/shotTank.wav").getPath().replaceAll("%20", " ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getCostAction()
	 */
	@Override
	public int getCostAction() {
		return Constant.TANK_ENERGY_ACTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getCostMoving()
	 */
	@Override
	public int getCostMoving() {
		return Constant.TANK_ENERGY_MOVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getDamageTaken()
	 */
	@Override
	public int getDamageTaken() {
		return Constant.TANK_DAMAGE_SUBMIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getMoveSound()
	 */
	@Override
	public String getMoveSound() {
		return getClass().getResource("/org/virtualwar/res/moveTank.wav").getPath().replaceAll("%20", " ");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getMoving()
	 */
	@Override
	public List<Coordinates> getMoving() {
		return Constant.MOVE_TANK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getRange()
	 */
	@Override
	public int getRange() {
		return Constant.TANK_RANGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getType()
	 */
	@Override
	public String getType() {
		return TextData.TANK_NAME.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.robot.Robot#runBaseAction()
	 */
	@Override
	public void runBaseAction() {
		setEnergy(super.getEnergy() + super.getRegenBase());
	}

}
