package ui;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import business.LibraryMember;
import business.SystemController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.ReadOnlyStringWrapper;

public class AdminWindow extends Application implements Initializable {

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
	@FXML
	private TextField adminsearchText;
	@FXML
	private Text adminMemMsg;
	@FXML
	private GridPane adminEditMemberForm;
	@FXML
	private TableView<LibraryMember> adminMembersTable;

	@FXML
	private TableColumn<LibraryMember, String> adminLibMemIdCol;
	@FXML
	private TableColumn<LibraryMember, String> adminLibMemNameCol;
	@FXML
	private TableColumn<LibraryMember, String> adminLibMemTelephoneCol;
	@FXML
	private TableColumn<LibraryMember, String> adminLibMemAddressCol;

	protected static SystemController controller = new SystemController();

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AdminWindow.fxml"));
		stage.setTitle("All Library Members");
		stage.setScene(new Scene(root));

		stage.show();
	}

	public void onAdminEditMemberBtn() throws Exception {
		Stage addStage = new Stage();
		Parent addParent = FXMLLoader.load(getClass().getResource("AddEditMemberForm.fxml"));
		 addParent.getChildrenUnmodifiable();
		Scene scene = new Scene(addParent);
		addStage.setScene(scene);
		addStage.show();
	}

	public static void main(String[] args) {
	
		launch(args);
	}

	public void onAdminSaveBtn() {
		System.out.println("Btn has clicked : " + newMemId.getText());
		controller.addMember(this);
	}

	public HashMap<String, String> getMemberData() {
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("newMemId", newMemId.getText());
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

	public void onAdminSearchMember() {

		LibraryMember lmem = controller.getMember(adminsearchText.getText());
		if (lmem != null) {
			adminMemMsg.setText("");
			newMemId.setText(lmem.getMemberId());
			fname.setText(lmem.getFirstName());
			lname.setText(lmem.getLastName());
			tel.setText(lmem.getTelephone());
			street.setText(lmem.getAddress().getStreet());
			city.setText(lmem.getAddress().getCity());
			state.setText(lmem.getAddress().getState());
			zip.setText(lmem.getAddress().getZip());
		} else {
			adminMemMsg.setText("Library member with this id is not found");
			adminMemMsg.setFill(Color.RED);
			newMemId.setText("");
			fname.setText("");
			lname.setText("");
			tel.setText("");
			street.setText("");
			city.setText("");
			state.setText("");
			zip.setText("");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final ObservableList<LibraryMember> data = FXCollections.observableArrayList(
			    controller.allMembers()
			);
	adminLibMemIdCol.setCellValueFactory(
		    new PropertyValueFactory<LibraryMember,String>("memberId")
		);
	adminLibMemNameCol.setCellValueFactory(
		    new PropertyValueFactory<LibraryMember,String>("firstName")
		);
	adminLibMemTelephoneCol.setCellValueFactory(
		    new PropertyValueFactory<LibraryMember,String>("telephone")
		);
	adminLibMemAddressCol.setCellValueFactory(
		    new PropertyValueFactory<LibraryMember,String>("address")
		);
	adminMembersTable.setItems(data);
	adminMembersTable.getColumns().add(adminLibMemIdCol);
	adminMembersTable.getColumns().add(adminLibMemNameCol);
	adminMembersTable.getColumns().add(adminLibMemTelephoneCol);
	adminMembersTable.getColumns().add(adminLibMemAddressCol);
	
	}
}
