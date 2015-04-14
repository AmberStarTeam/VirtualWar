package org.virtualwar.ia;

import java.util.List;

import org.virtualwar.action.Action;
import org.virtualwar.robot.Robot;

// TODO: Auto-generated Javadoc
/**
 * The Class Inteligence.
 *
 * @author Nicolas Beaussart
 */
public abstract class Inteligence {
	
	/** The ls robot. */
	private List<Robot> lsRobot;
	
	/** The team. */
	private final int team;
	
	/**
	 * Instantiates a new inteligence.
	 *
	 * @param robots the robots
	 * @param team the team
	 */
	public Inteligence(List<Robot> robots, int team){
		lsRobot = robots;
		this.team = team;
	}
	
	/**
	 * Instantiates a new inteligence.
	 *
	 * @param team the team
	 */
	public Inteligence(int team){
		this.team = team;
	}
	
	/**
	 * Make turn.
	 */
	public abstract Action makeTurn();
	
	public abstract List<Robot> getInitialRobots(int numberOfBots);

	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Gets the ls robot.
	 *
	 * @return the ls robot
	 */
	public List<Robot> getLsRobot() {
		return lsRobot;
	}
}
