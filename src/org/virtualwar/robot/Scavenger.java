package org.virtualwar.robot;
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


import java.util.List;

import org.virtualwar.action.Attack;
import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * The Scavenger Class.
 *
 * @author Nicolas Beaussart
 */
public class Scavenger extends Robot {

	/** the number of mine inside. */
	private int nmbMine = Constant.SCAVENGER_MAX_MINE;

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
	public Scavenger(int team, Coordinates coordinates, Board board) {
		super(team, coordinates, board, Constant.SCAVENGER_ENERGY_REGEN,
				Constant.SCAVENGER_ENERGY_INITIAL);
	}

	/**
	 * Gets the stock.
	 *
	 * @return the number of mine in inventory
	 */
	public int getStock() {
		return nmbMine;
	}

	/**
     * Sets the number of mine inside.
     *
     * @param nmbMine
     *            the new number of mine inside
     */
	public void setNmbMine(int nmbMine) {
		if (nmbMine < 0) {
			this.nmbMine = 0;
		} else if (nmbMine > Constant.SCAVENGER_MAX_MINE) {
			this.nmbMine = Constant.SCAVENGER_MAX_MINE;
		} else {
			this.nmbMine = nmbMine;
		}
	}


	/**
     * Drop mine.
     */
	public void dropMine() {
		this.nmbMine--;
		if (nmbMine < 0) {
			this.nmbMine = 0;
		} else if (nmbMine > Constant.SCAVENGER_MAX_MINE) {
			this.nmbMine = Constant.SCAVENGER_MAX_MINE;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getCostAction()
	 */
	@Override
	public int getCostAction() {
		return Constant.SCAVENGER_ENERGY_ACTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getCostMoving()
	 */
	@Override
	public int getCostMoving() {
		return Constant.SCAVENGER_ENERGY_MOVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getDamageTaken()
	 */
	@Override
	public int getDamageTaken() {
		return Constant.SCAVENGER_DAMAGE_SUBMIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getRange()
	 */
	@Override
	public int getRange() {
		return Constant.SCAVENGER_RANGE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getType()
	 */
	@Override
	public String getType() {
		return "Scavenger";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getMoving()
	 */
	@Override
	public List<Coordinates> getMoving() {
	    return Constant.MOVE_LIGHT_ROBOT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#canAttack()
	 */
	@Override
	public boolean canAttack() {
	    for (Coordinates cords : Constant.CARDINAL_DIRECTION) {
            if (!new Attack(this, cords).canDoIt()) {
                return false;
            }
        }
	    return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getAttackSound()
	 */
	@Override
	public String getAttackSound() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#getMoveSound()
	 */
	@Override
	public String getMoveSound() {
		return "sounds/running.wav";
    }

    /* (non-Javadoc)
     * @see org.amberstar.virtualwar.Robot#runBaseAction()
     */
    @Override
    public void runBaseAction() {
        this.nmbMine = Constant.SCAVENGER_MAX_MINE;
        setEnergy(super.getEnergy() + super.getRegenBase());
    }

}
