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
        } else if (toAct.mineContains() != 0) {
            super.getRobotSource().hasBeenMined();
            toAct.setMine(0);
        }

        Robot rob = super.getRobotSource();

        if (rob.getBoard().setRobot(rob, toAct.getCoordinates())) {
            rob.setEnergy(rob.getEnergy() - rob.getCostMoving());
        }
    }

    /**
     * 
     * @return the cell of objective
     */
    private Cell getObjectif() {
        Coordinates tmp;
        Cell cellOfTmp;

        if (super.getRobotSource() instanceof Tank) {
            tmp = super.getRobotSource().getCoordinates()
                    .add(super.getDirection().times(2));
            if (!super.getRobotSource().getBoard().isValid(tmp)
                    || super.getRobotSource().getBoard().isObstacle(tmp)
                    || super.getRobotSource().getBoard().getCell(tmp)
                            .mineContains() == super.getRobotSource().getTeam()
                    || (super.getRobotSource().getBoard().isBase(tmp) != 0 && super
                            .getRobotSource().getBoard().isBase(tmp) != super
                            .getRobotSource().getTeam())) {
                tmp = tmp.minus(super.getDirection());
            }
        } else {
            tmp = super.getRobotSource().getCoordinates()
                    .add(super.getDirection());
        }

        cellOfTmp = super.getRobotSource().getBoard().getCell(tmp);

        if (cellOfTmp == null) {
            return null;
        }
        if (cellOfTmp.isObstacle()
                || cellOfTmp.mineContains() == super.getRobotSource().getTeam()
                || (super.getRobotSource().getBoard().isBase(tmp) != 0 && super
                        .getRobotSource().getBoard().isBase(tmp) != super
                        .getRobotSource().getTeam())) {
            return null;
        }

        return cellOfTmp;
    }
}
