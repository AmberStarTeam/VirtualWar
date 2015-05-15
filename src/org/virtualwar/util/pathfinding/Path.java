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
package org.virtualwar.util.pathfinding;

import java.util.ArrayList;

/**
 * A path determined by some path finding algorithm. A series of steps from the
 * starting location to the target location. This includes a step for the
 * initial location.
 *
 * @author Kevin Glass
 */
public class Path {
	/**
	 * A single step within the path
	 *
	 * @author Kevin Glass
	 */
	public class Step {
		/** The x coordinate at the given step */
		private int x;
		/** The y coordinate at the given step */
		private int y;

		/**
		 * Create a new step
		 *
		 * @param x
		 *            The x coordinate of the new step
		 * @param y
		 *            The y coordinate of the new step
		 */
		public Step(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @see Object#equals(Object)
		 */
		@Override
		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;

				return (o.x == x) && (o.y == y);
			}

			return false;
		}

		/**
		 * Get the x coordinate of the new step
		 *
		 * @return The x coodindate of the new step
		 */
		public int getX() {
			return x;
		}

		/**
		 * Get the y coordinate of the new step
		 *
		 * @return The y coodindate of the new step
		 */
		public int getY() {
			return y;
		}

		/**
		 * @see Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return x * y;
		}
	}

	/** The list of steps building up this path */
	private ArrayList<Step> steps = new ArrayList<Step>();

	/**
	 * Create an empty path
	 */
	public Path() {

	}

	/**
	 * Append a step to the path.
	 *
	 * @param x
	 *            The x coordinate of the new step
	 * @param y
	 *            The y coordinate of the new step
	 */
	public void appendStep(int x, int y) {
		steps.add(new Step(x, y));
	}

	/**
	 * Check if this path contains the given step
	 *
	 * @param x
	 *            The x coordinate of the step to check for
	 * @param y
	 *            The y coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(int x, int y) {
		return steps.contains(new Step(x, y));
	}

	/**
	 * Get the length of the path, i.e. the number of steps
	 *
	 * @return The number of steps in this path
	 */
	public int getLength() {
		return steps.size();
	}

	/**
	 * Get the step at a given index in the path
	 *
	 * @param index
	 *            The index of the step to retrieve. Note this should be >= 0
	 *            and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(int index) {
		return steps.get(index);
	}

	/**
	 * Get the x coordinate for the step at the given index
	 *
	 * @param index
	 *            The index of the step whose x coordinate should be retrieved
	 * @return The x coordinate at the step
	 */
	public int getX(int index) {
		return getStep(index).x;
	}

	/**
	 * Get the y coordinate for the step at the given index
	 *
	 * @param index
	 *            The index of the step whose y coordinate should be retrieved
	 * @return The y coordinate at the step
	 */
	public int getY(int index) {
		return getStep(index).y;
	}

	/**
	 * Prepend a step to the path.
	 *
	 * @param x
	 *            The x coordinate of the new step
	 * @param y
	 *            The y coordinate of the new step
	 */
	public void prependStep(int x, int y) {
		steps.add(0, new Step(x, y));
	}
}
