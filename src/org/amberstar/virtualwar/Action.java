package org.amberstar.virtualwar;

/**
 * 
 * @author beaussan
 *
 */
public abstract class Action {
    /** TODO:javadoc. */
    private Robot robotIn;
    /** TODO:javadoc. */
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
     * @return TODO
     */
    public Robot getRobotSource() {
        return robotIn;
    }
    /**
     * 
     * @return TODO
     */
    public Coordinates getDirection() {
        return direction;
    }
    /** TODO:javadoc. */
    abstract void act();

}
