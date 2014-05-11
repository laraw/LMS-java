/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  The holding interface provides a list of method signatures 
 that must be implemented by the holding classes that implement it
 
**/
package lms.model;
 
public interface Holding {
    
    // The borrow date is the date the library member borrows the holding
    
    public String getBorrowDate();
    
    // The holding code is an identifier for the holding
    
    public int getCode();
    
    // The default loan fee is the loan fee for a holding and will vary
    // depending on the type of holding
    
    public double getDefaultLoanFee();
    
    // The max loan period is the maximum period a holding can be borrowed 
    // before a late fee is incurred
    
    public int getMaxLoanPeriod();
    
    // Returns the holding title
    
    public String getTitle();
    
    // Will set a holding to be on loan
    
    public void onLoan(boolean onLoan);
    
    // Will return true if the holding is on loan
    
    public boolean isOnLoan();
    
    // Sets the date a holding is borrowed
    
    public void setBorrowDate(String borrowDate);
    
    // Returns a string representation of the holding
    
    public String toString();
    
    // Based on business rules, will determine the correct late fee to be 
    // charged for a holding
    
    public double calculateLateFee();
    
}
