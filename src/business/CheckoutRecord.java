package business;

public class CheckoutRecord {
	private CheckoutRecordEntry checkoutEntry;

	public CheckoutRecord(CheckoutRecordEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;

	}

	public CheckoutRecordEntry getCheckoutEntry() {
		return checkoutEntry;
	}

	public void setCheckoutEntry(CheckoutRecordEntry checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}

}
