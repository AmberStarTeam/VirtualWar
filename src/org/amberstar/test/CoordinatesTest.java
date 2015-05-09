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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.amberstar.virtualwar.Board;
import org.amberstar.virtualwar.Coordinates;
import org.junit.Test;

/**
 * Testing of Coordinates.
 * 
 * @author amberStar
 *
 */
public class CoordinatesTest {

    /**
     * test constructor.
     */
    @Test
    public void testCoordinates() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
        assertTrue(tmp.getWidth() == 2);
    }

    /**
     * test getter width.
     */
    @Test
    public void testGetWidth() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getWidth() == 2);
    }

    /**
     * test getter height.
     */
    @Test
    public void testGetHeight() {
        Coordinates tmp = new Coordinates(2, 20);
        assertTrue(tmp.getHeight() == 20);
    }

    /**
     * test toString.
     */
    @Test
    public void testToString() {
        assertEquals("Coordinates [height=20, width=2]",
                new Coordinates(2, 20).toString());
    }

    /**
     * test add.
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
     * test minus function.
     */
    @Test
    public void testMinus() {
        assertEquals(new Coordinates(2, 2),
                new Coordinates(3, 4).minus(new Coordinates(1, 2)));
        assertEquals(new Coordinates(-4, 2),
                new Coordinates(3, 4).minus(new Coordinates(7, 2)));
    }

    /**
     * test multiplying.
     */
    @Test
    public void testTimes() {
        assertEquals(new Coordinates(8, 4), new Coordinates(4, 2).times(2));
        assertEquals(new Coordinates(-8, -4), new Coordinates(4, 2).times(-2));
    }

    /**
     * test dividing coordinates.
     */
    @Test
    public void testDivide() {
        assertEquals(new Coordinates(10, 5), new Coordinates(20, 10).divide(2));
    }

    /**
     * test equals.
     */
    @Test
    public void testEqualsObject() {
        assertTrue("Equals",
                new Coordinates(0, 10).equals(new Coordinates(0, 10)));
        assertFalse("Equals",
                new Coordinates(0, 10).equals(new Coordinates(1, 10)));
        assertFalse("Equals", new Coordinates(0, 10).equals(null));
        assertFalse("Equals", new Coordinates(0, 10).equals(Board.newBoard(5, 5)));

    }

}
