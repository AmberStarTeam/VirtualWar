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
 * A description of an implementation that can find a path from one location on
 * a tile map to another based on information provided by that tile map.
 *
 * @see TileBasedMap
 * @author beaussan
 */
public interface PathFinder {

	/**
	 * Find a path from the starting location provided (cordsSource) to the
	 * target location (cordsTo) avoiding blockages and attempting to honour
	 * costs provided by the tile map.
	 *
	 * @param mover
	 *            The entity that will be moving along the path. This provides a
	 *            place to pass context information about the game entity doing
	 *            the moving, e.g. can it fly? can it swim etc.
	 *
	 * @param cordsSource
	 *            The coordinate of the start location
	 * @param cordsTo
	 *            The coordinate of the target location
	 * @return The path found from start to end, or null if no path can be
	 *         found.
	 */
	public Path findPath(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo);
}
