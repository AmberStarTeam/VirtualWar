package org.amberstar.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.virtualwar.board.Board;
import org.virtualwar.robot.Robot;
import org.virtualwar.robot.Scavenger;
import org.virtualwar.util.Coordinates;

import com.sun.swing.internal.plaf.basic.resources.basic;

// TODO: Auto-generated Javadoc
/**
 * The Class BoardTest.
 *
 * @author amberStar
 */
public class BoardTest {
	
	Board b = new Board(10, 5);
	Coordinates c = new Coordinates(0,0);
	Coordinates c2 = new Coordinates(2,2);
	Robot r = new Scavenger(0,c2,b);
	
    /**
     * Sets the up before class.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * Test board.
     */
    @Test
    public void testBoard() {
        //fail("Not yet implemented");
    }

    /**
     * Test generate.
     */
    @Test
    public void testGenerate() {
        //fail("Not yet implemented");
    }

    /**
     * Test get height.
     */
    @Test
    public void testGetHeight() {
    	assertEquals(b.getHeight(),10);
        //fail("Not yet implemented");
    }

    /**
     * Test get width.
     */
    @Test
    public void testGetWidth() {
    	assertEquals(b.getWidth(),5);
        //fail("Not yet implemented");
    }

    /**
     * Test get cell.
     */
    @Test
    public void testGetCell() {
    	System.out.println(b.getCell(c));
    	assertEquals(b.getCell(c),c);
        //fail("Not yet implemented");
    }

    /**
     * Test get robot.
     */
    @Test
    public void testGetRobot() {
    	assertEquals(b.getRobot(c),null);
    	assertEquals(b.getRobot(c2), "Piegeur[energy=50, team=1, coordinates=[Hauteur=0, Largeur=0], regenBase=2, maxEng=50, moves=[[Hauteur=-1, Largeur=0], [Hauteur=1, Largeur=0], [Hauteur=0, Largeur=1], [Hauteur=0, Largeur=-1], [Hauteur=-1, Largeur=1], [Hauteur=-1, Largeur=-1], [Hauteur=1, Largeur=1], [Hauteur=1, Largeur=-1]]]");
        //fail("Not yet implemented");
    }

    /**
     * Test set robot.
     */
    @Test
    public void testSetRobot() {
        //fail("Not yet implemented");
    }

    /**
     * Test set mine.
     */
    @Test
    public void testSetMine() {
        //fail("Not yet implemented");
    }

    /**
     * Test is base.
     */
    @Test
    public void testIsBase() {
    	assertEquals(b.isBase(c), 1);
        //fail("Not yet implemented");
    }

    /**
     * Test is obstacle.
     */
    @Test
    public void testIsObstacle() {
        //fail("Not yet implemented");
    }

    /**
     * Test is mine.
     */
    @Test
    public void testIsMine() {
        //fail("Not yet implemented");
    }

    /**
     * Test is valid.
     */
    @Test
    public void testIsValid() {
    	assertEquals(b.isValid(c),true);
        //fail("Not yet implemented");
    }

    /**
     * Test out grind.
     */
    @Test
    public void testOutGrind() {
    	b.outGrind(1);
    	try{
    		Robot r = new Scavenger(1,c2,b);
    	} catch (Exception e){
    	}
    	//fail("Not yet implemented");
    }

    /**
     * Test to string.
     */
    @Test
    public void testToString() {
        //fail("Not yet implemented");
    }

}
