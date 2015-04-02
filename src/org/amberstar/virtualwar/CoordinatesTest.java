package org.amberstar.virtualwar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class CoordinatesTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testCoordinates() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
        assertTrue(tmp.getWidth() == 2);
    }

    @Test
    public void testGetWidth() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getWidth() == 2);
    }

    @Test
    public void testGetHeight() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
    }

    @Test
    public void testToString() {
        assertEquals("Coordinates [height=20, width=2]",
                new Coordinates(2, 20).toString());
    }

    @Test
    public void testAdd() {
        assertEquals(new Coordinates(2, 10),
                new Coordinates(0, 10).add(new Coordinates(2, 0)));
        assertNotEquals(new Coordinates(2, 10),
                new Coordinates(0, 10).add(new Coordinates(2, 1)));
        assertEquals(new Coordinates(2, 4),
                new Coordinates(1, 3).add(new Coordinates(1, 1)));
    }

    @Test
    public void testMinus() {
        assertEquals(new Coordinates(2, 2),
                new Coordinates(3, 4).minus(new Coordinates(1, 2)));
        assertEquals(new Coordinates(-4, 2),
                new Coordinates(3, 4).minus(new Coordinates(7, 2)));
    }

    @Test
    public void testTimes() {
        assertEquals(new Coordinates(8, 4), new Coordinates(4, 2).times(2));
        assertEquals(new Coordinates(-8, -4), new Coordinates(4, 2).times(-2));
    }

    @Test
    public void testDivide() {
        assertEquals(new Coordinates(10, 5), new Coordinates(20, 10).divide(2));
    }

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
