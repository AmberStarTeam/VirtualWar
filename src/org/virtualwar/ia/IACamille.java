package org.virtualwar.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.AStarPathFinder;
import org.virtualwar.util.pathfinding.Path;

public class IACamille extends Intelligence {

	/** The random generator. */
	private Random ran = new Random();

	/** The path find diagonal */
	AStarPathFinder pathDiag;

	/** The path find straight */
	AStarPathFinder pathStraight;

	/**
	 * Instantiates a new IACamille.
	 */
	public IACamille() {
	}

	/**
	 * Instantiates a new IACamille
	 * 
	 * @param team
	 * @param board
	 */
	public IACamille(int team, Board board) {
		super(team, board);
		if (board != null) {
			pathDiag = new AStarPathFinder(super.getBoard(), 10000, true);
			pathStraight = new AStarPathFinder(super.getBoard(), 10000, false,
					2);
		}
	}

	/**
	 * Instantiates a new IACamille
	 * 
	 * @param robots
	 * @param team
	 * @param board
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.ia.Intelligence#getInitialRobots(int)
	 */
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

	/**
	 * Get the path home
	 * 
	 * @param rob
	 * @return the path home
	 */
	@SuppressWarnings("unused")
	private Path getPathHome(Robot rob) {
		return getPathTo(rob, getBoard().getCoordsBase(getTeam()));
	}

	/**
	 * Get the path to
	 * 
	 * @param rob
	 * @param cords
	 * @return the path to
	 */
	private Path getPathTo(Robot rob, Coordinates cords) {
		if (rob instanceof Tank) {
			//System.err.println("8-1-1");
			return pathStraight.findPath(rob, rob.getCoordinates(), cords);
		} else {
			//System.err.println("8-1-2");
			return pathDiag.findPath(rob, rob.getCoordinates(), cords);
		}
	}

	/**
	 * Detect a robot just down
	 * 
	 * @param r
	 * @return robot
	 */
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

	/**
	 * Detect a robot just up
	 * 
	 * @param r
	 * @return robot
	 */
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

	/**
	 * Detect a robot just right
	 * 
	 * @param r
	 * @return robot
	 */
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

	/**
	 * Detect a robot just left
	 * 
	 * @param r
	 * @return robot
	 */
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

	/**
	 * Detect the robot that has the lowest energy
	 * 
	 * @param r1
	 * @param ennemies
	 * @return robot
	 */
	public Robot lowRobot(Robot r1, List<Robot> ennemies) {
		Robot result = null;
		Robot r = r1;
		if (!(ennemies.isEmpty())) {
			for (Robot robot : ennemies) {
				if (!(robot == null)) {
					if (robot.getEnergy() <= r.getEnergy()) {
						r = robot;
					}
				}
			}
			if (r == r1) {
				int i = ran.nextInt(ennemies.size());
				r = ennemies.get(i);
			}
		}
		if (!(r == r1)) {
			result = r;
		}
		return result;
	}

	/**
	 * attack towards the low robot
	 * 
	 * @param r
	 * @param rLow
	 * @return action
	 */
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

	/**
	 * Go to next step
	 * 
	 * @param rob
	 * @param path
	 * @return the action
	 */
	private Action goToNextStep(Robot rob, Path path) {
		if (path == null) {
			// System.err.println("8-1");
			return null;
		}
		Coordinates to;
		try {
			to = path.getCoordsRelativ(1);
		} catch (Exception e) {
			// System.err.println("8-2");
			return null;
		}

		if (rob.getAvailableMove() == null) {
			// System.err.println("8-3");
			return null;
		}
		for (Action mov : rob.getAvailableMove()) {
			if (mov.getDirection().equals(to)) {
				// System.err.println("8-4");
				return mov;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.virtualwar.ia.Intelligence#makeTurn()
	 */
	@Override
	public Action makeTurn() {
		List<Robot> robots = super.getLsRobot();
		//System.err.println(robots);
		if (robots == null) {
			//System.err.println("null1");
			return null;
		}

		List<Robot> ennemyRobots = new ArrayList<Robot>();
		Robot rLow;
		Robot r = robots.get(ran.nextInt(robots.size()));
		if (r instanceof Shooter && r.canAttack()) {
			ennemyRobots.add(detectRobotJustDown(r));
			ennemyRobots.add(detectRobotJustUp(r));
			ennemyRobots.add(detectRobotJustLeft(r));
			ennemyRobots.add(detectRobotJustRight(r));
			if (!ennemyRobots.isEmpty()) {
				rLow = lowRobot(r, ennemyRobots);
				if (!(rLow == null)) {
					// System.err.println("2 " + dirOfLow(r, rLow));
					return dirOfLow(r, rLow);
				} else {
					List<Action> lsAct = r.getAvailableMove();
					// System.err.println("3 "
					// + lsAct.get(ran.nextInt(lsAct.size())));
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
					// System.err.println("3 " + dirOfLow(r, rLow));
					return dirOfLow(r, rLow);
				} else {
					List<Action> lsAct = r.getAvailableMove();
					// System.err.println("4 "
					// + lsAct.get(ran.nextInt(lsAct.size())));
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
			}
		} else {
			if (r instanceof Scavenger) {
				if (ran.nextBoolean() && r.canMove()) {
					List<Action> lsAction = r.getAvailableMove();
					// System.err.println("5 "
					// + lsAction.get(ran.nextInt(lsAction.size())));
					return lsAction.get(ran.nextInt(lsAction.size()));

				} else if (r.canAttack()) {
					List<Action> lsAction = r.getAvailableAtacks();
					// System.err.println("6 "
					// + lsAction.get(ran.nextInt(lsAction.size())));
					return lsAction.get(ran.nextInt(lsAction.size()));

				}
			} else {
				int t = r.getTeam();

				Coordinates b1A = new Coordinates(1, 0);
				Coordinates b1B = new Coordinates(1, 0);
				Coordinates b1C = new Coordinates(1, 0);
				List<Coordinates> lsB1 = new ArrayList<>();
				lsB1.add(b1A);
				lsB1.add(b1B);
				lsB1.add(b1C);

				Coordinates b2A = new Coordinates(getBoard().getWidth() - 1,
						getBoard().getHeight() - 1);
				Coordinates b2B = new Coordinates(getBoard().getWidth() - 1,
						getBoard().getHeight());
				Coordinates b2C = new Coordinates(getBoard().getWidth(),
						getBoard().getHeight() - 1);
				List<Coordinates> lsB2 = new ArrayList<>();
				lsB2.add(b2A);
				lsB2.add(b2B);
				lsB2.add(b2C);

				Action act = null;
				if ((r.isInBase()) && t == 2) {
					Coordinates b1 = lsB1.get(ran.nextInt(lsB1.size()));
					// System.err.println("b1 : " + b1);
					act = goToNextStep(r, getPathTo(r, b1));
				} else if (r.isInBase() && t == 1) {
					Coordinates b2 = lsB2.get(ran.nextInt(lsB2.size()));
					// System.err.println("b2 : " + b2);
					act = goToNextStep(r, getPathTo(r, b2));
				} else {
					List<Coordinates> lsB = new ArrayList<>();
					lsB.add(lsB1.get(ran.nextInt(lsB1.size())));
					lsB.add(lsB2.get(ran.nextInt(lsB2.size())));

					Coordinates b = lsB.get(ran.nextInt(lsB.size()));
					act = goToNextStep(r, getPathTo(r, b));

					// System.err.println("8 " + act);
				}
				if (act != null) {
					return act;
				}
			}
		}
		return null;
	}
}
