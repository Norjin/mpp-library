package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BookCopyStatus extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Status.fxml"));
		System.out.println(root);
		primaryStage.setTitle("FXML Welcome");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		//unitcol.setCellValueFactory(new PropertyValueFactory<Person,String>("UnitName"));

	
		
	}
    TableView<Integer> table= new TableView<>();

    
	public static void main(String[] args) {
		launch(args);
	}
}
