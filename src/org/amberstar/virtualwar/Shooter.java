package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

public class Shooter extends Robot {

	final static int regenBase = 2;
	final static int maxEnergy = 40;
	
	public Shooter(int team, Coordinates coordinates, Board board) {
		super(team, coordinates, board, regenBase, maxEnergy);
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
		return "Shooter";
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
		return (retVal.size() == 0)? null : retVal;
	}

	@Override
	public int getRange() {
		// TODO Auto-generated method stub
		return 4;
	}

}
