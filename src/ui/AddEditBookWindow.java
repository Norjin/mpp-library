package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import business.Address;
import business.Author;
import business.Book;
import business.LibraryMember;
import business.SystemController;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddEditBookWindow implements Initializable {

	@FXML
	private TextField bookIsbn;
	@FXML
	private TextField bookTitle;
	@FXML
	private TextField bookAuthors;
//	@FXML
//	private TextField bookSearchText;
	@FXML
	private TextField bookNumCopies;
	@FXML
	private Text bookAuthorList;
	@FXML
	private Text bookMsg;

	@FXML
	private ComboBox bookCkOutLen;

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

	protected static SystemController controller = new SystemController();

	Book newBook;

	AdminWindow adminWindow;

	public void setAdminWindow(AdminWindow adminWindow) {
		this.adminWindow = adminWindow;
	}

	@FXML
	public void onAdminSaveBookBtn() {
		if(bookIsbn.getText().equals("") || bookTitle.getText().equals("")) {
			bookMsg.setText("Enter values ");
		}
		else {

		newBook = new Book(bookIsbn.getText(), bookTitle.getText(),
				Integer.parseInt(bookCkOutLen.getValue().toString()), adminWindow.getAuthors());
		for (int i = 1; i < Integer.parseInt(bookNumCopies.getText()); i++) {
			newBook.addCopy();
		}
		controller.saveBook(newBook);
		adminWindow.addBookForTable(newBook);
		adminWindow.closeCookForm();
		}
	}

	@FXML
	public void onAdminAddAuthorWindow() throws IOException {
		adminWindow.openAddAuthorWindow();
	}

	@FXML
	public void adminAddAuthorBtn() {
		Address addr = new Address(authorStreet.getText(), authorCity.getText(), authorState.getText(),
				authorZip.getText());
		Author au = new Author(authorFName.getText(), authorLName.getText(), authorTel.getText(), addr,
				authorBio.getText());
		authors.add(au);
		adminWindow.holdAuthors(au);
		System.out.println("new author " + au);
		adminWindow.closeAuthorWindow();
	}

	public String toAuthorListString(List<Author> authorList) {
		String aOut = " ";
		Iterator<Author> alist = authorList.iterator();
		while (alist.hasNext()) {
			aOut += alist.next().toString() + "\n";
		}
		return aOut;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (bookCkOutLen != null) {

			bookCkOutLen.getItems().clear();
			bookCkOutLen.getItems().addAll("7", "21");

			bookCkOutLen.setValue("7");
		}
	}

}
