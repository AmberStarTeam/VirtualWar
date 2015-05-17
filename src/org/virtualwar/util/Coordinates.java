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
package org.virtualwar.util;

import org.virtualwar.config.Constant;
import org.virtualwar.config.TextData;

/**
 * The Coordinates rightClass.
 *
 * @author amberstar
 */
public class Coordinates {

	/** the width coordinate. */
	private int width;

	/** the height coordinate. */
	private int height;

	/**
	 * Copy constructor.
	 *
	 * @param other
	 *            Other coordinates
	 */
	public Coordinates(Coordinates other) {
		width = other.width;
		height = other.height;
	}

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
	 * Adds the coordinate c into this one as a new one.
	 *
	 * @param c
	 *            coordinate to add
	 * @return a new coordinate
	 */
	public Coordinates add(Coordinates c) {
		return new Coordinates(width + c.width, height + c.height);
	}

	/**
	 * Divide the coordinate c into this one as a new one.
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

	/**
	 * Gets the height coordinate.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the width coordinate.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
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

	/**
	 * Minus the coordinate c into this one as a new one.
	 *
	 * @param c
	 *            coordinate to minus
	 * @return a new coordinate
	 */
	public Coordinates minus(Coordinates c) {
		return new Coordinates(width - c.width, height - c.height);
	}

	/**
	 * Multiplies the coordinate c into this one as a new one.
	 *
	 * @param n
	 *            the number to multiply by
	 * @return a new coordinate
	 */
	public Coordinates times(int n) {
		return new Coordinates(width * n, height * n);
	}

	/**
	 * Return the text value of the direction if know
	 *
	 * @return the value
	 */
	public String toStr() {
		if (equals(Constant.UP) || equals(Constant.UP.times(2))) {
			return TextData.LABEL_UP.toString();
		}
		if (equals(Constant.DOWN) || equals(Constant.DOWN.times(2))) {
			return TextData.LABEL_DOWN.toString();
		}
		if (equals(Constant.RIGHT) || equals(Constant.RIGHT.times(2))) {
			return TextData.LABEL_RIGHT.toString();
		}
		if (equals(Constant.LEFT) || equals(Constant.LEFT.times(2))) {
			return TextData.LABEL_LEFT.toString();
		}

		if (equals(Constant.DIAG_DOWN_LEFT)) {
			return TextData.LABEL_DOWN_LEFT.toString();
		}
		if (equals(Constant.DIAG_DOWN_RIGHT)) {
			return TextData.LABEL_DOWN_RIGHT.toString();
		}

		if (equals(Constant.DIAG_UP_LEFT)) {
			return TextData.LABEL_UP_LEFT.toString();
		}
		if (equals(Constant.DIAG_UP_RIGHT)) {
			return TextData.LABEL_UP_RIGHT.toString();
		}

		return "(" + TextData.GAME_WIDTH.toString().charAt(0) + width + ": "
				+ TextData.GAME_HEIGHT.toString().charAt(0) + height + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + TextData.GAME_HEIGHT + "=" + height + ", "
				+ TextData.GAME_WIDTH + "=" + width + "]";
	}

}
