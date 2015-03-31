package org.amberstar.virtualwar;

/**
 * 
 * @author beaussan
 *
 */
public abstract class Action {
    /** the robot source */
    private Robot robotIn;
    /** the direction of action */
    private Coordinates direction;

    /**
     * 
     * @param robot
     *            robot from action
     * @param dir
     *            direction to do act
     */
    public Action(Robot robot, Coordinates dir) {
        this.robotIn = robot;
        this.direction = dir;
    }

    /**
     * 
     * @return the source of the action
     */
    public Robot getRobotSource() {
        return robotIn;
    }

    /**
     * 
     * @return the direction of the action
     */
    public Coordinates getDirection() {
        return direction;
    }

    /** run the action */
    abstract void act();

}
