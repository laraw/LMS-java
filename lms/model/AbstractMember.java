/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson, s3342496
Date: 04/02/2014

Description:  The Abstract Member provides a blueprint for the properties and behaviours of all types of Library members.
**/

package lms.model;
import java.util.ArrayList;
import lms.model.exception.*;
import lms.model.util.DateUtil;

public abstract class AbstractMember implements Member {

     private String memberID;
     private String fullname;
     
     // A member has a borrowing history, which is a collection of holdings.
     
     private BorrowingHistory history = new BorrowingHistory();
     
     // A member also has a collection of currently borrowed holdings.
     
     private ArrayList<Holding> currentholdings = new ArrayList<Holding>();
     private int credit;
     private int maxcredit;
     
     public AbstractMember() {}     

     public AbstractMember(String memberID, String fullname, int credit,
    		 int maxcredit) {
          this.memberID = memberID;
          this.fullname = fullname;
          this.credit = credit;
          this.maxcredit = maxcredit;
     }
     
     // Accessors for the members attributes. 
     

     public int calculateRemainingCredit() {
               return credit;
     }

     public BorrowingHistory getBorrowingHistory() {
               return history;
     }

     public ArrayList<Holding> getCurrentHoldings() {
               return currentholdings;
     }
     
     public String getFullName() {
               return fullname;
     }

     public abstract int getMaxCredit();

     public String getMemberId() {
               return memberID;
     }

     /** The borrow holding method accepts any type of holding as a parameter. 
      * If a holding has been previously been loaned to a member, they are no 
      * longer able to borrow it. Additionally, a holding can only be borrowed
      *  by a member if the member has sufficient credit. **/
     
     public void borrowHolding(Holding holding) throws LMSException {     
               
          // First, check that the holding has not been borrowed before, if it
    	 // has, throw an exception
          if (history.getHistoryRecord(holding.getCode()) != null) {
               throw new MultipleBorrowingException("This holding has already"
               		+ " been borrowed and cannot be borrowed again");
          }
          
          // Check if the member has sufficient credit. If they do not, throw
          // an exception. 
          
          else if (this.credit < holding.getDefaultLoanFee()) {
               throw new InsufficientCreditException("Member does not have"
               		+ " sufficient credit");
          }
          
          
          /** Once a holding is borrowed, it should be set to on loan, and the
           *  members current list of holdings are updated. At the time of the
           *  transaction, the member will pay the standard loan fee **/
          
          else {
               holding.onLoan(true);
               currentholdings.add(holding);
               this.credit -= holding.getDefaultLoanFee();
               holding.setBorrowDate(DateUtil.getInstance().getDate());
          }     
     }
     
     // Resets the members credit to the initial maximum value. 
     
     public void resetCredit() {     
          credit = maxcredit;
     }
     
     // A placeholder for the return holding method, the implementation will 
     // depend on the type of member. 
     
     public abstract void returnHolding(Holding holding) throws LMSException;
     
     // Deducts a late fee from the members credit when a holding is returned by
     // the member. 
     
     public void deductLateFee (int latefee) {
          this.credit -= latefee;
     }
     
     
          // Generates a string representation of the Member
     
     public String toString() {
          return memberID+":"+fullname+":";
     }

}
