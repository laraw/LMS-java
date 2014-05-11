/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson, s3342496
Date: 04/02/2014

Description:  The Borrower interface enforces the use of methods relating to borrowing interactions for a Library member.
**/

package lms.model;
import lms.model.exception.*;

public interface Borrower {
    public void borrowHolding(Holding holding) throws LMSException;
    public void returnHolding(Holding holding) throws LMSException;
}
