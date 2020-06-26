package kajivakinsley.app;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;

import java.util.UUID;

import static kajivakinsley.app.gui.HomeController.*;

public class DriverMainClass implements OnUpdateImpl {
public static int RewardCleanedRoomPoints =0;
/**This is full charge*/
public static double BATTERY_CHARGE = 0;
public static int LOST_POINTS_TO_MOVEMENT = 0;
public static int STEPS_TAKEN = 0 ;

	public static void main(String[] args) {
	//	new DriverMainClass ().cleanRoom (UUID.randomUUID ().toString ());
	}
		// write your code here
		public void cleanRoom (String roomID, Label txtSteps_ ) {

		Loader loader = new Loader ();
		//	loader.dirtyAreas (30);
			//System.exit (0);
			loader.loadInits ();
		LocationsOfDirtRoombaMap locationsOfDirtRoombaMap = loader.getLocationsOfDirtRoombaMap ();
		RoomBaMachineLoocationPosition roomBaMachineLoocationPosition = loader.getRoomBaMachineLoocationPosition ();
		DrivingInstructions drivingInstructions = loader.getDrivingInstructions();

		// Validate relevant entities

		// Initialise our robot

			Solution solution;
			RoombaBotSystem roombaBotSystem = new RoombaBotSystem (locationsOfDirtRoombaMap , roomBaMachineLoocationPosition,this);
			solution = roombaBotSystem.clean(drivingInstructions,txtSteps_);
		/*	System.out.println (cleanedAreas.size () + " dirtAreasList Size");
			System.out.println (dirtAreasList.size () + " dirtAreasListSize");*/
			if(cleanedAreas.size () == dirtAreasList.size ()){
				cleanedROOMS.addAll ("Cleaned Room ID of "+roomID);
				RewardCleanedRoomPoints += 250;
				System.out.println (RewardCleanedRoomPoints + " RewardCleanedRoomPoints Size");
				System.out.println (cleanedAreas.size () + " dirtAreasList Size");
				System.out.println (dirtAreasList.size () + " dirtAreasListSize");
			}


	}

	@Override
	public void onDoneCleaning (String message) {

	}

	@Override
	public void onFoundDirt (String message) {

	}

	@Override
	public void onMoving (String messasge) {

	}

	@Override
	public void onDoneWithRoom (String message) {

	}
}
