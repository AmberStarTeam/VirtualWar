package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public class Scavenger extends Robot {
	/** the number of mine inside */
	private int nmbMine = Constant.SCAVENGER_MAX_MINE;

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
	public Scavenger(int team, Coordinates coordinates, Board board) {
		super(team, coordinates, board, Constant.SCAVENGER_ENERGY_REGEN,
				Constant.SCAVENGER_ENERGY_INITIAL);
	}

	/**
	 * 
	 * @return the number of mine in inventory
	 */
	public int getStock() {
		return nmbMine;
	}

	@Override
	public int getCostAction() {
		return Constant.SCAVENGER_ENERGY_ACTION;
	}

	@Override
	public int getCostMoving() {
		return Constant.SCAVENGER_ENERGY_MOVE;
	}

	@Override
	public int getDamageTaken() {
		return Constant.SCAVENGER_DAMAGE_SUBMIT;
	}

	@Override
	public int getRange() {
		return Constant.SCAVENGER_RANGE;
	}


	@Override
	public String getType() {
		return "Scavenger";
	}

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

	@Override
	public String getAttackSound() {
		return null;
	}

	@Override
	public String getMoveSound() {
		return "sounds/running.wav";
	}

}
