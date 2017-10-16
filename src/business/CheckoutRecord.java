package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable {
	private static final long serialVersionUID = 623004515166605827L;

	private List<CheckoutRecordEntry> checkoutEntry = new ArrayList<CheckoutRecordEntry>();

	public CheckoutRecord() {
//		this.checkoutEntry =new ArrayList<CheckoutRecordEntry>();

	}

	public List<CheckoutRecordEntry> getCheckoutEntry() {
		return checkoutEntry;
	}

	public void setCheckoutEntry(List<CheckoutRecordEntry> checkoutEntry) {
		this.checkoutEntry = checkoutEntry;
	}

}
