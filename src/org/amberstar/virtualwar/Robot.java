package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

public abstract class Robot {
	private int energy;
	private int team;
	private Coordinates coordinates;
	final private int regenBase;
	final private int maxEng;
	private Board board;

	public Robot(int team, Coordinates coordinates, Board board, int regenBase, int maxEng) {
		this.team = team;
		this.coordinates = coordinates;
		this.regenBase = regenBase;
		this.maxEng = maxEng;
		this.energy = maxEng;
		this.board = board;
	}
	
	public Board getBoard(){
		return board;
	}

	public int getRegenBase() {
		return regenBase;
	}

	public int getMaxEng() {
		return maxEng;
	}

	public boolean isInBase() {
		return false;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates
	 *            the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * @param energy
	 *            the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	abstract public int getCostAction();

	abstract public int getCoastMoving();

	abstract public int getDamageTaken();

	abstract public String getType();

	abstract public List<Coordinates> getMoving();
	
	abstract public int getRange();
	
	public boolean canAttack(){
		if (getEnergy() - getCostAction() < 0) {
			return false;
		}
		List<Coordinates> forDir = new ArrayList<Coordinates>();
		forDir.add(Constant.UP);
		forDir.add(Constant.DOWN);
		forDir.add(Constant.RIGHT);
		forDir.add(Constant.LEFT);
		
		for (Coordinates directions : forDir) {
			for (int i = 1; i <= getRange();  i++) {
				Coordinates tmp = directions.times(i).add(coordinates);
				if (!board.isValid(tmp)){
					break;
				 }
				if(board.isObstacle(tmp)){
					break;
				}
				Robot other = board.getRobot(tmp);
				if (other == null) {
					continue;
				}
				if (other.team == team){
					return false;
				}else{
					return true;
				}
			}
		}
		return false;
	}

	public void hasBeenShoot() {
		energy -= getDamageTaken();
	}

	public void hasBeenMined() {
		energy -= getDamageTaken();
	}
	// abstract public int getDamageMine();

}
