package ui;

import java.util.HashMap;

import business.LibraryMember;
import business.SystemController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddEditMemberWindow {

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

	AdminWindow adminWindow;

	public void setAdminWindow(AdminWindow adminWindow) {
		this.adminWindow = adminWindow;
	}

	protected static SystemController controller = new SystemController();

	@FXML
	public void onAdminSaveBtn() {
		LibraryMember mem = controller.addMember(this);
		adminWindow.addMemberForTable(mem);
		adminWindow.closeMemberForm();
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

	@FXML
	public void onAdminSearchMember() {

		LibraryMember lmem = controller.getMember(adminsearchText.getText());
		if (lmem != null) {
			adminMemMsg.setText("");
			newMemId.setText(lmem.getMemberId());
			newMemId.setDisable(true);
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
			newMemId.setDisable(false);
			fname.setText("");
			lname.setText("");
			tel.setText("");
			street.setText("");
			city.setText("");
			state.setText("");
			zip.setText("");
		}
	}

}
