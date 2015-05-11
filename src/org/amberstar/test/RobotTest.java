/*
 * This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.amberstar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.virtualwar.action.Attack;
import org.virtualwar.action.Move;
import org.virtualwar.board.Base;
import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.robot.Shooter;
import org.virtualwar.robot.Tank;
import org.virtualwar.util.Coordinates;
import org.virtualwar.util.sound.ThreadSoundRun;

public class RobotTest {
	Board b = Board.newBoard(4, 10);
	Coordinates c = new Coordinates(0, 0);
	Robot r = new Scavenger(1, c, b);

	@Test
	public final void testCanAttack() {
		Board b2 = Board.newBoard(4, 10);
		Coordinates c2 = new Coordinates(0, 0);
		Robot r2 = new Scavenger(1, c2, b2);
		Coordinates c3 = new Coordinates(1, 1);
		Shooter s2 = new Shooter(2, c3, b2);
		assertEquals(s2.canAttack(), false);
		Move ms = new Move(r2, Constant.RIGHT);
		ms.act();
		assertEquals(s2.canAttack(), true);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCanMove() {
		Board b2 = Board.newBoard(4, 10);
		Coordinates c2 = new Coordinates(0, 2);
		Robot r2 = new Scavenger(1, c2, b2);
		Coordinates o1 = new Coordinates(0, 1);
		Coordinates o2 = new Coordinates(1, 2);
		Coordinates o3 = new Coordinates(0, 3);
		Coordinates o4 = new Coordinates(1, 1);
		Coordinates o5 = new Coordinates(1, 3);
		Robot r3 = new Scavenger(1, o1, b2);
		Robot r4 = new Scavenger(1, o2, b2);
		Robot r5 = new Scavenger(1, o3, b2);
		Robot r6 = new Scavenger(1, o4, b2);
		Robot r7 = new Scavenger(1, o5, b2);
		assertEquals(r2.canMove(), false);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testClone() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEqualsObject() {
		// fail("Not yet implemented"); // TODO

	}

	@Test
	public final void testEqualsObject1() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testFinalize() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAttackSound() {
		ThreadSoundRun tmp;

		for (String str : ("boom.wav\ndeathOfRobots.wav"
				+ "\nshotShooter.wav\n" + "shotTank.wav").split("\n")) {
			System.out.println(str);
			tmp = new ThreadSoundRun("sounds/" + str);
			try {
				tmp.start();
			} catch (Exception e) {
				fail("Not yet implemented"); // TODO
			}
			while (tmp.isAlive()) {
				System.out.println("Running");
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Test
	public final void testGetAvailableAtacks() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAvailableMove() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBoard() {
		Board b1 = Board.newBoard(4, 10);
		assertEquals(r.getBoard(), b1);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCell() {
		assertEquals(b.getCell(c), new Base(c, 1));
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetClass() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCoordinates() {
		Coordinates c1 = new Coordinates(0, 0);
		assertEquals(r.getCoordinates(), c1);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCostAction() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getCostAction(), Constant.TANK_ENERGY_ACTION);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getCostAction(), Constant.SHOOTER_ENERGY_ACTION);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getCostAction(),
						Constant.SCAVENGER_ENERGY_ACTION);
			}
		}

		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetCostMoving() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getCostMoving(), Constant.TANK_ENERGY_MOVE);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getCostMoving(), Constant.SHOOTER_ENERGY_MOVE);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getCostMoving(), Constant.SCAVENGER_ENERGY_MOVE);
			}
		}

		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetDamageTaken() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getCostMoving(), Constant.TANK_ENERGY_MOVE);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getCostMoving(), Constant.SHOOTER_ENERGY_MOVE);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getCostMoving(), Constant.SCAVENGER_ENERGY_MOVE);
			}
		}
		// fail("Not yet implemented"); // TODO

	}

	@Test
	public final void testGetEnergy() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getEnergy(), Constant.TANK_ENERGY_INITIAL);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getEnergy(), Constant.SHOOTER_ENERGY_INITIAL);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getEnergy(), Constant.SCAVENGER_ENERGY_INITIAL);
			}
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMaxEng() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getEnergy(), Constant.TANK_ENERGY_INITIAL);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getEnergy(), Constant.SHOOTER_ENERGY_INITIAL);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getEnergy(), Constant.SCAVENGER_ENERGY_INITIAL);
			}
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMoveSound() {
		ThreadSoundRun tmp;

		for (String str : ("moveTank.wav\nrunning.wav").split("\n")) {
			System.out.println(str);
			tmp = new ThreadSoundRun("sounds/" + str);
			try {
				tmp.start();
			} catch (Exception e) {
				fail("Not yet implemented"); // TODO
			}
			while (tmp.isAlive()) {
				System.out.println("Running");
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMoving() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRange() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getRange(), Constant.TANK_RANGE);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getRange(), Constant.SHOOTER_RANGE);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getRange(), Constant.SCAVENGER_RANGE);
			}
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRegenBase() {
		Coordinates cords = new Coordinates(0, 0);
		Board boa = Board.newBoard(4, 4);
		for (Robot r1 : new Robot[] { new Tank(0, new Coordinates(0, 0), boa),
				new Scavenger(0, cords, boa), new Shooter(0, cords, boa) }) {
			System.out.println(r);
			if (r1 instanceof Tank) {
				assertEquals(r1.getRegenBase(), Constant.TANK_ENERGY_REGEN);
			} else if (r1 instanceof Shooter) {
				assertEquals(r1.getRegenBase(), Constant.SHOOTER_ENERGY_REGEN);
			} else if (r1 instanceof Scavenger) {
				assertEquals(r1.getRegenBase(), Constant.SCAVENGER_ENERGY_REGEN);
			}
		}
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetTeam() {
		assertEquals(r.getTeam(), 1);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetType() {
		assertEquals(r.getType(), "Piegeur");
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHasBeenMined() {
		Coordinates cords = new Coordinates(0, 1);
		r.getBoard().setMine(Constant.RIGHT, r.getTeam());
		Coordinates cords1 = new Coordinates(1, 1);
		Shooter s = new Shooter(2, cords1, b);
		Move ms = new Move(s, Constant.UP);
		ms.act();
		Move ms1 = new Move(s, Constant.DOWN);
		ms1.act();
		assertEquals(b.isMine(cords), 0);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHasBeenShoot() {
		Coordinates cords1 = new Coordinates(1, 1);
		Shooter s = new Shooter(2, cords1, b);
		Move ms = new Move(r, Constant.RIGHT);
		ms.act();
		Attack ms1 = new Attack(s, Constant.UP);
		ms1.act();
		assertEquals(
				r.getEnergy(),
				Constant.SCAVENGER_ENERGY_INITIAL
						- (Constant.SCAVENGER_ENERGY_MOVE + Constant.SHOOTER_DAMAGE_SUBMIT));
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testHashCode() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsInBase() {
		Coordinates cords1 = new Coordinates(1, 1);
		Shooter s = new Shooter(2, cords1, b);
		assertEquals(b.isBase(r.getCoordinates()), 1);
		assertEquals(b.isBase(s.getCoordinates()), 0);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsValid() {
		Coordinates cords1 = new Coordinates(-1, 1);
		assertEquals(b.isValid(cords1), false);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotify() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testNotifyAll() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testObject() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRemoveEnergy() {
		r.removeEnergy(10);
		assertEquals(r.getEnergy(), Constant.SCAVENGER_ENERGY_INITIAL - 10);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testRobot() {
		// fail("Not yet implemented"); // TODO
		Board b1 = Board.newBoard(4, 10);
		Coordinates c1 = new Coordinates(0, 0);
		Robot r1 = new Scavenger(1, c1, b1);
	}

	@Test
	public final void testRunBaseAction() {
		r.getBoard().setMine(Constant.RIGHT, r.getTeam());
		r.setEnergy(10);
		r.runBaseAction();
		assertEquals(r.getEnergy(), 12);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetCoordinates() {
		Coordinates cords1 = new Coordinates(1, 1);
		r.setCoordinates(cords1);
		assertEquals(r.getCoordinates(), cords1);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetEnergy() {
		r.setEnergy(1);
		assertEquals(r.getEnergy(), 1);
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString() {
		System.out.println("Test toString : " + r.toString());
		assertEquals(
				"Test toString : " + r.toString(),
				"Test toString : Piegeur[energy=50, team=1, coordinates=[Hauteur=0, Largeur=0], regenBase=2, maxEng=50, moves=[[Hauteur=-1, Largeur=0], [Hauteur=1, Largeur=0], [Hauteur=0, Largeur=1], [Hauteur=0, Largeur=-1], [Hauteur=-1, Largeur=1], [Hauteur=-1, Largeur=-1], [Hauteur=1, Largeur=1], [Hauteur=1, Largeur=-1]]]");
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testToString1() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWait() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLong() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testWaitLongInt() {
		// fail("Not yet implemented"); // TODO
	}

}
