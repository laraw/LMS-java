/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson, s3342496
Date: 04/02/2014

Description:  The member interface enforces the use of methods relating to member interactions, such as calculating
credit, resetting credit, getting a list of current holdings and a members borrowing history. 
**/

package lms.model;

import java.util.ArrayList;

public interface Member extends Borrower {
    
    public int calculateRemainingCredit();
    
    public BorrowingHistory getBorrowingHistory();
    
    public ArrayList<Holding> getCurrentHoldings();
    
    public String getFullName();
    
    public int getMaxCredit();
    
    public String getMemberId();
    
    public void resetCredit();

}
