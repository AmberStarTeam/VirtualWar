package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

public class Case extends Cell {
	

	public Case(Coordinates coordinates) {
		super(coordinates);
	}

	@Override
	public List<Robot> getContents() {
		List<Robot> tmp = new ArrayList<Robot>();
		tmp.add(getRobotIn());
		return tmp;
	}

	@Override
	void moveOn(Robot robot) {
		// robot.getCoordinates();
		robot.setCoordinates(super.getCoordinates());
		super.setRobotIn(robot);
	}

	@Override
	public void addMine(int team) {
		super.setMine(team);
	}

	@Override
	void clearBox() {
		super.setRobotIn(null);
	}

}
