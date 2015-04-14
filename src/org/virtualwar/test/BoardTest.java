/**
 * 
 */
package org.virtualwar.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.virtualwar.board.Board;
import org.virtualwar.config.Constant;
import org.virtualwar.util.Coordinates;

/**
 * @author Nicolas Beaussart
 *
 */
public class BoardTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#newBoard(int, int)}.
	 */
	@Test
	public void testNewBoard() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#generate(int, boolean)}.
	 */
	@Test
	public void testGenerate() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#getHeight()}.
	 */
	@Test
	public void testGetHeight() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#getWidth()}.
	 */
	@Test
	public void testGetWidth() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#getCell(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testGetCell() {
		Board b = Board.newBoard(4, 10);
		for (int i = 0; i < 10; i++) {
			System.out.print(b.setMine(new Coordinates(i, 4), Constant.ID_TEAM_A));
		}
		System.out.println("\n" + b.outGrindPlusLegend(Constant.ID_TEAM_A));
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#getRobot(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testGetRobot() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#setRobot(org.virtualwar.robot.Robot, org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testSetRobot() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#setMine(org.virtualwar.util.Coordinates, int)}.
	 */
	@Test
	public void testSetMine() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#isBase(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testIsBase() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#isObstacle(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testIsObstacle() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#isMine(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testIsMine() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#isValid(org.virtualwar.util.Coordinates)}.
	 */
	@Test
	public void testIsValid() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#outGrind(int)}.
	 */
	@Test
	public void testOutGrind() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#outGrindPlusLegend(int)}.
	 */
	@Test
	public void testOutGrindPlusLegend() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#toString()}.
	 */
	@Test
	public void testToString() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.virtualwar.board.Board#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		//fail("Not yet implemented");
	}

}
