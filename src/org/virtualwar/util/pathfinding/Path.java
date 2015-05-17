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

import org.virtualwar.util.Coordinates;

// TODO: Auto-generated Javadoc
/**
 * A path determined by some path finding algorithm. A series of steps from the
 * starting location to the target location. This includes a step for the
 * initial location.
 *
 * @author beaussan
 */
public class Path {
	
	/**
	 * A single step within the path.
	 *
	 * @author Kevin Glass
	 */
	public class Step {
		
		/**  The coordinates at the given step. */
		private Coordinates cords;

		/**
		 * Create a new step.
		 *
		 * @param cords            The coordinate of the new step
		 */
		public Step(Coordinates cords) {
			this.cords = cords;
		}

		/**
		 * Equals.
		 *
		 * @param other the other
		 * @return true, if successful
		 * @see Object#equals(Object)
		 */
		@Override
		public boolean equals(Object other) {
			if (other instanceof Step) {
				Step o = (Step) other;

				return (o.cords.equals(cords));
			}

			return false;
		}

		/**
		 * Gets the coords.
		 *
		 * @return the coords
		 */
		public Coordinates getCoords() {
			return cords;
		}

		/**
		 * Hash code.
		 *
		 * @return the int
		 * @see Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return cords.hashCode();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "" + cords;
		}
	}

	/**  The list of steps building up this path. */
	private ArrayList<Step> steps = new ArrayList<Step>();

	/**
	 * Create an empty path.
	 */
	public Path() {

	}

	/**
	 * Append a step to the path.
	 *
	 * @param cords
	 *            The coordinate of the new step
	 */
	public void appendStep(Coordinates cords) {
		steps.add(new Step(cords));
	}

	/**
	 * Check if this path contains the given step.
	 *
	 * @param cords            The coordinate of the step to check for
	 * @return True if the path contains the given step
	 */
	public boolean contains(Coordinates cords) {
		return steps.contains(new Step(cords));
	}

	/**
	 * Get the coordinate for the step at the given index.
	 *
	 * @param index            The index of the step whose y coordinate should be retrieved
	 * @return The coordinate at the step
	 */
	public Coordinates getCoords(int index) {
		return getStep(index).cords;
	}

	/**
	 * Get the coordinate for the step at the given index from the previous step.
	 *
	 * @param index            The index of the step whose y coordinate should be retrieved
	 * @return The coordinate at the step
	 */
	public Coordinates getCoordsRelativ(int index) {
		if (index == 0) {
			return new Coordinates(0, 0);
		} else {
			return getStep(index).cords.minus(getStep(index - 1).cords);
		}
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
	 * Get the step at a given index in the path.
	 *
	 * @param index            The index of the step to retrieve. Note this should be >= 0
	 *            and < getLength();
	 * @return The step information, the position on the map.
	 */
	public Step getStep(int index) {
		return steps.get(index);
	}

	/**
	 * Prepend a step to the path.
	 *
	 * @param cords
	 *            The coordinate of the new step
	 */
	public void prependStep(Coordinates cords) {
		steps.add(0, new Step(cords));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return steps.toString();
	}

}
