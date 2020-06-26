package kajivakinsley.app;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

import java.util.ListIterator;

import static kajivakinsley.app.DriverMainClass.BATTERY_CHARGE;
import static kajivakinsley.app.DriverMainClass.LOST_POINTS_TO_MOVEMENT;


public class RoombaBotSystem {
	private OnUpdateImpl onUpdate;
	private LocationsOfDirtRoombaMap locationsOfDirtRoombaMap = null;
	private RoomBaMachineLoocationPosition roomBaMachineLoocationPosition = null;
	private int dirtsCleaned = 0;
	private int movementsCounter = 0 ;
	RoomBaMachineLoocationPosition previousRoomBaMachineLoocationPosition = null;

	/**
	 * Constructor
	 *
	 * @param locationsOfDirtRoombaMap       Robot is aware of the whole map.
	 * @param roomBaMachineLoocationPosition  Initial position of the robot
	 */
	public RoombaBotSystem (LocationsOfDirtRoombaMap locationsOfDirtRoombaMap , RoomBaMachineLoocationPosition roomBaMachineLoocationPosition,OnUpdateImpl onUpdate) {
		this.locationsOfDirtRoombaMap = locationsOfDirtRoombaMap;
		this.onUpdate = onUpdate;
		this.roomBaMachineLoocationPosition = roomBaMachineLoocationPosition;
		previousRoomBaMachineLoocationPosition = new RoomBaMachineLoocationPosition (roomBaMachineLoocationPosition);
	}

	/**
	 * Makes the robot move to a direction
	 *
	 * @param direction  Direction to move towards
	 */
	private void move(char direction) {

		previousRoomBaMachineLoocationPosition.setLocation(roomBaMachineLoocationPosition);
		//looking for the location to move to
		onUpdate.onMoving ("Moving to the " + direction + " Of Tile");

		switch (direction) {
			case 'N':
				roomBaMachineLoocationPosition.y++;
				break;
			case 'S':
				roomBaMachineLoocationPosition.y--;
				break;
			case 'E':
				roomBaMachineLoocationPosition.x++;
				break;
			case 'W':
				roomBaMachineLoocationPosition.x--;
				break;
			default:
				break;
		}

		// If a position is not in the map, the robot stays in the same place
		if (roomBaMachineLoocationPosition.isValid()) {
			LOST_POINTS_TO_MOVEMENT -= 10;
			doClean(roomBaMachineLoocationPosition);
		} else {
			LOST_POINTS_TO_MOVEMENT -= 10;
			roomBaMachineLoocationPosition.setLocation(previousRoomBaMachineLoocationPosition);
		}

	}

	/**
	 * Goes through the driving instructions and checks for dirts to clean.
	 *
	 * @param  drivingInstructions  Our instructions
	 * @return Solution             The solution of our game
	 */
	public Solution clean(DrivingInstructions drivingInstructions, Label txtSteps_) {

		Solution solution = null;

		// Check if initial position needs cleaning
		if (roomBaMachineLoocationPosition.isValid()) {
			//

			doClean(roomBaMachineLoocationPosition);
			txtSteps_.setText (movementsCounter + " Steps");
		}

		for (ListIterator <Character> iter = drivingInstructions.listIterator() ;
		     iter.hasNext(); ) {
			/*try {
				Thread.sleep (2_000);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}*/
			Character direction = iter.next();

			move(direction);


		}

		solution = new Solution(roomBaMachineLoocationPosition , dirtsCleaned);

		return solution;
	}


	/**
	 * Cleans dirt on a specific position
	 *
	 * @param roomBaMachineLoocationPosition  Position with potential dirt to be cleaned
	 */
	private void doClean(RoomBaMachineLoocationPosition roomBaMachineLoocationPosition) {
		movementsCounter++;
		LOST_POINTS_TO_MOVEMENT -= 10;
		if (locationsOfDirtRoombaMap.hasDirt(roomBaMachineLoocationPosition)) {
			BATTERY_CHARGE = BATTERY_CHARGE + 0.001;
			dirtsCleaned ++;
		}
	}
}
