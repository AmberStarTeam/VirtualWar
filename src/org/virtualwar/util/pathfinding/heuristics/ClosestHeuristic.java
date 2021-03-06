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
 * A heuristic that uses the tile that is closest to the target as the next best
 * tile.
 *
 * @author beaussan
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, Coordinates,
	 *      Coordinates)
	 */
	@Override
	public float getCost(TileBasedMap map, Mover mover, Coordinates coorFrom,
			Coordinates coorTo) {
		float dx = coorTo.getWidth() - coorFrom.getWidth();
		float dy = coorTo.getHeight() - coorFrom.getHeight();

		float result = (float) (Math.sqrt((dx * dx) + (dy * dy)));

		return result;
	}

}
