package org.virtualwar.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.virtualwar.action.Action;
import org.virtualwar.action.Attack;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.AStarPathFinder;
import org.virtualwar.util.pathfinding.Path;

public class BeaussartIntelligence extends Inteligence {

	/** The random generator. */
	private Random ran = new Random();

	AStarPathFinder pathFindDiag;

	AStarPathFinder pathFindStrait;

	public BeaussartIntelligence() {
	}

	public BeaussartIntelligence(int team, Board board) {
		super(team, board);
		pathFindDiag = new AStarPathFinder(super.getBoard(), 10000, true);
		pathFindStrait = new AStarPathFinder(super.getBoard(), 10000, false, 2);
	}

	public BeaussartIntelligence(List<Robot> robots, int team, Board board) {
		super(robots, team, board);
		pathFindDiag = new AStarPathFinder(super.getBoard(), 10000, true);
		pathFindStrait = new AStarPathFinder(super.getBoard(), 10000, false, 2);
	}

	private boolean canAttackSafely(Robot rob) {
		if (rob instanceof Scavenger) {
			return true;
		} else {
			if (!rob.getAvailableAtacks().isEmpty()) {
				Attack atck = (Attack) rob.getAvailableAtacks().get(0);
				if (rob.getEnergy() - atck.getObjectif().getDamageTaken() >= 5) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private Action dodge(Attack att) {
		Robot robSource = att.getRobotSource();
		if (att.getDirection().getHeight() != 0) { // attaque vers Height
			// new Coordinates(width, height)
			List<Action> lsCanMove = robSource.getAvailableMove();
			if (lsCanMove == null) {
				return null;
			}
			for (int i = lsCanMove.size() - 1; i >= 0; i--) {
				if (lsCanMove.get(i).getDirection().getHeight() != 0) {
					lsCanMove.remove(i);
				}
			}
			if (lsCanMove.isEmpty()) {
				lsCanMove = robSource.getAvailableMove();
				if (lsCanMove == null) {
					return null;
				}
				for (int i = lsCanMove.size() - 1; i >= 0; i--) {
					if (lsCanMove.get(i).getDirection().getHeight() != att
							.getDirection().getHeight()) {
						lsCanMove.remove(i);
					}
				}
			}
			if (lsCanMove.isEmpty()) {
				return null;
			}
			return lsCanMove.get(ran.nextInt(lsCanMove.size()));
		} else { // attaque vers width
			// new Coordinates(width, height)
			List<Action> lsCanMove = robSource.getAvailableMove();
			if (lsCanMove == null) {
				return null;
			}
			for (int i = lsCanMove.size() - 1; i >= 0; i--) {
				if (lsCanMove.get(i).getDirection().getWidth() != 0) {
					lsCanMove.remove(i);
				}
			}
			if (lsCanMove.isEmpty()) {
				lsCanMove = robSource.getAvailableMove();
				if (lsCanMove == null) {
					return null;
				}
				for (int i = lsCanMove.size() - 1; i >= 0; i--) {
					if (lsCanMove.get(i).getDirection().getWidth() != att
							.getDirection().getWidth()) {
						lsCanMove.remove(i);
					}
				}
			}
			if (lsCanMove.isEmpty()) {
				return null;
			}
			return lsCanMove.get(ran.nextInt(lsCanMove.size()));
		}

	}

	@Override
	public List<Robot> getInitialRobots(int numberOfBots) {
		List<Robot> retVal = new ArrayList<Robot>();
		Coordinates cords = getBoard().getCoordsBase(getTeam());
		for (int i = 0; i < numberOfBots; i++) {
			int outRand = ran.nextInt(100);
			if (outRand < 10) {
				retVal.add(new Scavenger(getTeam(), cords, getBoard()));
			} else if (outRand < 40) {
				retVal.add(new Shooter(getTeam(), cords, getBoard()));
			} else {
				retVal.add(new Tank(getTeam(), cords, getBoard()));
			}
		}
		setLsRobot(retVal);
		return retVal;
	}

	private Path getPathHome(Robot rob) {
		return getPathTo(rob, getBoard().getCoordsBase(getTeam()));
	}

	private Path getPathTo(Robot rob, Coordinates cords) {
		if (rob instanceof Tank) {
			return pathFindStrait.findPath(rob, rob.getCoordinates(), cords);
		} else {
			return pathFindDiag.findPath(rob, rob.getCoordinates(), cords);
		}
	}

	private Action goToNextStep(Robot rob, Path path) {

		Coordinates to = path.getCoordsRelativ(1);
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

	private List<Robot> haveToGoHome() {
		List<Robot> lsRobot = new ArrayList<Robot>(super.getLsRobot());
		List<Robot> retVal = new ArrayList<Robot>();
		for (Robot rob : lsRobot) {
			Path pathToHome = getPathHome(rob);
			if (pathToHome == null) {
				continue;
			}
			if (pathToHome.getLength() * rob.getCostMoving() <= 10) {
				retVal.add(rob);
			}
		}
		return retVal;
	}

	@Override
	public Action makeTurn() {
		List<Robot> lsRobot = new ArrayList<Robot>(super.getLsRobot());
		if (lsRobot.isEmpty()) {
			return null;
		}

		List<Robot> haveToBack = haveToGoHome();

		if (!haveToBack.isEmpty()) {
			Robot rob = haveToBack.get(ran.nextInt(haveToBack.size()));

			Action act = goToNextStep(rob, getPathHome(rob));
			if (act != null) {
				return act;
			}
		}

		List<Action> attacks = new ArrayList<Action>();
		for (Robot rob : lsRobot) {
			if (!(rob instanceof Scavenger) && rob.canAttack()) {
				attacks.addAll(rob.getAvailableAtacks());

				Action act = tankAttack(attacks);
				if (act != null) {
					return act;
				}

				List<Attack> lsAttackShooter = new ArrayList<Attack>();

				for (Action att : attacks) {
					if (att.getRobotSource() instanceof Shooter) {
						lsAttackShooter.add((Attack) att);
					}
				}
				act = shooterAttack(lsAttackShooter);
				if (act != null) {
					return act;
				}
			}
		}

		List<Robot> robLost = willNotMakeHome();
		if (!robLost.isEmpty()) {
			Collections.shuffle(robLost, ran);
			Coordinates goToBase;
			Coordinates goTo;
			if (getTeam() == 1) {
				goToBase = getBoard().getCoordsBase(2);
			} else {
				goToBase = getBoard().getCoordsBase(1);
			}
			for (Robot rob : robLost) {
				goTo = new Coordinates(goToBase);
				while (rob.isValid(goTo) != null) {
					goTo = new Coordinates(goToBase);
					goTo.setHeight(goTo.getHeight() + ran.nextInt(4) - 2);
					goTo.setWidth(goTo.getWidth() + ran.nextInt(4) - 2);
				}
				Action act = goToNextStep(rob, getPathTo(rob, goTo));
				if (act != null) {
					return act;
				}
			}
		}
		lsRobot = new ArrayList<Robot>(super.getLsRobot());
		Collections.shuffle(lsRobot, ran);

		for (Robot rob : robLost) {
			if (rob.canAttack() && !(rob instanceof Scavenger)) {
				List<Action> lsAct = rob.getAvailableAtacks();
				return lsAct.get(ran.nextInt(lsAct.size()));
			}
		}

		lsRobot = new ArrayList<Robot>(super.getLsRobot());
		Collections.shuffle(lsRobot, ran);

		for (Robot rob : lsRobot) {
			if (rob.canAttack()) {
				if (rob instanceof Scavenger && ran.nextBoolean()
						&& ran.nextBoolean()) {

					List<Action> lsAct = rob.getAvailableAtacks();
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
			}
			if (rob.canMove()) {
				List<Action> lsAct = rob.getAvailableMove();
				return lsAct.get(ran.nextInt(lsAct.size()));
			}
		}
		for (Robot rob : lsRobot) {
			if (rob.canAttack()) {
				if (rob instanceof Scavenger) {
					List<Action> lsAct = rob.getAvailableAtacks();
					return lsAct.get(ran.nextInt(lsAct.size()));
				}
			}
		}
		System.err.println("Oups...");
		System.err.println(lsRobot);
		return null;

	}

	@Override
	public void setBoard(Board board) {
		super.setBoard(board);
		pathFindDiag = new AStarPathFinder(board, 10000, true);
		pathFindStrait = new AStarPathFinder(board, 10000, false, 2);
		ran = new Random();
	}

	private Action shooterAttack(List<Attack> lsAction) {
		if (!lsAction.isEmpty()) {
			Collections.shuffle(lsAction, ran);
			for (Attack att : lsAction) {
				if (att.getRobotSource() instanceof Tank) {
					if (canAttackSafely(att.getRobotSource())) {
						return att;
					} else {
						Action act = dodge(att);
						if (act != null) {
							return act;
						}
					}
				}
			}
		}
		return null;
	}

	private Action tankAttack(List<Action> lsAction) {
		for (Action att : lsAction) {
			if (att.getRobotSource() instanceof Tank) {
				if (canAttackSafely(att.getRobotSource())) {
					return att;
				} else {
					Action act = dodge((Attack) att);
					if (act != null) {
						return act;
					}
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return super.toString() + "Beausssart";
	}

	private List<Robot> willNotMakeHome() {
		List<Robot> lsRobot = new ArrayList<Robot>(super.getLsRobot());
		List<Robot> retVal = new ArrayList<Robot>();
		for (Robot rob : lsRobot) {
			Path pathToHome = getPathHome(rob);
			if (pathToHome == null) {
				continue;
			}
			if (pathToHome.getLength() * rob.getCostMoving() <= 0) {
				retVal.add(rob);
			}
		}
		return retVal;
	}

}
