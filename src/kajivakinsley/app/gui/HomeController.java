package kajivakinsley.app.gui;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kajivakinsley.app.DriverMainClass;
import kajivakinsley.app.OnUpdateImpl;

import java.util.UUID;

import static kajivakinsley.app.DriverMainClass.*;
import static kajivakinsley.app.Loader.RandomInt2;

public class HomeController implements OnUpdateImpl {
	@FXML
	public Label txtCleanStatus,txtBtterry,txtpoints,txtPointsLabel,txtSteps;

	@FXML
	public Button btnStartEnd;

	@FXML
	public ListView<String> listProgressor,listCleaned ;

	public static ObservableList <String> dirtAreasList = FXCollections.observableArrayList ();
	public static ObservableList <String> cleanedAreas = FXCollections.observableArrayList ();
	public static ObservableList <String> cleanedROOMS = FXCollections.observableArrayList ();
	//public static ObservableList <String> uncleanedROOMS = FXCollections.observableArrayList ();
	final int NUMBER_OF_ROOMS_TO_BE_CLEANED = 64;
	public static StringProperty txtPointsLabel_ = new SimpleStringProperty ("0 Points");
	int alllowwableCount = 0;
	//public  StringProperty txtSteps_ = new SimpleStringProperty ("0 Steps");
	public void initialize () {

		txtPointsLabel_.addListener ((observableValue , s , newVale) -> txtPointsLabel.setText (newVale));

		btnStartEnd.setOnAction (ev->{
			ObservableList <String> alllResults = FXCollections.observableArrayList ();

			if(btnStartEnd.getText ().equals ("Start")){
				txtCleanStatus.setText (" Cleaning");
				btnStartEnd.setText ("Stop");
				cleanedAreas.clear ();
				dirtAreasList.clear ();
				listCleaned.setItems (cleanedROOMS);
				BATTERY_CHARGE = 0 ;
				listProgressor.setItems (alllResults);
				txtBtterry.setText ((BATTERY_CHARGE * 100)+"%");

				for (int i = 1 ; i < NUMBER_OF_ROOMS_TO_BE_CLEANED+1 ; i++) {
					String roomID  =(i * 2 ) + "";
					cleanedAreas.clear ();
					dirtAreasList.clear ();
					RewardCleanedRoomPoints = 0;
					LOST_POINTS_TO_MOVEMENT = 0;
					STEPS_TAKEN = 0;
					try {
						Thread.sleep (300);
					} catch (InterruptedException e) {
						e.printStackTrace ();
					}
					new DriverMainClass ().cleanRoom (roomID,txtSteps);
					txtpoints.setText (" Points: " + RewardCleanedRoomPoints);
					alllResults.addAll (dirtAreasList);
					alllResults.addAll (cleanedAreas);
				//	alllResults.addAll (cleanedROOMS);
					listCleaned.setItems (cleanedROOMS);

					txtCleanStatus.setText ( (LOST_POINTS_TO_MOVEMENT - RewardCleanedRoomPoints) > 1000?"Failed" :  (LOST_POINTS_TO_MOVEMENT - RewardCleanedRoomPoints) < 1? "Trial Failed": "Done");
					txtPointsLabel.setText ( "Points  :" + (LOST_POINTS_TO_MOVEMENT - RewardCleanedRoomPoints) );

					listProgressor.setItems (alllResults);
					btnStartEnd.setText ("Start");
					txtSteps.setText ("Steps Taken " +STEPS_TAKEN);
					txtpoints.setText ("  " );
					// txtpoints.setText (" Points: " + RewardCleanedRoomPoints);

					txtBtterry.setText ((BATTERY_CHARGE * 100)+"%");
					if(BATTERY_CHARGE <RandomInt2(1,4) && alllowwableCount  > 3) {

						txtBtterry.setText ((0)+"%");
						txtCleanStatus.setText ("Low On Battery Returning to charge \nCleaned "+ i+ " Rooms");
						break;
					}else{
						//btnStartEnd.fire ();
						alllowwableCount++;
					}

				}
			//	new DriverMainClass ().cleanRoom ();


			}else{
				btnStartEnd.setText ("Start");
				alllResults.addAll (dirtAreasList);
				alllResults.addAll (cleanedAreas);
				listCleaned.setItems (cleanedROOMS);
				txtpoints.setText (" Points: " + RewardCleanedRoomPoints);
				txtBtterry.setText ((BATTERY_CHARGE * 100)+"%");



				listProgressor.setItems (alllResults);

				cleanedROOMS.clear ();
			}

			Task <Void> t2 = new Task <> () {
				@Override
				protected Void call () throws Exception {

					return  null;
				}
			};
			t2.cancel ();
			var t =new Thread (t2);
					t.start ();

			t2.setOnSucceeded (e -> {
			//
			});



		});




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
