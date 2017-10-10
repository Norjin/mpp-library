package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();

	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@FXML TextField isbn;
	@FXML TextField memId;
	@FXML TableView<LibraryMember> checkedOutBooks;
	ObservableList<LibraryMember> data = FXCollections.observableArrayList();
	@FXML TableColumn<LibraryMember, String> coulmnMemId;

	@FXML public void checkoutBooks(ActionEvent event){
		DataAccess da = new DataAccessFacade();
		List<String> memIds = new ArrayList<>();
		memIds.addAll(da.readMemberMap().keySet());
		List<String> isbns = new ArrayList<>();

		HashMap<String, Book>  checkedBooks = da.readBooksMap();
		isbns.addAll(checkedBooks.keySet());
		//checkedBooks.values();
		coulmnMemId.setCellValueFactory(new PropertyValueFactory<LibraryMember, String>("memberId"));

		data.add(new LibraryMember("1", "", "", "", null));
		/*data.add("2");
		data.add("3");
		data.add("4");*/
		checkedOutBooks.setItems(null);
		checkedOutBooks.setItems(data);
		//checkedOutBooks.setCellFactory(studentListView -> new StudentListViewCell());
		if(memIds.contains(memId) && isbns.contains(isbn)){

		}
		/*DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();*/
		;
		//return retval;
	}

}
