package org.amberstar.virtualwar;

import java.util.List;
/**
 * 
 * @author beaussan
 *
 */
public class Base extends Cell {
    /**L
     * constructor
     * @param coordinates coordinates of Base
     * @param baseTeam Owner of base
     */
	public Base(Coordinates coordinates, int baseTeam) {
		super(coordinates, baseTeam);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Robot> getContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void moveOn(Robot robot) {
		// TODO Auto-generated method stub

	}

	@Override
	void addMine(int team) {
		// TODO Auto-generated method stub

	}

	@Override
	void clearBox() {
		// TODO Auto-generated method stub
	}

}
