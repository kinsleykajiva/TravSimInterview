module TravSimInterview {

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires kotlin.stdlib;
	requires java.datatransfer;
	requires java.desktop;


	exports kajivakinsley.app.gui to javafx.graphics ,javafx.fxml ;
	opens kajivakinsley.app to javafx.fxml, java.desktop, java.datatransfer;
	opens kajivakinsley.app.gui to javafx.fxml, java.desktop, java.datatransfer;
	//rea


}