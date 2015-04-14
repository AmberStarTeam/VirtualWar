package org.virtualwar.ia;

import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;

public class RandomInteligence extends Inteligence {
	
	Random ran = new Random();

	public RandomInteligence(List<Robot> robots, int team) {
		super(robots, team);
	}

	public RandomInteligence(int team) {
		super(team);
	}

	@Override
	public Action makeTurn() {
		List<Robot> lsRobot = super.getLsRobot();
		if (lsRobot == null) {
			return null;
		}
		while(true){
			Robot tmp = lsRobot.get(ran.nextInt(lsRobot.size()));
			Boolean state = true;
			if (tmp instanceof Scavenger) {
				 state = ran.nextBoolean() && ran.nextBoolean();
			}
			
			if (state && tmp.canAttack()) {
				List<Action> lsAct = tmp.getAvailableMove();
				return lsAct.get(ran.nextInt(lsAct.size()));
			} else if (tmp.canMove()) {
				List<Action> lsAct = tmp.getAvailableMove();
				return lsAct.get(ran.nextInt(lsAct.size()));
			}
		}

	}

	@Override
	public List<Robot> getInitialRobots(int numberOfBots) {
		// TODO Auto-generated method stub
		return null;
	}

}
