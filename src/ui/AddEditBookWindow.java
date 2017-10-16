package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddEditBookWindow {
	
	@FXML
	private TextField bookIsbn;
	@FXML
	private TextField bookTitle;
	@FXML
	private TextField bookAuthors;
	@FXML
	private TextField bookCkOutLen;
	@FXML
	private TextField bookSearchText;
	@FXML
	private ListView bookAuthorListView;
	
	@FXML
	private TextField authorFName;
	@FXML
	private TextField authorLName;
	@FXML
	private TextField authorStreet;
	@FXML
	private TextField authorCity;
	@FXML
	private TextField authorState;
	@FXML
	private TextField authorZip;
	
	
	AdminWindow adminWindow;
	public void setAdminWindow(AdminWindow adminWindow) {
		this.adminWindow = adminWindow;
	}
	
	@FXML
	public void onAdminEditBookBtn() {
		System.out.println("Admin edit book btn");
	}
	@FXML
	public void onAdminSaveBookBtn() {
		
	}
	
	@FXML
	public void onAdminSearchMember() {
		
	}
	
	@FXML
	public void onAdminAddAuthorWindow() {
		
	}
	
	@FXML
	public void adminAddAuthorBtn() {
		
	}
}
