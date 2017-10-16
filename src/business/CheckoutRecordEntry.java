package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {
	private static final long serialVersionUID = 1946470062324009553L;

	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookcopy;
	private LocalDate returnDate;

	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookcopy, LocalDate returnDate) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookcopy = bookcopy;
		this.returnDate = returnDate;
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

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "CheckoutRecordEntry [returnDate=" + returnDate + "]";
	}


}
