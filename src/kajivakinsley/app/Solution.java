package kajivakinsley.app;

public class Solution {

	private RoomBaMachineLoocationPosition roomBaMachineLoocationPosition = null;
	private int dirtsCleaned = 0;

	/**
	 * Solution constructor.
	 *
	 * @param roomBaMachineLoocationPosition      Final position of the robot
	 * @param dirtsCleaned  Dirts that have been cleaned
	 */
	public Solution(RoomBaMachineLoocationPosition roomBaMachineLoocationPosition , int dirtsCleaned) {
		this.roomBaMachineLoocationPosition = roomBaMachineLoocationPosition;
		this.dirtsCleaned = dirtsCleaned;
	}

	/**
	 * Checks that our solution matches another solution. Used in unit testing.
	 */
	public boolean equals(Object object){
		boolean isEqual = false;

		if (object != null && object instanceof Solution) {
			Solution other = (Solution)object;
			isEqual = roomBaMachineLoocationPosition.equals(other.getRoomBaMachineLoocationPosition ()) &&
					dirtsCleaned == other.getDirtsCleaned();
		}
		return isEqual;
	}

	/**
	 * Getter for the position
	 * @return Position
	 */
	public RoomBaMachineLoocationPosition getRoomBaMachineLoocationPosition () {
		return roomBaMachineLoocationPosition;
	}

	/**
	 * Getter for the position
	 * @return Position
	 */
	public int getDirtsCleaned() {
		return dirtsCleaned;
	}
}
