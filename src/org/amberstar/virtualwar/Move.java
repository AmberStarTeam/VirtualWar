package org.amberstar.virtualwar;

public class Move extends Action {

	public Move(Robot robotIn, Coordinates direction) {
		super(robotIn, direction);
	}

	@Override
	void act() {
		Cell toAct = getObjectif();
		if (toAct == null) {
			return;
		}
	}
	
	private Cell getObjectif(){
		if (super.getRobotSource() instanceof Tank) {
			Coordinates tmp = super.getRobotSource().getCoordinates().add(super.getDirection().times(2));
			if (super.getRobotSource().getMoving().contains(tmp)) {
				return super.getRobotSource().getBoard().getCell(tmp);
			}else{
				return null;
			}
		}else{
			Coordinates tmp = super.getRobotSource().getCoordinates().add(super.getDirection());
			if (super.getRobotSource().getMoving().contains(tmp)) {
				return super.getRobotSource().getBoard().getCell(tmp);
			}else{
				return null;
			}
		}
	}

}
