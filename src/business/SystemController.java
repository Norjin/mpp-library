package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ui.AdminWindow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import business.Book;
import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private DataAccess da = new DataAccessFacade();

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
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
	
	@FXML public void addMember(AdminWindow aw) {
		HashMap<String, String> memData = aw.getMemberData();
		HashMap<String, String> addrData = aw.getAddressData();
		Address addr = new Address(addrData.get("street"), addrData.get("city"), addrData.get("state"), addrData.get("zip"));
		LibraryMember lmem = new LibraryMember(memData.get("newMemId"), memData.get("fname"), memData.get("lname"), memData.get("tel"), addr);
		DataAccess da = new DataAccessFacade();
		da.saveLibraryMember(lmem.getMemberId(), lmem);
	}
	
	public LibraryMember getMember(String name) {
		return da.getMember(name);
	}

}
