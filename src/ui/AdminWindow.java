package ui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import business.Book;
import business.LibraryMember;
import business.SystemController;

public class AdminWindow extends Application implements Initializable {

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
	
	@FXML
	private TableView<Book> adminBooksTable;
	@FXML
	private TableColumn<Book, String> adminBookISBN;
	@FXML
	private TableColumn<Book, String> adminBookTitle;
	@FXML
	private TableColumn<Book, String> adminBookAuthors;
	@FXML
	private TableColumn<Book, String> adminBookCkOutLen;
	@FXML
	private TableColumn<Book, String> adminBookNumCopy;
	

	protected static SystemController controller = new SystemController();

	Stage addStage = new Stage();

	final ObservableList<LibraryMember> memberData = FXCollections
			.observableArrayList(controller.allMembers());
	
//	final ObservableList<Book> bookData = FXCollections
//			.observableArrayList(controller.);
	
	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"AdminWindow.fxml"));
		Parent root = loader.load();
		root.getChildrenUnmodifiable();

		stage.setTitle("All Library Members");
		stage.setScene(new Scene(root));

		stage.show();
	}

	public void onAdminEditMemberBtn() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"AddEditMemberForm.fxml"));
		Parent addParent = loader.load();
		AddEditMemberWindow controller = loader.getController();
		controller.setAdminWindow(this);
		addParent.getChildrenUnmodifiable();
		Scene scene = new Scene(addParent);
		addStage.setScene(scene);
		addStage.showAndWait();

	}

	public static void main(String[] args) {

		launch(args);
	}

	public void libMemDetails() {
		adminMembersTable.getColumns().clear();
		adminLibMemIdCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"memberId"));
		adminLibMemNameCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"firstName"));
		adminLibMemTelephoneCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"telephone"));
		adminLibMemAddressCol
				.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>(
						"address"));
		adminMembersTable.setItems(memberData);

		adminMembersTable.getColumns().add(adminLibMemIdCol);
		adminMembersTable.getColumns().add(adminLibMemNameCol);
		adminMembersTable.getColumns().add(adminLibMemTelephoneCol);
		adminMembersTable.getColumns().add(adminLibMemAddressCol);

	}
	public void libBookDetails() {
		adminBooksTable.getColumns().clear();
		adminBookISBN
				.setCellValueFactory(new PropertyValueFactory<Book, String>(
						"isbn"));
		adminBookTitle
				.setCellValueFactory(new PropertyValueFactory<Book, String>(
						"title"));
		adminBookAuthors
				.setCellValueFactory(new PropertyValueFactory<Book, String>(
						"authors"));
		adminBookCkOutLen
				.setCellValueFactory(new PropertyValueFactory<Book, String>(
						"maxCheckoutLength"));
		adminBookNumCopy
		.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"copies"));
//		adminBooksTable.setItems(data);

		adminBooksTable.getColumns().add(adminBookISBN);
		adminBooksTable.getColumns().add(adminBookTitle);
		adminBooksTable.getColumns().add(adminBookAuthors);
		adminBooksTable.getColumns().add(adminBookCkOutLen);
		adminBooksTable.getColumns().add(adminBookNumCopy);

	}

	public void addMemberForTable(LibraryMember member) {
		System.out.println("added member" + member);
		ListIterator<LibraryMember> li = memberData.listIterator(0);
		boolean found = false;
		while (li.hasNext()) {
			if (li.next().getMemberId().equals(member.getMemberId())) {
				li.set(member);
				System.out.println("LIKIIIIIII " + li.nextIndex());
				found = true;
				break;
			}
		}

		if (!found) {
			memberData.add(member);
		}
	}

	public void editMemberForTable(LibraryMember member) {
	}

	public void closeMemberForm() {
		addStage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		libMemDetails();
	}
	
	@FXML
	public void onAdminOpenBookFormBtn() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"AddEditBookForm.fxml"));
		Parent addParent = loader.load();
		AddEditBookWindow controller = loader.getController();
		controller.setAdminWindow(this);
		addParent.getChildrenUnmodifiable();
		Scene scene = new Scene(addParent);
		addStage.setScene(scene);
		addStage.showAndWait();
	}
	
	public void openAddAuthorWindow(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"AddEditMemberForm.fxml"));
		Parent addParent = loader.load();
		AddEditMemberWindow controller = loader.getController();
		controller.setAdminWindow(this);
		addParent.getChildrenUnmodifiable();
		Scene scene = new Scene(addParent);
		addStage.setScene(scene);
		addStage.showAndWait();
	}
	
}
