package org.virtualwar.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.board.Case;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.AStarPathFinder;
import org.virtualwar.util.pathfinding.Path;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

public class IACamille extends Inteligence {

	private Random ran = new Random();

	AStarPathFinder pathDiag;
	AStarPathFinder pathStraight;

	public IACamille() {
	}

	public IACamille(int team, Board board) {
		super(team, board);
		if (board != null) {
			pathDiag = new AStarPathFinder(super.getBoard(), 10000, true);
			pathStraight = new AStarPathFinder(super.getBoard(), 10000, false,
					2);
		}
	}

	public IACamille(List<Robot> robots, int team, Board board) {
		super(robots, team, board);
		if (board != null) {
			pathDiag = new AStarPathFinder(super.getBoard(), 10000, true);
			pathStraight = new AStarPathFinder(super.getBoard(), 10000, false,
					2);
		}
	}
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.ia.Inteligence#setBoard(org.virtualwar.board.Board)
	 */
	@Override
	public void setBoard(Board board) {
		super.setBoard(board);
		pathDiag = new AStarPathFinder(board, 10000, true);
		pathStraight = new AStarPathFinder(board, 10000, false, 2);
		ran = new Random();
	}

	@Override
	public List<Robot> getInitialRobots(int numberOfBots) {
		List<Robot> r = new ArrayList<Robot>();
		Coordinates cBase = getBoard().getCoordsBase(getTeam());

		for (int i = 1; i <= numberOfBots; i++) {
			if (i == 1 || i == 2) {
				r.add(new Shooter(getTeam(), cBase, getBoard()));
			} else if (i == 3) {
				r.add(new Scavenger(getTeam(), cBase, getBoard()));
			} else if (i == 4 || i == 5) {
				r.add(new Tank(getTeam(), cBase, getBoard()));
			}
		}
		setLsRobot(r);
		return r;
	}

	@SuppressWarnings("unused")
	private Path getPathHome(Robot rob) {
		return getPathTo(rob, getBoard().getCoordsBase(getTeam()));
	}

	private Path getPathTo(Robot rob, Coordinates cords) {
		if (rob instanceof Tank) {
			return pathStraight.findPath(rob, rob.getCoordinates(), cords);
		} else {
			return pathDiag.findPath(rob, rob.getCoordinates(), cords);
		}
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

	public Robot lowRobot(Robot r1, List<Robot> ennemies) {
		Robot r = ennemies.get(0);
		if (r.getEnergy() < r1.getEnergy()) {
			for (int i = 1; i < ennemies.size(); i++) {
				if (ennemies.get(i).getEnergy() < r.getEnergy()) {
					r = ennemies.get(i);
				}
			}
		} else {
			for (int i = 1; i < ennemies.size(); i++) {
				if (ennemies.get(i).getEnergy() < r.getEnergy()) {
					r = ennemies.get(i);
				}
			}
		}
		return r;
	}

	public Action dirOfLow(Robot r, Robot rLow) {
		List<Action> action = r.getAvailableAtacks();
		for (Action act : action) {
			Coordinates c = act.getDirection();
			if (rLow == getBoard().getRobot(c)) {
				return act;
			}
		}
		return null;
	}
	
	private Action goToNextStep(Robot rob, Path path) {
		if (path == null) {
			return null;
		}
		Coordinates to;
		try {
			to = path.getCoordsRelativ(1);
		} catch (Exception e) {
			return null;
		}

		if (rob.getAvailableMove() == null) {
			return null;
		}
		for (Action mov : rob.getAvailableMove()) {
			if (mov.getDirection().equals(to)) {
				return mov;
			}
		}
		return null;
	}

	@Override
	public Action makeTurn() {
		List<Robot> robots = super.getLsRobot();
		if (robots == null) {
			return null;
		}

		List<Robot> ennemyRobots = new ArrayList<Robot>();
		Robot rLow;
		for (Robot r : robots) {
			if (r instanceof Scavenger && r.canAttack()) {
				ennemyRobots.add(detectRobotJustDown(r));
				ennemyRobots.add(detectRobotJustUp(r));
				ennemyRobots.add(detectRobotJustLeft(r));
				ennemyRobots.add(detectRobotJustRight(r));
				if (!ennemyRobots.isEmpty()) {
					rLow = lowRobot(r, ennemyRobots);
					if (!(rLow == null)) {
						return dirOfLow(r, rLow);
					} else {
						List<Action> lsAct = r.getAvailableMove();
						return lsAct.get(ran.nextInt(lsAct.size()));
					}
				}

			} else if (r instanceof Tank && r.canAttack()) {
				ennemyRobots.add(detectRobotJustDown(r));
				ennemyRobots.add(detectRobotJustUp(r));
				ennemyRobots.add(detectRobotJustLeft(r));
				ennemyRobots.add(detectRobotJustRight(r));
				if (!ennemyRobots.isEmpty()) {
					rLow = lowRobot(r, ennemyRobots);
					if (!(rLow == null)) {
						return dirOfLow(r, rLow);
					} else {
						List<Action> lsAct = r.getAvailableMove();
						return lsAct.get(ran.nextInt(lsAct.size()));
					}
				}
			} else {
				if (r instanceof Scavenger) {
					if (ran.nextBoolean() && r.canMove()) {
						List<Action> lsAction = r.getAvailableMove();
						return lsAction.get(ran.nextInt(lsAction.size()));
					} else if (r.canAttack()) {
						List<Action> lsAction = r.getAvailableAtacks();
						return lsAction.get(ran.nextInt(lsAction.size()));
					}
				} else {
					int t = r.getTeam();
					if (t == 1) {
						Coordinates b1 = new Coordinates(0, 0);
						return goToNextStep(r, getPathTo(r, b1));
					} else if (t == 2) {
						Coordinates b2 = new Coordinates(
								getBoard().getWidth() - 1, getBoard()
										.getHeight() - 1);
						
						Action act = goToNextStep(r, getPathTo(r, b2));
						if (act != null) {
							return act;
						}
					}
				}
			}
		}

		return null;
	}
}
