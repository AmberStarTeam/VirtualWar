package org.amberstar.virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author beaussan
 *
 */
public final class Constant {
    /**
     * private constructor so we don't creat it
     */
    private Constant() {
    }

    /** UP movement up */
    static final Coordinates UP = new Coordinates(0, 1);
    /** DOWN movement down */ 
    static final Coordinates DOWN = new Coordinates(1, 0);
    /** RIGHT movement right*/
    static final Coordinates RIGHT = new Coordinates(1, 0);
    /** LEFT movement left*/
    static final Coordinates LEFT = new Coordinates(0, 1);
    /** DIAG_UP_RIGHT movement up right */
    static final Coordinates DIAG_UP_RIGHT = UP.add(RIGHT);
    /** DIAG_UP_LEFT movement up left */
    static final Coordinates DIAG_UP_LEFT = UP.add(LEFT);
    /** DIAG_DOWN_RIGHT movement down right */
    static final Coordinates DIAG_DOWN_RIGHT = DOWN.add(RIGHT);
    /** DIAG_DOWN_LEFT movement down left */
    static final Coordinates DIAG_DOWN_LEFT = DOWN.add(LEFT);
    /** MOVE_TANK movement for tanks */
    static final List<Coordinates> MOVE_TANK;
    /** MOVE_LIGHT_ROBOT movement for light robots */
    static final List<Coordinates> MOVE_LIGHT_ROBOT;
    static {
        MOVE_TANK = new ArrayList<Coordinates>();
        MOVE_TANK.add(UP.add(UP));
        MOVE_TANK.add(DOWN.add(DOWN));
        MOVE_TANK.add(RIGHT.add(RIGHT));
        MOVE_TANK.add(LEFT.add(LEFT));

        MOVE_LIGHT_ROBOT = new ArrayList<Coordinates>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (j == 0 && i == 0) {
                    continue;
                }
                MOVE_LIGHT_ROBOT.add(new Coordinates(i, j));
            }
        }
    }
}
