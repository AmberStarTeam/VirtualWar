package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

public class Scavenger extends Robot {

	int nmbMine = 10;

	final static int regenBase = 2;
	final static int maxEnergy = 40;

	public Scavenger(int team, Coordinates coordinates, Board board) {
		super(team, coordinates, board, regenBase, maxEnergy);
		// TODO Auto-generated constructor stub
	}

	public int getStock() {
		return nmbMine;
	}

	@Override
	public int getCostAction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCoastMoving() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDamageTaken() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Scavenger";
	}

	@Override
	public List<Coordinates> getMoving() {
		List<Coordinates> retVal = new ArrayList<Coordinates>();
		for (Coordinates toA : Constant.MOVE_LIGHT_ROBOT) {
			Coordinates tmp = toA.add(getCoordinates());
			if (getBoard().isValid(tmp)) {
				retVal.add(tmp);
			}
		}
		return (retVal.size() == 0) ? null : retVal;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 1;
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

}
