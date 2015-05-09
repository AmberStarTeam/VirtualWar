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
package org.virtualwar.config;

import java.util.ArrayList;
import java.util.List;

import org.virtualwar.util.Coordinates;

/**
 * The Constant Class.
 *
 * @author amberstar
 */
public final class Constant {

	/** UP movement up. */
	public static final Coordinates UP = new Coordinates(0, -1);

	/** DOWN movement down. */
	public static final Coordinates DOWN = new Coordinates(0, 1);

	/** RIGHT movement right. */
	public static final Coordinates RIGHT = new Coordinates(1, 0);

	/** LEFT movement left. */
	public static final Coordinates LEFT = new Coordinates(-1, 0);

	/** DIAG_UP_RIGHT movement up right. */
	public static final Coordinates DIAG_UP_RIGHT = UP.add(RIGHT);

	/** DIAG_UP_LEFT movement up left. */
	public static final Coordinates DIAG_UP_LEFT = UP.add(LEFT);

	/** DIAG_DOWN_RIGHT movement down right. */
	public static final Coordinates DIAG_DOWN_RIGHT = DOWN.add(RIGHT);

	/** DIAG_DOWN_LEFT movement down left. */
	public static final Coordinates DIAG_DOWN_LEFT = DOWN.add(LEFT);

	/** CARDINAL_DIRECTION cardinal directions. */
	public static final List<Coordinates> CARDINAL_DIRECTION;

	static {
		CARDINAL_DIRECTION = new ArrayList<Coordinates>();
		CARDINAL_DIRECTION.add(UP);
		CARDINAL_DIRECTION.add(DOWN);
		CARDINAL_DIRECTION.add(RIGHT);
		CARDINAL_DIRECTION.add(LEFT);
	}
	/** MOVE_TANK movement for tanks. */
	public static final List<Coordinates> MOVE_TANK;

	static {
		MOVE_TANK = new ArrayList<Coordinates>();
		MOVE_TANK.add(UP.add(UP));
		MOVE_TANK.add(DOWN.add(DOWN));
		MOVE_TANK.add(RIGHT.add(RIGHT));
		MOVE_TANK.add(LEFT.add(LEFT));

	}
	/** MOVE_LIGHT_ROBOT movement for light robots. */
	public static final List<Coordinates> MOVE_LIGHT_ROBOT;

	static {
		MOVE_LIGHT_ROBOT = new ArrayList<Coordinates>();
		MOVE_LIGHT_ROBOT.add(UP);
		MOVE_LIGHT_ROBOT.add(DOWN);
		MOVE_LIGHT_ROBOT.add(RIGHT);
		MOVE_LIGHT_ROBOT.add(LEFT);
		MOVE_LIGHT_ROBOT.add(DIAG_UP_RIGHT);
		MOVE_LIGHT_ROBOT.add(DIAG_UP_LEFT);
		MOVE_LIGHT_ROBOT.add(DIAG_DOWN_RIGHT);
		MOVE_LIGHT_ROBOT.add(DIAG_DOWN_LEFT);
	}
	/** The Constant SHOOTER_RANGE. */
	public static final int SHOOTER_RANGE = 3;

	/** The Constant SHOOTER_ENERGY_INITIAL. */
	public static final int SHOOTER_ENERGY_INITIAL = 40;

	/** The Constant SHOOTER_ENERGY_REGEN. */
	public static final int SHOOTER_ENERGY_REGEN = 2;

	/** The Constant SHOOTER_ENERGY_ACTION. */
	public static final int SHOOTER_ENERGY_ACTION = 2;

	/** The Constant SHOOTER_ENERGY_MOVE. */
	public static final int SHOOTER_ENERGY_MOVE = 1;

	/** The Constant SHOOTER_DAMAGE_SUBMIT. */
	public static final int SHOOTER_DAMAGE_SUBMIT = 3;

	/** The Constant SCAVENGER_RANGE. */
	public static final int SCAVENGER_RANGE = 1;

	/** The Constant SCAVENGER_ENERGY_INITIAL. */
	public static final int SCAVENGER_ENERGY_INITIAL = 50;

	/** The Constant SCAVENGER_ENERGY_REGEN. */
	public static final int SCAVENGER_ENERGY_REGEN = 2;

	/** The Constant SCAVENGER_ENERGY_ACTION. */
	public static final int SCAVENGER_ENERGY_ACTION = 2;

	/** The Constant SCAVENGER_ENERGY_MOVE. */
	public static final int SCAVENGER_ENERGY_MOVE = 2;

	/** The Constant SCAVENGER_DAMAGE_SUBMIT. */
	public static final int SCAVENGER_DAMAGE_SUBMIT = 2;

	/** The Constant SCAVENGER_MAX_MINE. */
	public static final int SCAVENGER_MAX_MINE = 10;

	/** The Constant TANK_RANGE. */
	public static final int TANK_RANGE = 10;

	/** The Constant TANK_ENERGY_INITIAL. */
	public static final int TANK_ENERGY_INITIAL = 60;

	/** The Constant TANK_ENERGY_REGEN. */
	public static final int TANK_ENERGY_REGEN = 2;

	/** The Constant TANK_ENERGY_ACTION. */
	public static final int TANK_ENERGY_ACTION = 2;

	/** The Constant TANK_ENERGY_MOVE. */
	public static final int TANK_ENERGY_MOVE = 5;

	/** The Constant TANK_DAMAGE_SUBMIT. */
	public static final int TANK_DAMAGE_SUBMIT = 6;

	/** The id team a. */
	public static final int ID_TEAM_A = 1;

	/** The id team b. */
	public static final int ID_TEAM_B = 2;

	/** The id team b. */
	public static final int ID_TEAM_NULL = 0;

	public static final String ROBOT_DEATH_SOUND = "sounds/deathOfRobots.wav";

	/**
	 * private constructor so we don't create it.
	 */
	private Constant() {
	}

}
