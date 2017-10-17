package business;

import java.time.LocalDate;
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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private DataAccess da = new DataAccessFacade();
	@FXML
	Text memIdErrMsg;
	@FXML
	Text isbnErrMsg;
	@FXML
	Text memIdErrMsg1;
	@FXML
	Text isbnErrMsg1;
	@FXML
	Text isbnErrMsg2;
	@FXML
	Text isbnErrMsg3;
	@FXML
	TextField isbn;
	@FXML
	TextField isbn3;
	@FXML
	TextField isbn1;
	@FXML
	TextField isbn2;
	@FXML
	TextField memId;
	@FXML
	TextField memId1;
	@FXML
	TextField memId2;

	@FXML
	TableView<CheckoutRecordEntry> checkedOutBooks = new TableView<>();
	ObservableList<CheckoutRecordEntry> data = FXCollections.observableArrayList();
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnMemId;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnISBN;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnCheckOutDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnDueDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> colBookTitle;

	@FXML
	TableColumn<CheckoutRecordEntry, String> retColMemId;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retColISBN;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retColCheckOutDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retColDueDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retColBookTitle;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retCopyNumId;
	@FXML
	TableColumn<CheckoutRecordEntry, String> retColRetDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnDueDate3;
	@FXML
	Button checkBookCopyId;
	@FXML
	ComboBox<BookCopy> copyNumComboBoxId;
	@FXML
	TableColumn<CheckoutRecordEntry, String> copyNumId;
	@FXML
	Button createEntryId;

	@FXML
	Button checkSatusBtn;
	@FXML
	Button bookDetToRet;
	@FXML
	Button retBookDetBtn;
	@FXML
	Text copyNumText;

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

	@FXML
	TableView<CheckoutRecordEntry> status = new TableView<>();
	@FXML
	TableView<CheckOutEntryExtend> status1 = new TableView<>();

	@FXML
	TableView<CheckoutRecordEntry> retCheckedOutBooks/* = new TableView<>() */;

	@FXML
	TableColumn<CheckOutEntryExtend, String> columnMemId1;

	@FXML
	TableColumn<CheckoutRecordEntry, String> columnISBN1;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnISBN2;

	@FXML
	TableColumn<CheckoutRecordEntry, String> columnDueDate1;
	@FXML
	TableColumn<CheckoutRecordEntry, String> colRetAction;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnDueDate2;
	@FXML
	TableColumn<CheckoutRecordEntry, String> copyNumComboBox;
	@FXML
	TableColumn<CheckoutRecordEntry, String> colRetDate;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnCopyNumber;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnCopyNumber1;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnCopyNumber2;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnTitle;
	@FXML
	TableColumn<CheckoutRecordEntry, String> colBookTitle1;
	@FXML
	TableColumn<CheckoutRecordEntry, String> colBookTitle2;

	@FXML
	TableColumn<CheckoutRecordEntry, String> columnStatus2;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnStatus;
	@FXML
	TableColumn<CheckoutRecordEntry, String> columnStatus1 = new TableColumn<>();

	boolean checkStatusFlag = false;
	@FXML
	Text memIdLable;
	@FXML public void checkOutBooksPane(){
		isbnErrMsg.setText("");
		if(memId != null)
			memId.visibleProperty().set(true);
		membersMap = da.readMemberMap();
		memlist = new ArrayList<LibraryMember>(membersMap.values());
		List<CheckoutRecordEntry> newMemList = new ArrayList<>();
		String tx = memId != null ? memId.getText() : "";
		libMem = membersMap.get(tx);
		if(libMem != null) {
		for(CheckoutRecordEntry entry:libMem.getCheckoutRecord().getCheckoutEntry()){
			if(entry.getBookCopy().getBook().getIsbn().equals(isbn.getText())) {
				newMemList.add(entry);
			}
		}
		ObservableList<CheckoutRecordEntry> data = FXCollections.observableArrayList(newMemList);
		checkedOutBooks.getItems().clear();
		checkedOutBooks.setItems(data);
		copyNumId.setCellValueFactory(d -> {
			Integer rowValue = d.getValue().getBookCopy().getCopyNum();
			System.out.println("row val-->" + rowValue.toString());
			return new ReadOnlyStringWrapper(rowValue.toString());
        });
		columnMemId.setCellValueFactory(d -> {
			return new ReadOnlyStringWrapper(memId.getText());
        });
		colBookTitle.setCellValueFactory(d -> {
			Book rowValue = d.getValue().getBookCopy().getBook();
			String cellValue = rowValue.getTitle();
			return new ReadOnlyStringWrapper(cellValue);
        });
		columnISBN.setCellValueFactory(d -> {
			Book rowValue = d.getValue().getBookCopy().getBook();
			String cellValue = rowValue.getIsbn();
			return new ReadOnlyStringWrapper(cellValue);
        });
		columnCheckOutDate.setCellValueFactory(d -> {
			LocalDate rowValue = d.getValue().getCheckoutDate();
			return new ReadOnlyStringWrapper(rowValue.toString());
        });
		columnDueDate.setCellValueFactory(d -> {
			LocalDate rowValue = d.getValue().getDueDate();
			return new ReadOnlyStringWrapper(rowValue.toString());
        });
		}
 	}

	LibraryMember libMem;
	CheckoutRecordEntry entry = null;
	List<Book> bookList = null;
	HashMap<String, LibraryMember> membersMap;
	HashMap<String, Book>  checkedBooksMap;
	List<LibraryMember> memlist;
	@FXML public void createCheckOut(){
		 membersMap = da.readMemberMap();
		isbnErrMsg.setText("");
		memIdErrMsg.setText("");
		DataAccess da = new DataAccessFacade();
		Book b = null;
		checkedBooksMap = da.readBooksMap();
			b = checkedBooksMap.get(isbn.getText());
			libMem = membersMap.get(memId.getText());
			if(copyNumComboBoxId.getValue()==null){
				isbnErrMsg.setText("Book copy is not available");
				return;
			}
			b.getCopies()[copyNumComboBoxId.getValue().getCopyNum()-1].changeAvailability();
			entry = new CheckoutRecordEntry(LocalDate.now(), LocalDate.now(), new BookCopy(b, copyNumComboBoxId.getValue().getCopyNum()), null);
			libMem.getCheckoutRecord().getCheckoutEntry().add(entry);
			libMem.getCheckoutRecord().getCheckoutEntry().get(copyNumComboBoxId.getValue().getCopyNum()-1).getBookCopy().changeAvailability();
			membersMap.put(memId.getText(), libMem);
			memlist = new ArrayList<LibraryMember>(membersMap.values());
			DataAccessFacade.loadMemberMap(memlist);
			membersMap = da.readMemberMap();
			checkedBooksMap.put(isbn.getText(), b);
			bookList = new ArrayList<Book>(checkedBooksMap.values());
			DataAccessFacade.loadBookMap(bookList);
			checkedBooksMap = da.readBooksMap();
			if(!checkBookAvailabilty()){
				ObservableList<CheckoutRecordEntry> data = FXCollections.observableArrayList(libMem.getCheckoutRecord().getCheckoutEntry());
				checkedOutBooks.getItems().clear();
				checkedOutBooks.setItems(data);
				copyNumId.setCellValueFactory(d -> {
					Integer rowValue = d.getValue().getBookCopy().getCopyNum();
					System.out.println("row val-->"+rowValue.toString());
					return new ReadOnlyStringWrapper(rowValue.toString());
		        });
				columnMemId.setCellValueFactory(d -> {
					return new ReadOnlyStringWrapper(memId.getText());
		        });
				colBookTitle.setCellValueFactory(d -> {
					Book rowValue = d.getValue().getBookCopy().getBook();
					String cellValue = rowValue.getTitle();
					return new ReadOnlyStringWrapper(cellValue);
		        });
				columnISBN.setCellValueFactory(d -> {
					Book rowValue = d.getValue().getBookCopy().getBook();
					String cellValue = rowValue.getIsbn();
					return new ReadOnlyStringWrapper(cellValue);
		        });
				columnCheckOutDate.setCellValueFactory(d -> {
					LocalDate rowValue = d.getValue().getCheckoutDate();
					return new ReadOnlyStringWrapper(rowValue.toString());
		        });
				columnDueDate.setCellValueFactory(d -> {
					LocalDate rowValue = d.getValue().getDueDate();
					return new ReadOnlyStringWrapper(rowValue.toString());
		        });
			}
		}




	@FXML
	public boolean checkBookAvailabilty() {
		HashMap<String, Book> checkedBooksMap = da.readBooksMap();
		Book b = da.readBooksMap().get(isbn.getText());
		List<String> isbnList = new ArrayList<>();
		isbnList.addAll(checkedBooksMap.keySet());
		int valErrCount = 0;
		if (isbn.getText().length() == 0 || !isbnList.contains(isbn.getText())) {
			isbnErrMsg.setText("Please a valid ISBN");
			valErrCount++;
		}
		List<BookCopy> availBookCopies = new ArrayList<>();
		List<String> memIdList = new ArrayList<>();
		memIdList.addAll(da.readMemberMap().keySet());
		if (memId.getText().length() == 0 || !memIdList.contains(memId.getText())) {
			if (!checkStatusFlag) {
				memIdErrMsg.setText("Please a valid Member ID");
				valErrCount++;
			}
		}
		if (valErrCount > 0) {
			return true;
		} else {
			b = da.readBooksMap().get(isbn.getText());
			for (BookCopy obj : b.getCopies()) {
				if (obj.isAvailable()) {
					availBookCopies.add(obj);
				}
			}
		}
		ObservableList<BookCopy> copyNumData = new SimpleListProperty<>(FXCollections.observableList(availBookCopies));
		copyNumComboBoxId.setItems(copyNumData);
		copyNumComboBoxId.getSelectionModel().selectFirst();
		copyNumComboBoxId.setCellFactory(new Callback<ListView<BookCopy>, ListCell<BookCopy>>() {
			@Override
			public ListCell<BookCopy> call(ListView<BookCopy> param) {
				return new ListCell<BookCopy>() {
					@Override
					public void updateItem(BookCopy item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							setText(Integer.toString(item.getCopyNum()));
							setGraphic(null);
						} else {
							setText(null);
						}
					}
				};
			}
		});
		return false;
	}

	@FXML
	public void overDueBook() {
		DataAccess da = new DataAccessFacade();
		Book b = null;
		HashMap<String, Book> checkedBooksMap = da.readBooksMap();
		CheckOutEntryExtend entry = null;
		b = checkedBooksMap.get(isbn3.getText());
		List<String> isbnList = new ArrayList<>();
		isbnList.addAll(checkedBooksMap.keySet());
		ObservableList<CheckOutEntryExtend> data = FXCollections.observableArrayList();
		if (isbn3.getText().length() == 0 || !isbnList.contains(isbn3.getText())) {
			isbnErrMsg3.setText("Please a valid ISBN");
			return;
		}else {
			HashMap<String, LibraryMember> mMap = da.readMemberMap();
			List<LibraryMember> mlist = new ArrayList<>(mMap.values());
			DataAccessFacade.loadMemberMap(mlist);
			HashMap<String, LibraryMember> memeberMap = da.readMemberMap();
			List<LibraryMember> memberlist = new ArrayList<>(memeberMap.values());
			for (LibraryMember m : memberlist) {
				for (CheckoutRecordEntry cr : m.getCheckoutRecord().getCheckoutEntry()) {
					System.out.println(cr.getBookCopy().isAvailable());
					System.out.println(cr.getBookCopy().getBook().getIsbn().toString()+cr.getBookCopy().getCopyNum());
					System.out.println(isbn3.getText());
					System.out.println(m.getMemberId().toString());
					if (isbn3.getText().equals(cr.getBookCopy().getBook().getIsbn())==true && cr.getBookCopy().isAvailable()==false){
						System.out.println(cr.getDueDate().compareTo(LocalDate.now()));
					entry = new CheckOutEntryExtend(LocalDate.now(), LocalDate.now().plusDays(b.getMaxCheckoutLength()),
							new BookCopy(b, cr.getBookCopy().getCopyNum(), cr.getBookCopy().isAvailable()), m);
					if (entry.getDueDate().compareTo(LocalDate.now()) < 0) {
							data.add(entry);
						}
					}
				}
			}
		}

		status1.getItems().clear();
		status1.setItems(data);
		colBookTitle2.setCellValueFactory(d -> {
			Book rowValue = d.getValue().getBookCopy().getBook();
			String cellValue = rowValue.getTitle();
			return new ReadOnlyStringWrapper(cellValue);
		});
		columnISBN2.setCellValueFactory(d -> {
			Book rowValue = d.getValue().getBookCopy().getBook();
			String cellValue = rowValue.getIsbn();
			return new ReadOnlyStringWrapper(cellValue);
		});
		columnCopyNumber2.setCellValueFactory(d -> {
			int rowValue = d.getValue().getBookCopy().getCopyNum();
			return new ReadOnlyStringWrapper(String.valueOf(rowValue));
		});
		columnDueDate2.setCellValueFactory(d -> {
			LocalDate rowValue = d.getValue().getDueDate();
			return new ReadOnlyStringWrapper(rowValue.toString());
		});
		columnStatus2.setCellValueFactory(d -> {
			boolean rowValue = d.getValue().getBookCopy().isAvailable();
			System.out.println(rowValue);
			return new ReadOnlyStringWrapper(String.valueOf(rowValue));
		});
		columnMemId1.setCellValueFactory(d -> {
			LibraryMember rowValue = d.getValue().getMember();
			String cellValue = rowValue.getMemberId();
			return new ReadOnlyStringWrapper(cellValue);
		});
	}

	@FXML
	public void returnBookAction() {
		displayReturnBook();
	}
	@FXML
	public void bookDetToRet() {
		displayReturnBook();
	}
	Book b = null;
	public void displayReturnBook(){
		retCheckedOutBooks.getItems().clear();
		checkStatusFlag = true;
		DataAccess da = new DataAccessFacade();
		List<String> memIds = new ArrayList<>();
		memIds.addAll(da.readBooksMap().keySet());
		List<String> isbnList = new ArrayList<>();
		checkedBooksMap = da.readBooksMap();
		isbnList.addAll(checkedBooksMap.keySet());
		if (isbn1.getText().length()==0) {
			isbnErrMsg.setText("Please a ISBN");
			return;
		} else {
			b = da.readBooksMap().get(isbn1.getText());
			membersMap = da.readMemberMap();
			libMem = membersMap.get(memId1.getText());
			ObservableList<CheckoutRecordEntry> data = FXCollections.observableArrayList();
			data.addAll(libMem.getCheckoutRecord().getCheckoutEntry());
			colRetAction.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
	        Callback<TableColumn<CheckoutRecordEntry, String>, TableCell<CheckoutRecordEntry, String>> cellFactory
	                =  new Callback<TableColumn<CheckoutRecordEntry, String>, TableCell<CheckoutRecordEntry, String>>() {
	            @Override
	            public TableCell<CheckoutRecordEntry, String> call(final TableColumn<CheckoutRecordEntry, String> param) {
	                final TableCell<CheckoutRecordEntry, String> cell = new TableCell<CheckoutRecordEntry, String>() {
	                    final Button btn = new Button("Return Book");
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	int dataIndex = this.getTableRow().getIndex();
	                            btn.setOnAction(event -> {

	                            	CheckoutRecordEntry selectedItem = data.get(dataIndex);
	                            	System.out.println("b======>"+b);

	                            	selectedItem.setReturnDate(LocalDate.now());
	                            	System.out.println("selected item" + selectedItem);
	                            	libMem.getCheckoutRecord().getCheckoutEntry().remove(dataIndex);
	                            	membersMap.put(memId1.getText(), libMem);
	                    			List<LibraryMember> memlist = new ArrayList<LibraryMember>(membersMap.values());//copyNumComboBoxId.getSelectionModel().getSelectedIndex()
	                    			b.getBookCopy().getBook().getCopies()[dataIndex].changeAvailability();
	                    			checkedBooksMap.put(isbn1.getText(), b);
	                    			bookList = new ArrayList<Book>(checkedBooksMap.values());
	                    			selectedItem.getBookCopy().changeAvailability();
	                    			data.set(dataIndex, selectedItem);
	                    			 DataAccessFacade.loadMemberMap(memlist);
	                    			 membersMap = da.readMemberMap();
	                    			 retCheckedOutBooks.getItems().remove(selectedItem);
	                    			 DataAccessFacade.loadBookMap(bookList);
	                    			checkedBooksMap = da.readBooksMap();
	                            });
	                            setGraphic(btn);
	                            setText(null);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };
	        colRetAction.setCellFactory(cellFactory);
			retCheckedOutBooks.setItems(data);
			retColMemId.setCellValueFactory(d -> {
				return new ReadOnlyStringWrapper(isbn1.getText());
			});
			retColBookTitle.setCellValueFactory(d -> {
				Book rowValue = d.getValue().getBookCopy().getBook();
				String cellValue = rowValue.getTitle();
				System.out.println(cellValue);
				return new ReadOnlyStringWrapper(cellValue);
			});
			retColISBN.setCellValueFactory(d -> {
				Book rowValue = d.getValue().getBookCopy().getBook();
				String cellValue = rowValue.getIsbn();
				return new ReadOnlyStringWrapper(cellValue);
			});
			retColCheckOutDate.setCellValueFactory(d -> {
				LocalDate rowValue = d.getValue().getDueDate();
				return new ReadOnlyStringWrapper(rowValue.toString());
			});
			retCopyNumId.setCellValueFactory(d -> {
				int rowValue = d.getValue().getBookCopy().getCopyNum();
				return new ReadOnlyStringWrapper(String.valueOf(rowValue));
			});
			retColDueDate.setCellValueFactory(d -> {
				LocalDate rowValue = d.getValue().getDueDate();
				return new ReadOnlyStringWrapper(rowValue.toString());
			});
		}
	}


	@FXML public void renewBook(){
		renewBookTable.getItems().clear();
		membersMap = da.readMemberMap();
		libMem = membersMap.get(memId2.getText());
		data.addAll(libMem.getCheckoutRecord().getCheckoutEntry());
		displayRenewBooks();
	}
	@FXML TableView<CheckoutRecordEntry> renewBookTable;
	@FXML TableColumn<CheckoutRecordEntry, String> renewColMemId;
	@FXML TableColumn<CheckoutRecordEntry, String> renewColBTitel;
	@FXML TableColumn<CheckoutRecordEntry, String> renewColIsbn;
	@FXML TableColumn<CheckoutRecordEntry, String> renewColCopyId;
	@FXML TableColumn<CheckoutRecordEntry, String> renewCheckOutDate;
	@FXML TableColumn<CheckoutRecordEntry, String> renewColDueDate;
	@FXML TableColumn<CheckoutRecordEntry, String> renewActionBtn;
	public void displayRenewBooks(){
		data = FXCollections.observableArrayList();
		DataAccess da = new DataAccessFacade();
		List<String> memIds = new ArrayList<>();
		memIds.addAll(da.readBooksMap().keySet());
		List<String> isbnList = new ArrayList<>();
		checkedBooksMap = da.readBooksMap();
		isbnList.addAll(checkedBooksMap.keySet());
		if (isbn2.getText().length()==0) {
			isbnErrMsg.setText("Please a ISBN");
			return;
		} else {
			renewActionBtn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
	        Callback<TableColumn<CheckoutRecordEntry, String>, TableCell<CheckoutRecordEntry, String>> cellFactory
	                =  new Callback<TableColumn<CheckoutRecordEntry, String>, TableCell<CheckoutRecordEntry, String>>() {
	            @Override
	            public TableCell<CheckoutRecordEntry, String> call(final TableColumn<CheckoutRecordEntry, String> param) {
	                final TableCell<CheckoutRecordEntry, String> cell = new TableCell<CheckoutRecordEntry, String>() {
	                    final Button btn = new Button("Renew");
	                    @Override
	                    public void updateItem(String item, boolean empty) {
	                        super.updateItem(item, empty);
	                        if (empty) {
	                            setGraphic(null);
	                            setText(null);
	                        } else {
	                        	int dataIndex = this.getTableRow().getIndex();
	                        	System.out.println("dataIndex------>"+dataIndex);
	                            btn.setOnAction(event -> {
	                            	CheckoutRecordEntry selectedItem = data.get(dataIndex);
	                            	selectedItem.setDueDate(selectedItem.getDueDate().plusDays(selectedItem.getBookCopy().getBook().getMaxCheckoutLength()));
	                    			libMem.getCheckoutRecord().getCheckoutEntry().get(dataIndex).setDueDate(selectedItem.getDueDate().plusDays(selectedItem.getBookCopy().getBook().getMaxCheckoutLength()));
	                            	membersMap.put(memId2.getText(), libMem);
	                    			List<LibraryMember> memlist = new ArrayList<LibraryMember>(membersMap.values());
	                    			DataAccessFacade.loadMemberMap(memlist);
	                    			membersMap = da.readMemberMap();
	                    			libMem = membersMap.get(memId2.getText());
	                    			 data.addAll(libMem.getCheckoutRecord().getCheckoutEntry());
	                    			 displayRenewBooks();
	                            });
	                            setGraphic(btn);
	                            setText(null);
	                        }
	                    }
	                };
	                return cell;
	            }
	        };
	        renewActionBtn.setCellFactory(cellFactory);
	        renewBookTable.getItems().clear();
	        membersMap = da.readMemberMap();
			b = da.readBooksMap().get(isbn2.getText());
			libMem = membersMap.get(memId2.getText());
			data.addAll(libMem.getCheckoutRecord().getCheckoutEntry());
	        renewBookTable.setItems(data);
	        System.out.println("data ---->"+data);
	        renewColMemId.setCellValueFactory(d -> {
				Book rowValue = d.getValue().getBookCopy().getBook();
				String cellValue = rowValue.getTitle();
				return new ReadOnlyStringWrapper(cellValue);
			});
	        renewColBTitel.setCellValueFactory(d -> {
				Book rowValue = d.getValue().getBookCopy().getBook();
				String cellValue = rowValue.getTitle();
				System.out.println(cellValue);
				return new ReadOnlyStringWrapper(cellValue);
			});
	        renewColIsbn.setCellValueFactory(d -> {
				Book rowValue = d.getValue().getBookCopy().getBook();
				String cellValue = rowValue.getIsbn();
				return new ReadOnlyStringWrapper(cellValue);
			});
	        renewCheckOutDate.setCellValueFactory(d -> {
				LocalDate rowValue = d.getValue().getCheckoutDate();
				return new ReadOnlyStringWrapper(rowValue.toString());
			});
			renewColCopyId.setCellValueFactory(d -> {
				int rowValue = d.getValue().getBookCopy().getCopyNum();
				return new ReadOnlyStringWrapper(String.valueOf(rowValue));
			});
			renewColDueDate.setCellValueFactory(d -> {
				LocalDate rowValue = d.getValue().getDueDate();
				System.out.println("labda--->"+rowValue);
				return new ReadOnlyStringWrapper(rowValue.toString());
			});
		}
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
