package org.amberstar.virtualwar;

/**
 * 
 * @author beaussan
 *
 */
public class Move extends Action {
    /**
     * default constructor
     * 
     * @param robotIn
     *            the robot from
     * @param direction
     *            the coordinate of movement
     */
    public Move(Robot robotIn, Coordinates direction) {
        super(robotIn, direction);
    }

    @Override
    void act() {
        Cell toAct = getObjectif();
        if (toAct == null) {
            return;
        }
        toAct.moveOn(super.getRobotSource());
    }

    /**
     * 
     * @return the cell of objective
     */
    private Cell getObjectif() {
        if (super.getRobotSource() instanceof Tank) {
            Coordinates tmp = super.getRobotSource().getCoordinates()
                    .add(super.getDirection().times(2));
            if (super.getRobotSource().getMoving().contains(tmp)) {
                return super.getRobotSource().getBoard().getCell(tmp);
            } else {
                return null;
            }
        } else {
            Coordinates tmp = super.getRobotSource().getCoordinates()
                    .add(super.getDirection());
            if (super.getRobotSource().getMoving().contains(tmp)) {
                return super.getRobotSource().getBoard().getCell(tmp);
            } else {
                return null;
            }
        }
    }

}
