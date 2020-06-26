package kajivakinsley.app;

import java.awt.*;

public class RoomBaMachineLoocationPosition extends Point{

	private Dimensions dimensions = null;
	public RoomBaMachineLoocationPosition (int x, int y, Dimensions dimensions) {
		super(x, y);
		this.dimensions = dimensions;
	}


	/**
	 * Copy constructor
	 *
	 * @param roomBaMachineLoocationPosition  Another position
	 */
	public RoomBaMachineLoocationPosition (RoomBaMachineLoocationPosition roomBaMachineLoocationPosition) {
		super(roomBaMachineLoocationPosition);
	}
	/**
	 * Constructor using Point
	 *
	 * @param point       The position
	 * @param dimensions  The dimensions of the map
	 */
	public RoomBaMachineLoocationPosition (Point point, Dimensions dimensions) {
		this(point.x, point.y, dimensions);
	}



	public boolean isValid() {
		boolean isValid = false;

		if (dimensions != null &&
				x >= 0 && x < dimensions.x &&
				y>= 0 && y < dimensions.y) {
			return true;
		}
		return isValid;
	}}
