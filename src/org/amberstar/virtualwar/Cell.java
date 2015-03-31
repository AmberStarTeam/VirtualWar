package org.amberstar.virtualwar;

import java.util.List;

/**
 * @author Asus UX302L
 *
 */
public abstract class Cell {
	private int mine;
	private int home;
	private Coordinates coordinates;
	private Robot robotIn;
	private boolean isObscacle;

	public Cell(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Cell(Coordinates coordinates, int bases) {
		this.coordinates = coordinates;
		this.home = bases;
	}

	/**
	 * @return the isObscacle
	 */
	public boolean isObscacle() {
		return isObscacle;
	}

	/**
	 * set the Cell as osbacle
	 */
	public void setObscacle() {
		this.isObscacle = true;
	}
	
	public void removeObscacle(){
		this.isObscacle = false;
	}

	/**
	 * @return the number of mines
	 */
	public int mineContains() {
		return mine;
	}

	/**
	 * @param mineIn the mine to set
	 */
	public void setMine(int mineIn) {
		this.mine = mineIn;
	}

	/**
	 * @return if it's a base, 0 = no, else team
	 */
	public int isBase() {
		return home;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @return the robotIn
	 */
	public Robot getRobotIn() {
		return robotIn;
	}

	public void setRobotIn(Robot robot) {
		this.robotIn = robot;
	}

	abstract public List<Robot> getContents();

	abstract void moveOn(Robot robot);

	abstract void addMine(int team);

	abstract void clearBox();

}
