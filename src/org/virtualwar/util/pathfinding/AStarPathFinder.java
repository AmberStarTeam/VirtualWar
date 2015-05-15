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
import java.util.Collections;
import java.util.List;

import org.virtualwar.util.Coordinates;
import org.virtualwar.util.pathfinding.heuristics.ClosestHeuristic;

/**
 * A path finder implementation that uses the AStar heuristic based algorithm to
 * determine a path.
 *
 * @author beaussan
 */
public class AStarPathFinder implements PathFinder {
	/**
	 * A single node in the search graph
	 */
	private class Node implements Comparable<Node> {
		/** The coordinate of the node */
		private Coordinates cords;
		/** The path cost for this node */
		private float cost;
		/** The parent of this node, how we reached it in the search */
		private Node parent;
		/** The heuristic cost of this node */
		private float heuristic;
		/** The search depth of this node */
		private int depth;

		/**
		 * Create a new node
		 *
		 * @param x
		 *            The x coordinate of the node
		 * @param y
		 *            The y coordinate of the node
		 */
		public Node(Coordinates cords) {
			this.cords = cords;
		}

		/**
		 * @see Comparable#compareTo(Object)
		 */
		@Override
		public int compareTo(Node other) {
			Node o = other;

			float f = heuristic + cost;
			float of = o.heuristic + o.cost;

			if (f < of) {
				return -1;
			} else if (f > of) {
				return 1;
			} else {
				return 0;
			}
		}

		/**
		 * Set the parent of this node
		 *
		 * @param parent
		 *            The parent node which lead us to this node
		 * @return The depth we have no reached in searching
		 */
		public int setParent(Node parent) {
			depth = parent.depth + 1;
			this.parent = parent;

			return depth;
		}
	}

	/**
	 * A simple sorted list
	 *
	 * @author beaussan
	 */
	private class SortedList {
		/** The list of elements */
		// <Comparable<? super Comparable<? super Object>>>
		private List<Node> list = new ArrayList<Node>();

		/**
		 * Add an element to the list - causes sorting
		 *
		 * @param o
		 *            The element to add
		 */
		public void add(Node o) {
			list.add(o);
			Collections.sort(list);
		}

		/**
		 * Empty the list
		 */
		public void clear() {
			list.clear();
		}

		/**
		 * Check if an element is in the list
		 *
		 * @param o
		 *            The element to search for
		 * @return True if the element is in the list
		 */
		public boolean contains(Node o) {
			return list.contains(o);
		}

		/**
		 * Retrieve the first element from the list
		 *
		 * @return The first element from the list
		 */
		public Node first() {
			return list.get(0);
		}

		/**
		 * Remove an element from the list
		 *
		 * @param o
		 *            The element to remove
		 */
		public void remove(Node o) {
			list.remove(o);
		}

		/**
		 * Get the number of elements in the list
		 *
		 * @return The number of element in the list
		 */
		public int size() {
			return list.size();
		}
	}

	/** The set of nodes that have been searched through */
	private ArrayList<Node> closed = new ArrayList<Node>();
	/** The set of nodes that we do not yet consider fully searched */
	private SortedList open = new SortedList();

	/** The map being searched */
	private TileBasedMap map;
	/** The maximum depth of search we're willing to accept before giving up */
	private int maxSearchDistance;
	/** The complete set of nodes across the map */
	private Node[][] nodes;

	/** True if we allow diaganol movement */
	private boolean allowDiagMovement;

	/** The heuristic we're applying to determine which nodes to search first */
	private AStarHeuristic heuristic;

	/**
	 * Create a path finder with the default heuristic - closest to target.
	 *
	 * @param map
	 *            The map to be searched
	 * @param maxSearchDistance
	 *            The maximum depth we'll search before giving up
	 * @param allowDiagMovement
	 *            True if the search should try diaganol movement
	 */
	public AStarPathFinder(TileBasedMap map, int maxSearchDistance,
			boolean allowDiagMovement) {
		this(map, maxSearchDistance, allowDiagMovement, new ClosestHeuristic());
	}

	/**
	 * Create a path finder
	 *
	 * @param heuristic
	 *            The heuristic used to determine the search order of the map
	 * @param map
	 *            The map to be searched
	 * @param maxSearchDistance
	 *            The maximum depth we'll search before giving up
	 * @param allowDiagMovement
	 *            True if the search should try diaganol movement
	 */
	public AStarPathFinder(TileBasedMap map, int maxSearchDistance,
			boolean allowDiagMovement, AStarHeuristic heuristic) {
		this.heuristic = heuristic;
		this.map = map;
		this.maxSearchDistance = maxSearchDistance;
		this.allowDiagMovement = allowDiagMovement;

		nodes = new Node[map.getWidthInTiles()][map.getHeightInTiles()];
		for (int x = 0; x < map.getWidthInTiles(); x++) {
			for (int y = 0; y < map.getHeightInTiles(); y++) {
				nodes[x][y] = new Node(new Coordinates(y, x));
			}
		}
	}

	/**
	 * Add a node to the closed list
	 *
	 * @param node
	 *            The node to add to the closed list
	 */
	protected void addToClosed(Node node) {
		closed.add(node);
	}

	/**
	 * Add a node to the open list
	 *
	 * @param node
	 *            The node to be added to the open list
	 */
	protected void addToOpen(Node node) {
		open.add(node);
	}

	@Override
	public Path findPath(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo) {
		// easy first check, if the destination is blocked, we can't get there
		if (map.blocked(mover, cordsTo)) {
			return null;
		}

		// initial state for A*. The closed group is empty. Only the starting
		// tile is in the open list and it's cost is zero, i.e. we're already
		// there
		getNode(cordsSource).cost = 0;
		getNode(cordsSource).depth = 0;
		closed.clear();
		open.clear();
		open.add(getNode(cordsSource));

		getNode(cordsTo).parent = null;

		// while we haven't found the goal and haven't exceeded our max search
		// depth
		int maxDepth = 0;
		while ((maxDepth < maxSearchDistance) && (open.size() != 0)) {
			// pull out the first node in our open list, this is determined to
			// be the most likely to be the next step based on our heuristic
			Node current = getFirstInOpen();
			if (current == getNode(cordsTo)) {
				break;
			}

			removeFromOpen(current);
			addToClosed(current);

			// search through all the neighbours of the current node evaluating
			// them as next steps
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					// not a neighbour, its the current tile
					if ((x == 0) && (y == 0)) {
						continue;
					}

					// if we're not allowing diaganol movement then only
					// one of x or y can be set
					if (!allowDiagMovement) {
						if ((x != 0) && (y != 0)) {
							continue;
						}
					}

					// determine the location of the neighbour and evaluate it
					int xp = x + current.cords.getHeight();
					int yp = y + current.cords.getWidth();
					Coordinates xCords = new Coordinates(xp, yp);

					if (isValidLocation(mover, cordsSource, xCords)) {
						// the cost to get to this node is cost the current plus
						// the movement
						// cost to reach this node. Note that the heursitic
						// value is only used
						// in the sorted open list
						float nextStepCost = current.cost
								+ getMovementCost(mover, current.cords, xCords);
						Node neighbour = getNode(xCords);
						map.pathFinderVisited(xCords);

						// if the new cost we've determined for this node is
						// lower than
						// it has been previously makes sure the node hasn't
						// been discarded. We've
						// determined that there might have been a better path
						// to get to
						// this node so it needs to be re-evaluated
						if (nextStepCost < neighbour.cost) {
							if (inOpenList(neighbour)) {
								removeFromOpen(neighbour);
							}
							if (inClosedList(neighbour)) {
								removeFromClosed(neighbour);
							}
						}

						// if the node hasn't already been processed and
						// discarded then
						// reset it's cost to our current cost and add it as a
						// next possible
						// step (i.e. to the open list)
						if (!inOpenList(neighbour)
								&& !(inClosedList(neighbour))) {
							neighbour.cost = nextStepCost;
							neighbour.heuristic = getHeuristicCost(mover,
									xCords, cordsTo);
							maxDepth = Math.max(maxDepth,
									neighbour.setParent(current));
							addToOpen(neighbour);
						}
					}
				}
			}
		}

		// since we've got an empty open list or we've run out of search
		// there was no path. Just return null
		if (getNode(cordsTo).parent == null) {
			return null;
		}

		// At this point we've definitely found a path so we can uses the parent
		// references of the nodes to find out way from the target location back
		// to the start recording the nodes on the way.
		Path path = new Path();
		Node target = getNode(cordsTo);
		while (target != getNode(cordsSource)) {
			path.prependStep(target.cords);
			target = target.parent;
		}
		path.prependStep(cordsSource);

		// thats it, we have our path
		return path;
	}

	/**
	 * Get the first element from the open list. This is the next one to be
	 * searched.
	 *
	 * @return The first element in the open list
	 */
	protected Node getFirstInOpen() {
		return open.first();
	}

	/**
	 * Get the heuristic cost for the given location. This determines in which
	 * order the locations are processed.
	 *
	 * @param mover
	 *            The entity that is being moved
	 * @param x
	 *            The x coordinate of the tile whose cost is being determined
	 * @param y
	 *            The y coordiante of the tile whose cost is being determined
	 * @param tx
	 *            The x coordinate of the target location
	 * @param ty
	 *            The y coordinate of the target location
	 * @return The heuristic cost assigned to the tile
	 */
	public float getHeuristicCost(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo) {
		return heuristic.getCost(map, mover, cordsSource, cordsTo);
	}

	/**
	 * Get the cost to move through a given location
	 *
	 * @param mover
	 *            The entity that is being moved
	 * @param sx
	 *            The x coordinate of the tile whose cost is being determined
	 * @param sy
	 *            The y coordiante of the tile whose cost is being determined
	 * @param tx
	 *            The x coordinate of the target location
	 * @param ty
	 *            The y coordinate of the target location
	 * @return The cost of movement through the given tile
	 */
	public float getMovementCost(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo) {
		return map.getCost(mover, cordsSource, cordsTo);
	}

	private Node getNode(Coordinates cords) {
		return nodes[cords.getWidth()][cords.getHeight()];
	}

	/**
	 * Check if the node supplied is in the closed list
	 *
	 * @param node
	 *            The node to search for
	 * @return True if the node specified is in the closed list
	 */
	protected boolean inClosedList(Node node) {
		return closed.contains(node);
	}

	/**
	 * Check if a node is in the open list
	 *
	 * @param node
	 *            The node to check for
	 * @return True if the node given is in the open list
	 */
	protected boolean inOpenList(Node node) {
		return open.contains(node);
	}

	/**
	 * Check if a given location is valid for the supplied mover
	 *
	 * @param mover
	 *            The mover that would hold a given location
	 * @param sx
	 *            The starting x coordinate
	 * @param sy
	 *            The starting y coordinate
	 * @param x
	 *            The x coordinate of the location to check
	 * @param y
	 *            The y coordinate of the location to check
	 * @return True if the location is valid for the given mover
	 */
	protected boolean isValidLocation(Mover mover, Coordinates cordsSource,
			Coordinates cordsTo) {
		boolean invalid = (cordsTo.getWidth() < 0) || (cordsTo.getHeight() < 0)
				|| (cordsTo.getWidth() >= map.getWidthInTiles())
				|| (cordsTo.getHeight() >= map.getHeightInTiles());

		if ((!invalid) && (!cordsSource.equals(cordsTo))) {
			invalid = map.blocked(mover, cordsTo);
		}

		return !invalid;
	}

	/**
	 * Remove a node from the closed list
	 *
	 * @param node
	 *            The node to remove from the closed list
	 */
	protected void removeFromClosed(Node node) {
		closed.remove(node);
	}

	/**
	 * Remove a node from the open list
	 *
	 * @param node
	 *            The node to remove from the open list
	 */
	protected void removeFromOpen(Node node) {
		open.remove(node);
	}
}
