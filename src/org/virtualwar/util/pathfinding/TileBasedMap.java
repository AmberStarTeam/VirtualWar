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

import org.virtualwar.util.Coordinates;

/**
 * The description for the data we're pathfinding over. This provides the
 * contract between the data being searched (i.e. the in game map) and the path
 * finding generic tools
 *
 * @author Kevin Glass
 */
public interface TileBasedMap {
	/**
	 * Check if the given location is blocked, i.e. blocks movement of the
	 * supplied mover.
	 *
	 * @param mover
	 *            The mover that is potentially moving through the specified
	 *            tile.
	 * @param cords
	 *            The coordinate of the tile to check
	 * @return True if the location is blocked
	 */
	boolean blocked(Mover mover, Coordinates cords);

	/**
	 * Get the cost of moving through the given tile. This can be used to make
	 * certain areas more desirable. A simple and valid implementation of this
	 * method would be to return 1 in all cases.
	 *
	 * @param mover
	 *            The mover that is trying to move across the tile
	 * @param cordsSource
	 *            The coordinate of the tile we're moving from
	 * @param cordsTo
	 *            The coordinate of the tile we're moving to
	 * @return The relative cost of moving across the given tile
	 */
	float getCost(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo);

	/**
	 * Get the height of the tile map. The slightly odd name is used to
	 * distiguish this method from commonly used names in game maps.
	 *
	 * @return The number of tiles down the map
	 */
	int getHeightInTiles();

	/**
	 * Get the width of the tile map. The slightly odd name is used to
	 * distiguish this method from commonly used names in game maps.
	 *
	 * @return The number of tiles across the map
	 */
	int getWidthInTiles();

	/**
	 * Notification that the path finder visited a given tile. This is used for
	 * debugging new heuristics.
	 *
	 * @param cords
	 *            The coordinate of the tile that was visited
	 */
	void pathFinderVisited(Coordinates cords);

}
