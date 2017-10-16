package business;

import java.time.LocalDate;

public class CheckOutEntryExtend extends CheckoutRecordEntry {

	private LibraryMember member;
	public CheckOutEntryExtend(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookcopy,LibraryMember mem) {
		super(checkoutDate, dueDate, bookcopy, null);
		this.member= mem;
	}
	public LibraryMember getMember() {
		return member;
	}
	public void setMember(LibraryMember member) {
		this.member = member;
	}


}
