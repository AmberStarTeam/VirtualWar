package org.amberstar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.amberstar.virtualwar.Board;
import org.amberstar.virtualwar.Coordinates;
import org.junit.Test;

/**
 * . TODO
 * 
 * @author beaussan
 *
 */
public class CoordinatesTest {

    /**
     * test constructor
     */
    @Test
    public void testCoordinates() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
        assertTrue(tmp.getWidth() == 2);
    }

    /**
     * test getter width
     */
    @Test
    public void testGetWidth() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getWidth() == 2);
    }

    /**
     * test getter height
     */
    @Test
    public void testGetHeight() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
    }

    /**
     * test toString
     */
    @Test
    public void testToString() {
        assertEquals("Coordinates [height=20, width=2]",
                new Coordinates(2, 20).toString());
    }

    /**
     * test add
     */
    @Test
    public void testAdd() {
        assertEquals(new Coordinates(2, 10),
                new Coordinates(0, 10).add(new Coordinates(2, 0)));
        assertNotEquals(new Coordinates(2, 10),
                new Coordinates(0, 10).add(new Coordinates(2, 1)));
        assertEquals(new Coordinates(2, 4),
                new Coordinates(1, 3).add(new Coordinates(1, 1)));
    }

    /**
     * test minus function
     */
    @Test
    public void testMinus() {
        assertEquals(new Coordinates(2, 2),
                new Coordinates(3, 4).minus(new Coordinates(1, 2)));
        assertEquals(new Coordinates(-4, 2),
                new Coordinates(3, 4).minus(new Coordinates(7, 2)));
    }

    /**
     * test multiplying
     */
    @Test
    public void testTimes() {
        assertEquals(new Coordinates(8, 4), new Coordinates(4, 2).times(2));
        assertEquals(new Coordinates(-8, -4), new Coordinates(4, 2).times(-2));
    }

    /**
     * test dividing coordinates
     */
    @Test
    public void testDivide() {
        assertEquals(new Coordinates(10, 5), new Coordinates(20, 10).divide(2));
    }

    /**
     * test equals
     */
    @Test
    public void testEqualsObject() {
        assertTrue("Equals",
                new Coordinates(0, 10).equals(new Coordinates(0, 10)));
        assertFalse("Equals",
                new Coordinates(0, 10).equals(new Coordinates(1, 10)));
        assertFalse("Equals", new Coordinates(0, 10).equals(null));
        assertFalse("Equals", new Coordinates(0, 10).equals(new Board(1, 1)));

    }

}
