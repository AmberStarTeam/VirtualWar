package org.virtualwar.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.action.Move;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.AStarPathFinder;
import org.virtualwar.util.pathfinding.Path;

public class AdvancedIntelligence extends Inteligence {
	private Random ran = new Random();
	private Coordinates Ennemy;
	private AStarPathFinder pathFindDiag;
	private AStarPathFinder pathFindStrait;

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 */
	public AdvancedIntelligence() {
		super();
	}

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public AdvancedIntelligence(int team, Board board) {
		super(team, board);
		if (board != null) {
			pathFindDiag = new AStarPathFinder(super.getBoard(), 10000, true);
			pathFindStrait = new AStarPathFinder(super.getBoard(), 10000,
					false, 2);
		}
	}

	/**
	 * Instantiates a new advanced inteligence.
	 *
	 * @param robots
	 *            the robots of the team
	 * @param team
	 *            the team
	 * @param board
	 *            the board
	 */
	public AdvancedIntelligence(List<Robot> robots, int team, Board board) {
		super(robots, team, board);
		if (board != null) {
			pathFindDiag = new AStarPathFinder(super.getBoard(), 10000, true);
			pathFindStrait = new AStarPathFinder(super.getBoard(), 10000,
					false, 2);
		}
	}

	@Override
	public List<Robot> getInitialRobots(int numberOfBots) {
		List<Robot> retVal = new ArrayList<Robot>();
		Coordinates cords = getBoard().getCoordsBase(getTeam());
		for (int i = 0; i < numberOfBots; i++) {
			switch (i) {
			case 0:
				retVal.add(new Tank(getTeam(), cords, getBoard()));
				break;
			case 1:
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
				break;
			case 2:
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
				break;
			case 3:
				retVal.add(new Scavenger(getTeam(), cords, getBoard()));
				break;

			default:
				break;
			}
		}
		setLsRobot(retVal);
		return retVal;
	}

	@Override
	public Action makeTurn() {
		List<Robot> lsRobot = new ArrayList<Robot>(super.getLsRobot());
		List<Action> attacks = new ArrayList<Action>();
		if (lsRobot.isEmpty()) {
			return null;
		}
		for (Robot rob : lsRobot) {
			if (rob instanceof Tank && attackAndSafe(rob) && rob.canAttack()) {
				attacks.addAll(rob.getAvailableAtacks());
				return attacks.get(0);
			}
		}
		for (Robot rob : lsRobot) {
			if (rob instanceof Shooter && attackAndSafe(rob) && rob.canAttack()) {
				attacks.addAll(rob.getAvailableAtacks());
				return attacks.get(0);
			}
		}

		for (Robot rob : lsRobot) {
			if (rob instanceof Scavenger && attackAndSafe(rob)
					&& rob.canAttack()) {
				if (detectRobotDown(rob) || detectRobotUp(rob)
						|| detectRobotLeft(rob) || detectRobotRight(rob)) {
					attacks.addAll(rob.getAvailableAtacks());
					return attacks.get(0);
				}
			}
		}

		Robot tmp = lsRobot.get(ran.nextInt(lsRobot.size()));
		for (Robot rob : lsRobot) {
			if (rob instanceof Tank && (!attackAndSafe(rob)) && rob.canMove()) {
				if (getEnnemy(rob) == null) {
					List<Action> lsAct = tmp.getAvailableMove();
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
				Coordinates c = getEnnemy(rob);
				Path path = pathFindStrait.findPath(rob, rob.getCoordinates(),
						c);
				Coordinates m = path.getCoordsRelativ(1);
				Action move = new Move(rob, m);
				return move;
			}
		}
		for (Robot rob : lsRobot) {
			if (rob instanceof Shooter
					&& /* (!attackAndSafe(rob)) && */rob.canMove()) {
				if (getEnnemy(rob) == null) {
					List<Action> lsAct = tmp.getAvailableMove();
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
				Coordinates c = getEnnemy(rob);
				Path path = pathFindDiag.findPath(rob, rob.getCoordinates(), c);
				Coordinates m = path.getCoordsRelativ(1);
				Action move = new Move(rob, m);
				return move;
			}
		}
		for (Robot rob : lsRobot) {
			if (rob instanceof Scavenger
					&& /* (!attackAndSafe(rob)) && */rob.canMove()) {
				if (getEnnemy(rob) == null) {
					List<Action> lsAct = tmp.getAvailableMove();
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
				Coordinates c = getEnnemy(rob);
				Path path = pathFindDiag.findPath(rob, rob.getCoordinates(), c);
				Coordinates m = path.getCoordsRelativ(1);
				Action move = new Move(rob, m);
				return move;
			}
		}
		return null;

	}

	public boolean detectRobotUp(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getHeight() - 1 == 0) {
			return false;
		}

		for (int i = c.getHeight(); i > -1; i--) {
			Coordinates detectTank = new Coordinates(c.getWidth(), i);
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectTank).getTeam() != t) {
				return true;
			}
		}
		return false;
	}

	public boolean detectRobotLeft(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getWidth() - 1 == 0) {
			return false;
		}

		for (int i = c.getWidth(); i > -1; i--) {
			Coordinates detectTank = new Coordinates(i, c.getHeight());
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectTank).getTeam() != t) {
				return true;
			}
		}
		return false;
	}

	public boolean detectRobotDown(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getHeight() + 1 == getBoard().getHeight() - 1) {
			return false;
		}

		for (int i = c.getHeight(); i < getBoard().getHeight() + 1; i++) {
			Coordinates detectTank = new Coordinates(c.getWidth(), i);
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectTank).getTeam() != t) {
				return true;
			}
		}
		return false;
	}

	public boolean detectRobotRight(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getWidth() + 1 == getBoard().getWidth() - 1) {
			return false;
		}

		for (int i = c.getWidth(); i < getBoard().getWidth() + 2; i++) {
			Coordinates detectTank = new Coordinates(c.getWidth(), i);
			if (getBoard().getRobot(detectTank) instanceof Tank
					&& getBoard().getRobot(detectTank).getTeam() != t) {
				return true;
			}
		}
		return false;
	}

	public boolean attackAndSafe(Robot r) {
		if (detectRobotRight(r)
				&& (detectRobotDown(r)
						|| detectRobotLeft(r)
						|| detectRobotUp(r)
						|| !(detectRobotJustDown(r) instanceof Scavenger || detectRobotJustDown(r) == null)
						|| !(detectRobotJustUp(r) instanceof Scavenger || detectRobotJustDown(r) == null) || !(detectRobotJustLeft(r) instanceof Scavenger || detectRobotJustDown(r) == null))) {
			return false;
		} else if (detectRobotLeft(r)
				&& (detectRobotDown(r)
						|| detectRobotRight(r)
						|| detectRobotUp(r)
						|| !(detectRobotJustDown(r) instanceof Scavenger || detectRobotJustDown(r) == null)
						|| !(detectRobotJustUp(r) instanceof Scavenger || detectRobotJustDown(r) == null) || !(detectRobotJustRight(r) instanceof Scavenger || detectRobotJustDown(r) == null))) {
			return false;
		} else if (detectRobotUp(r)
				&& (detectRobotDown(r)
						|| detectRobotLeft(r)
						|| detectRobotRight(r)
						|| !(detectRobotJustDown(r) instanceof Scavenger || detectRobotJustDown(r) == null)
						|| !(detectRobotJustRight(r) instanceof Scavenger || detectRobotJustDown(r) == null) || !(detectRobotJustLeft(r) instanceof Scavenger || detectRobotJustDown(r) == null))) {
			return false;
		} else if (detectRobotDown(r)
				&& (detectRobotUp(r)
						|| detectRobotLeft(r)
						|| detectRobotRight(r)
						|| !(detectRobotJustRight(r) instanceof Scavenger || detectRobotJustDown(r) == null)
						|| !(detectRobotJustUp(r) instanceof Scavenger || detectRobotJustDown(r) == null) || !(detectRobotJustLeft(r) instanceof Scavenger || detectRobotJustDown(r) == null))) {
			return false;
		}
		return true;

	}

	public Robot detectRobotJustDown(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getHeight() == getBoard().getHeight() - 1) {
			return null;
		}
		Coordinates detect = new Coordinates(c.getWidth(), c.getHeight() + 1);
		if (getBoard().getRobot(detect) != null) {
			if (!(getBoard().getRobot(detect) instanceof Scavenger)
					&& getBoard().getRobot(detect).getTeam() != t) {
				return getBoard().getRobot(detect);
			}
		}
		return null;
	}

	public Robot detectRobotJustUp(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getHeight() == 0) {
			return null;
		}
		Coordinates detect = new Coordinates(c.getWidth(), c.getHeight() - 1);
		if (getBoard().getRobot(detect) != null) {
			if (!(getBoard().getRobot(detect) instanceof Scavenger)
					&& getBoard().getRobot(detect).getTeam() != t) {
				return getBoard().getRobot(detect);
			}
		}
		return null;
	}

	public Robot detectRobotJustRight(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();

		if (c.getWidth() == getBoard().getWidth() - 1) {
			return null;
		}
		Coordinates detect = new Coordinates(c.getWidth() + 1, c.getHeight());
		if (getBoard().getRobot(detect) != null) {
			if (!(getBoard().getRobot(detect) instanceof Scavenger)
					&& getBoard().getRobot(detect).getTeam() != t) {
				return getBoard().getRobot(detect);
			}
		}
		return null;
	}

	public Robot detectRobotJustLeft(Robot r) {
		Coordinates c = r.getCoordinates();
		int t = r.getTeam();
		if (c.getWidth() == 0) {
			return null;
		}
		Coordinates detect = new Coordinates(c.getWidth() - 1, c.getHeight());
		if (getBoard().getRobot(detect) != null) {
			if (!(getBoard().getRobot(detect) instanceof Scavenger)
					&& getBoard().getRobot(detect).getTeam() != t) {
				return getBoard().getRobot(detect);
			}
		}
		return null;
	}

	public Coordinates getEnnemy(Robot r) {
		Ennemy = new Coordinates(100, 100);
		List<Robot> lsRobot = super.getLsRobot();
		List<Coordinates> c = new ArrayList<Coordinates>();
		for (Robot rob : lsRobot) {
			if (rob.getTeam() != r.getTeam()) {
				c.add(rob.getCoordinates());
			}
		}
		return getPlusProche(r, c, 0);
	}

	public Coordinates getPlusProche(Robot r, List<Coordinates> c, int index) {
		if (c.isEmpty()) {
			return null;
		}
		if (index == c.size() - 1) {
			return Ennemy;
		}
		int x = c.get(index).getWidth() - r.getCoordinates().getWidth();
		if (x < 0) {
			x = x - (2 * x);
		}
		int y = c.get(index).getHeight() - r.getCoordinates().getHeight();
		if (y < 0) {
			y = y - (2 * y);
		}
		int x2 = Ennemy.getWidth() - r.getCoordinates().getWidth();
		if (x2 < 0) {
			x2 = x2 - (2 * x2);
		}
		int y2 = Ennemy.getHeight() - r.getCoordinates().getHeight();
		if (y2 < 0) {
			y2 = y2 - (2 * y2);
		}
		if (x + y < x2 + y2) {
			Ennemy = new Coordinates(c.get(index).getWidth(), c.get(index)
					.getHeight());
		}
		index = index + 1;
		return getPlusProche(r, c, index);

	}
}
