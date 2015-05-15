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
package org.virtualwar.util.pathfinding.heuristics;

import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.AStarHeuristic;
import org.virtualwar.util.pathfinding.Mover;
import org.virtualwar.util.pathfinding.TileBasedMap;

/**
 * A heuristic that drives the search based on the Manhattan distance between
 * the current location and the target
 *
 * @author beaussan
 */
public class ManhattanHeuristic implements AStarHeuristic {
	/** The minimum movement cost from any one square to the next */
	private int minimumCost;

	/**
	 * Create a new heuristic
	 *
	 * @param minimumCost
	 *            The minimum movement cost from any one square to the next
	 */
	public ManhattanHeuristic(int minimumCost) {
		this.minimumCost = minimumCost;
	}

	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, Coordinates,
	 *      Coordinates)
	 */
	@Override
	public float getCost(TileBasedMap map, Mover mover, Coordinates coorFrom,
			Coordinates coorTo) {
		return minimumCost
				* (Math.abs(coorFrom.getWidth() - coorTo.getWidth()) + Math
						.abs(coorFrom.getHeight() - coorTo.getHeight()));
	}

	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	@Override
	public float getCost(TileBasedMap map, Mover mover, int x, int y, int tx,
			int ty) {
		return minimumCost * (Math.abs(x - tx) + Math.abs(y - ty));
	}

}
