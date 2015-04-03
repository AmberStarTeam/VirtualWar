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
package org.amberstar.virtualwar;

/**
 * The Coordinates rightClass.
 *
 * @author amberStar
 */
public class Coordinates {

    /** the width coordinate. */
    private int width;

    /** the height coordinate. */
    private int height;

    /**
     * default constructor.
     *
     * @param width
     *            width coordinates
     * @param height
     *            height coordinates
     */
    public Coordinates(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the width coordinate.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height coordinate.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Coordinates [height=" + height + ", width=" + width + "]";
    }

    /**
     * Adds the.
     *
     * @param c
     *            coordinate to add
     * @return a new coordinate
     */
    public Coordinates add(Coordinates c) {
        return new Coordinates(width + c.width, height + c.height);
    }

    /**
     * Minus.
     *
     * @param c
     *            coordinate to minus
     * @return a new coordinate
     */
    public Coordinates minus(Coordinates c) {
        return new Coordinates(width - c.width, height - c.height);
    }

    /**
     * Times.
     *
     * @param n
     *            the number to multiply by
     * @return a new coordinate
     */
    public Coordinates times(int n) {
        return new Coordinates(width * n, height * n);
    }

    /**
     * Divide.
     *
     * @param n
     *            the number to divide by
     * @return a new coordinate
     */
    public Coordinates divide(int n) {
        return new Coordinates(width / n, height / n);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + width;
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Coordinates other = (Coordinates) obj;
        if (height != other.height) {
            return false;
        }
        if (width != other.width) {
            return false;
        }
        return true;
    }

}
