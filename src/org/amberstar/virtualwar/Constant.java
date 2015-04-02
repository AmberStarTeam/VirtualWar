package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Constant.
 *
 * @author beaussan
 */
public final class Constant {
	
	/**
	 * private constructor so we don't create it.
	 */
	private Constant() {
	}

	/** UP movement up. */
	static final Coordinates UP = new Coordinates(0, -1);
	
	/** DOWN movement down. */
	static final Coordinates DOWN = new Coordinates(0, 1);
	
	/** RIGHT movement right. */
	static final Coordinates RIGHT = new Coordinates(1, 0);
	
	/** LEFT movement left. */
	static final Coordinates LEFT = new Coordinates(-1, 0);
	
	/** DIAG_UP_RIGHT movement up right. */
	static final Coordinates DIAG_UP_RIGHT = UP.add(RIGHT);
	
	/** DIAG_UP_LEFT movement up left. */
	static final Coordinates DIAG_UP_LEFT = UP.add(LEFT);
	
	/** DIAG_DOWN_RIGHT movement down right. */
	static final Coordinates DIAG_DOWN_RIGHT = DOWN.add(RIGHT);
	
	/** DIAG_DOWN_LEFT movement down left. */
	static final Coordinates DIAG_DOWN_LEFT = DOWN.add(LEFT);
	
	/** MOVE_TANK movement for tanks. */
	static final List<Coordinates> MOVE_TANK;
	static {
		MOVE_TANK = new ArrayList<Coordinates>();
		MOVE_TANK.add(UP.add(UP));
		MOVE_TANK.add(DOWN.add(DOWN));
		MOVE_TANK.add(RIGHT.add(RIGHT));
		MOVE_TANK.add(LEFT.add(LEFT));

	}
	
	/** MOVE_LIGHT_ROBOT movement for light robots. */
	static final List<Coordinates> MOVE_LIGHT_ROBOT;
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
	static final int SHOOTER_RANGE = 3;
	
	/** The Constant SHOOTER_ENERGY_INITIAL. */
	static final int SHOOTER_ENERGY_INITIAL = 40;
	
	/** The Constant SHOOTER_ENERGY_REGEN. */
	static final int SHOOTER_ENERGY_REGEN = 2;
	
	/** The Constant SHOOTER_ENERGY_ACTION. */
	static final int SHOOTER_ENERGY_ACTION = 2;
	
	/** The Constant SHOOTER_ENERGY_MOVE. */
	static final int SHOOTER_ENERGY_MOVE = 1;
	
	/** The Constant SHOOTER_DAMAGE_SUBMIT. */
	static final int SHOOTER_DAMAGE_SUBMIT = 3;

	
	/** The Constant SCAVENGER_RANGE. */
	static final int SCAVENGER_RANGE = 1;
	
	/** The Constant SCAVENGER_ENERGY_INITIAL. */
	static final int SCAVENGER_ENERGY_INITIAL = 50;
	
	/** The Constant SCAVENGER_ENERGY_REGEN. */
	static final int SCAVENGER_ENERGY_REGEN = 2;
	
	/** The Constant SCAVENGER_ENERGY_ACTION. */
	static final int SCAVENGER_ENERGY_ACTION = 2;
	
	/** The Constant SCAVENGER_ENERGY_MOVE. */
	static final int SCAVENGER_ENERGY_MOVE = 2;
	
	/** The Constant SCAVENGER_DAMAGE_SUBMIT. */
	static final int SCAVENGER_DAMAGE_SUBMIT = 2;
	
	/** The Constant SCAVENGER_MAX_MINE. */
	static final int SCAVENGER_MAX_MINE = 10;

	
	/** The Constant TANK_RANGE. */
	static final int TANK_RANGE = 10;
	
	/** The Constant TANK_ENERGY_INITIAL. */
	static final int TANK_ENERGY_INITIAL = 60;
	
	/** The Constant TANK_ENERGY_REGEN. */
	static final int TANK_ENERGY_REGEN = 2;
	
	/** The Constant TANK_ENERGY_ACTION. */
	static final int TANK_ENERGY_ACTION = 2;
	
	/** The Constant TANK_ENERGY_MOVE. */
	static final int TANK_ENERGY_MOVE = 5;
	
	/** The Constant TANK_DAMAGE_SUBMIT. */
	static final int TANK_DAMAGE_SUBMIT = 6;

}
