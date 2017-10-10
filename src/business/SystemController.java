package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ui.AddEditMemberWindow;
import ui.AdminWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import business.Book;
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
	private DataAccess da = new DataAccessFacade();
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
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


	 public LibraryMember addMember(AddEditMemberWindow aw) {
		HashMap<String, String> memData = aw.getMemberData();
		HashMap<String, String> addrData = aw.getAddressData();
		Address addr = new Address(addrData.get("street"), addrData.get("city"), addrData.get("state"), addrData.get("zip"));
		LibraryMember lmem = new LibraryMember(memData.get("newMemId"), memData.get("fname"), memData.get("lname"), memData.get("tel"), addr);
		DataAccess da = new DataAccessFacade();
		da.saveLibraryMember(lmem.getMemberId(), lmem);
		return lmem;
	}

	public LibraryMember getMember(String name) {
		return da.getMember(name);
	}

	public List<LibraryMember> allMembers() {
		List<LibraryMember> retval = new ArrayList<LibraryMember>();
		retval.addAll(da.readMemberMap().values());
		return retval;
	}

	public void saveBook(Book book) {
		da.saveBook(book);
	}
	public List<Book> allBooks() {
		List<Book> bookList = new ArrayList<Book>();
		bookList.addAll(da.readBooksMap().values());
		return bookList;
	}
	public Book getBook(String isbn) {
		return da.getBook(isbn);
	}

}
