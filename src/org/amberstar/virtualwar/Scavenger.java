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

// TODO: Auto-generated Javadoc
/**
 * The Scavenger Class.
 *
 * @author amberStar
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
		List<Coordinates> retVal = new ArrayList<Coordinates>();
		for (Coordinates toA : Constant.MOVE_LIGHT_ROBOT) {
			Coordinates tmp = super.getCoordinates().add(toA);
			if (getBoard().isValid(tmp) && !getBoard().isObstacle(tmp)
					&& getBoard().isMine(tmp) != super.getTeam()) {
				retVal.add(tmp);
			}
		}
		return (retVal.size() == 0) ? null : retVal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.amberstar.virtualwar.Robot#canAttack()
	 */
	@Override
	public boolean canAttack() {
		if (super.getEnergy() - getCostAction() < 0) {
			return false;
		}
		List<Coordinates> forDir = new ArrayList<Coordinates>();
		forDir.add(Constant.UP);
		forDir.add(Constant.DOWN);
		forDir.add(Constant.RIGHT);
		forDir.add(Constant.LEFT);
		for (Coordinates dir : forDir) {
			Coordinates tmp = dir.add(getCoordinates());
			if (!getBoard().isValid(tmp)) {
				continue;
			}
			if (getBoard().isObstacle(tmp)) {
				continue;
			}
			if (getBoard().isMine(tmp) != 0) {
				continue;
			}
			if (getBoard().getCell(tmp).getRobotIn() != null) {
				continue;
			}
			if (getBoard().isMine(tmp) == 0) {
				return true;
			}
		}
		return false;
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
