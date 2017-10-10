package ui;

import business.SystemController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminWindow extends Application {
	
	@FXML
	private TextField memId;
	@FXML
	private TextField fname;
	@FXML
	private TextField lname;
	@FXML
	private TextField street;
	@FXML
	private TextField city;
	@FXML
	private TextField state;
	@FXML
	private TextField zip;
	@FXML
	private TextField tel;
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AdminWindow.fxml"));
		System.out.println(root);
		stage.setTitle("FXML Welcome");
		stage.setScene(new Scene(root));
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
		
	}
	

	public void onAdminSaveBtn() {
		SystemController controller = new SystemController();
		System.out.println("Btn has clicked sad : " + memId.getText() );
		controller.addMember();
	}
	
	public Object getMemberData() {
		return city;
		
	}
	
}
