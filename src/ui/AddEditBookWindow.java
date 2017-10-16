package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
	
	@FXML
	private TextField authorTel;
	
	@FXML
	private TextArea authorBio;
	
	List<Author> authors = new ArrayList<Author>();
	
	Book newBook;
	
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
	public void onAdminSearchBook() {
		
	}
	
	@FXML
	public void onAdminAddAuthorWindow() throws IOException {
		adminWindow.openAddAuthorWindow();
	}
	
	@FXML
	public void adminAddAuthorBtn() {
		HashMap<String, String> obj = new HashMap<String, String>();
		obj.put("fname", authorFName.getText());
		obj.put("lname", authorLName.getText());
		obj.put("street", authorStreet.getText());
		obj.put("city", authorCity.getText());
		obj.put("state", authorState.getText());
		obj.put("zip", authorZip.getText());
		
		
		Address addr = new Address(authorStreet.getText(),authorCity.getText(),authorState.getText(), authorZip.getText());
		Author au = new Author(authorFName.getText(), authorLName.getText(), authorTel.getText(), addr, authorBio.getText());
		authors.add(au);
		adminWindow.closeAuthorWindow();
	}
}
