/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  Within the library, there are various types of holdings which can 
 be borrowed by Library members.  The abstract holding class provides a 
 blueprint for the attributes and behaviours of those holdings.
**/

package lms.model;

public abstract class AbstractHolding implements Holding {

	private int code;
	private String title;
	private String borrowDate;
	public boolean isOnLoan = false;
	
	// Constructs an abstract holding with no properties
	
	public AbstractHolding() {

	}
	
	// Constructs the holding and passes it the holding code and
	//title of the holding
	
	public AbstractHolding(int code, String title) {
		this.code = code;
		this.title = title;
	}

	// Returns the holdings code & title
	
		@Override
		public int getCode() {
			return code;
		}
		
		@Override
		public String getTitle() {
			return title;
		}


	// When a holding is borrowed, the date the holding was borrowed is set
		
	@Override
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	
	// Returns the date that a holding was borrowed, this is used to calculate
	// late fees if applicable
	
	@Override
	public String getBorrowDate() {
		return borrowDate;
	}

	
	
	// The default loan fee that a member pays for a holding depends on the 
	//type of holding, this is implemented in the subclass
	
	@Override
	public abstract double getDefaultLoanFee();

	
	// Each type of holding has a maximum period that it can be loaned to 
	//a member
	
	@Override
	public abstract int getMaxLoanPeriod();


	
	// This allows us to find out if a holding is on loan to ensure that it 
	//cannot be borrowed twice
	
	@Override
	public boolean isOnLoan() {
		return isOnLoan;
	}

	// This allows us to flag a holding as being on loan to ensure that it 
	// cannot be borrowed twice

	public void onLoan(boolean isOnLoan) {
		this.isOnLoan = isOnLoan;
	}
	
	

	// Returns a string representation of the holding
		
	public String toString() {
		return code + ":" + title;
	};

}
