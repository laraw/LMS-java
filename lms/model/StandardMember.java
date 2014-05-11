/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson, s3342496
Date: 04/02/2014

Description:  The Standard Member has a max credit value of $30 and is 
not able to return a book if they cannot pay the late fee.
**/

package lms.model;


import lms.model.exception.*;


public class StandardMember extends AbstractMember {

    private static final int STD_MAX_CREDIT = 30;
    
    
    public StandardMember() {
    }
    
    
    public StandardMember(String standardMemberId, String standardMemberName) {
        super(standardMemberId, standardMemberName, STD_MAX_CREDIT, STD_MAX_CREDIT);
    }
    
    public int getMaxCredit() {
        return STD_MAX_CREDIT;
    };

    
    
    public void returnHolding(Holding holding) throws OverdrawnCreditException {
        if(super.calculateRemainingCredit() < holding.calculateLateFee()) {
            throw new OverdrawnCreditException("Unable to return as "
            		+ "credit would become overdrawn");
        }
        
        else {
            
            // First calculate & deduct any late fees from the members account
            super.deductLateFee((int)(holding.calculateLateFee()));
            
            // Remove the current holding from the collection of current member
            // holdings
            super.getCurrentHoldings().remove(holding);
            
            // Create a new history record and add it to the members history
            
            HistoryRecord histrecord = new HistoryRecord(holding, 
            		(int)(holding.getDefaultLoanFee() + holding.calculateLateFee()));
            super.getBorrowingHistory().addHistoryRecord(histrecord);
            
            // Finally, set the holding to no longer be on loan
            holding.onLoan(false);
        }
    }
    
    public String toString() {
        return super.getMemberId()+":"+super.getFullName()+":"+
                       super.calculateRemainingCredit()+":"+"STANDARD";
    }


}
