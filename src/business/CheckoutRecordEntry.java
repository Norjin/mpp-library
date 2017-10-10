package business;

import java.time.LocalDate;

public class CheckoutRecordEntry {
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookcopy;

	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookcopy) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookcopy = bookcopy;
	}

	public BookCopy getBookCopy() {
		return bookcopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		dueDate=LocalDate.now().plusDays(getBookCopy().getBook().getMaxCheckoutLength());
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	
}
