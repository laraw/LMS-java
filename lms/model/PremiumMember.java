/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson, s3342496
Date: 04/02/2014

Description: Premium members have a higher initial maximum credit and can 
return holdings even when they do not have sufficient credit to pay a late fee. 

**/


package lms.model;

import java.util.ArrayList;



public class PremiumMember extends AbstractMember {

    
    private static final int PREMIUM_MAX_CREDIT = 45;
    ArrayList<Holding> currentholdings = super.getCurrentHoldings();
    
    public PremiumMember() {
        
    }

    public PremiumMember(String premiumMemberId, String premiumMemberName) {
        super(premiumMemberId, premiumMemberName, PREMIUM_MAX_CREDIT, 
        		PREMIUM_MAX_CREDIT);
        
    }

    public int getMaxCredit() {
        return PREMIUM_MAX_CREDIT;
    };

    // When a holding is returned, the holding is added to the members 
    // history record and the member pays the late fee
    
    public void returnHolding(Holding holding) {
        
        // First calculate & deduct any late fees from the members account
        
        super.deductLateFee((int)(holding.calculateLateFee()));
        
        // Remove the current holding from the collection of current member holdings
        
        super.getCurrentHoldings().remove(holding);
        
        // Create a new history record and add it to the members history
        
        HistoryRecord histrecord = new HistoryRecord(holding, 
        		(int)(holding.getDefaultLoanFee() + holding.calculateLateFee()));
        super.getBorrowingHistory().addHistoryRecord(histrecord);
        
        // Finally, set the holding to no longer be on loan
        
        holding.onLoan(false);    
    }    
    
    // Generate a string representation of the premium member 
    
    public String toString() {
        return super.getMemberId()+":"+super.getFullName()+":"
                 +super.calculateRemainingCredit() +":"+"PREMIUM";
    }

}
