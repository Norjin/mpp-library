package ui;

import java.util.HashMap;

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
	private TextField newMemId;
	
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
		System.out.println("Btn has clicked sad : " + newMemId.getText() );
		controller.addMember(this);
	}
	
	public HashMap<String, String> getMemberData() {
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("memId", newMemId.getText());
		obj.put("fname", fname.getText());
		obj.put("lname", lname.getText());
		obj.put("tel", tel.getText());
		return obj;
	}
	
	public HashMap<String, String> getAddressData() {
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("street", street.getText());
		obj.put("city", city.getText());
		obj.put("state", state.getText());
		obj.put("zip", zip.getText());
		return obj;
	}
	
}
