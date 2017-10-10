package business;

import java.io.Serializable;
import java.time.LocalDate;



import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord;
	

		super(fname,lname, tel, add);
		this.checkoutRecord=checkoutRecord;

	}


	public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
		super(fname, lname, tel, add);
		this.memberId = memberId;
	}


	public String getMemberId() {
		return memberId;
	}


	public CheckoutRecord getChckoutRecord(){
		return checkoutRecord;
	}
	
	@Override
	public String toString() {
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
