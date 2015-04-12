package org.amberstar.virtualwar;

import org.amberstar.virtualwar.sound.ThreadSoundRun;

/**
 * The Attack Class.
 *
 * @author amberStar
 */
public class Attack extends Action {

	/**
	 * make a robot attack in a direction
	 * 
	 * @param robot
	 *            the robot from
	 * @param dir
	 *            the direction in 0 1 coordinates
	 */
	public Attack(Robot robot, Coordinates dir) {
		super(robot, dir);
	}

	/**
	 * 
	 * @return the Robot of objective, if none then null
	 */
	private Robot getObjectif() {
		for (int i = 0; i < super.getRobotSource().getRange(); i++) {
			Coordinates tmp = super.getRobotSource().getCoordinates()
					.add(super.getDirection().times(i + 1));
			Cell cellOf = super.getRobotSource().getBoard().getCell(tmp);
			if (cellOf.isObstacle() || cellOf.isBase() != 0) {
				return null;
			} else if (cellOf.getRobotIn() != null) {
				if (cellOf.getRobotIn().getTeam() == super.getRobotSource()
						.getTeam()) {
					return null;
				}
				return cellOf.getRobotIn();
			}
		}
		return null;
	}

	@Override
	void act() {
		// test s'il y a assez de point pour tirer
		if (super.getRobotSource().getEnergy()
				- super.getRobotSource().getCostAction() < 0) {
			return;
		}// test si l'on peut tirer hors du plateau
		if (super.getDirection() == Constant.UP
				&& super.getRobotSource().getCoordinates().getHeight() == 0) {
			return;
		} else if (super.getDirection() == Constant.LEFT
				&& super.getRobotSource().getCoordinates().getWidth() == 0) {
			return;
		} else if (super.getDirection() == Constant.RIGHT
				&& super.getRobotSource().getCoordinates().getWidth() == super
						.getRobotSource().getBoard().getWidth()) {
			return;
		} else if (super.getDirection() == Constant.DOWN
				&& super.getRobotSource().getCoordinates().getHeight() == super
						.getRobotSource().getBoard().getHeight()) {
			return;
		}

		// test si le robot qui attaque n'est pas un mineur
		if (!(super.getRobotSource() instanceof Scavenger)) {
			Robot toAttack = getObjectif();
			if (toAttack == null
					|| (super.getRobotSource() instanceof Tank && toAttack
							.getCoordinates().minus(getDirection())
							.equals(super.getRobotSource().getCoordinates()))) {
				System.out.println("Rien à attaquer");
				return;
			}
			super.getRobotSource().removeEnergy(
					super.getRobotSource().getCostAction());
			toAttack.hasBeenShoot();
			new ThreadSoundRun(super.getRobotSource().getAttackSound()).start();

		} else {
			Scavenger loc = (Scavenger) super.getRobotSource();
			if (loc.getStock() <= 0) {
				return;
			}
			Coordinates cords = loc.getCoordinates().add(super.getDirection());
			if (loc.getBoard().isMine(cords) != 0) {
				System.out.println("Il y a déjà une mine !");
				return;
			}
			if (loc.getBoard().isObstacle(cords) == true) {
				System.out.println("Il y a déjà un obstacle !");
				return;
			}
			if (loc.getBoard().isBase(cords) != 0) {
				System.out.println("Tu ne peut pas miner une base !");
				return;
			}
			if (loc.getBoard().getRobot(cords) != null) {
				System.out.println("Tu ne peut pas miner un robot !");
				return;
			}
			loc.getBoard().setMine(cords, loc.getTeam());
			loc.removeEnergy(loc.getCostAction());
			loc.dropMine();

		}
	}

	@Override
	boolean canDoIt() {
		// TODO Auto-generated method stub
		return false;
	}
}
