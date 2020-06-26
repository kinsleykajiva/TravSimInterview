package kajivakinsley.app.gui;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainUI extends Application {

	public static void main (String[] args) {
		launch (args);
	}


	//
	@Override
	public void start (Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
		primaryStage.setTitle("TravSim Roomba App");
		primaryStage.setScene(new Scene(root, 300 * 3, 275 * 2));
		primaryStage.setMaximized(false);
		primaryStage.show();
	}
}
