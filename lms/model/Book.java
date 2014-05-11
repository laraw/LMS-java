/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson s3342496
Date: 04/02/2014

Description:  Books are a type of holding within the library. Videos can be 
borrowed by library members and have a fixed standard loan fee. 
**/

package lms.model;
import lms.model.util.*;


public class Book extends AbstractHolding {

    // For a book, the max loan period, standard loan fee and late fee are 
	// values that do not change
    
    private static final int MAX_LOAN_PERIOD = 28;
    private static final int STANDARD_LOAN_FEE = 10;
    private static final int LATE_FEE = 2;
    
    public Book() {
        
    }
    
    // Creates a new book holding and calls the constructor of the super class

    public Book(int code, String title) {
        super(code, title);
    }
    
    // Returns the standard loan fee for the book
    
    public double getDefaultLoanFee() {
        return STANDARD_LOAN_FEE;
    }
    
    // Returns the maximum loan period for a book

    public int getMaxLoanPeriod() {
        return MAX_LOAN_PERIOD;
    }

        
    // To calculate a books late fee, the number of days late is calculated
    //by getting the difference between the borrow date, the max loan period
    // and the current date. Books have a daily fixed rate of $2
    
    public double calculateLateFee() {
        double latefee = 0.00;
        
        // First calculate the number of days between the current date and the
        //borrow date
    
        int elapseddays = DateUtil.getInstance().getElapsedDays
        		(super.getBorrowDate());
        
        // If the number of elapsed days is less than the loan period, there 
        //will be no late fee,
        // if the book is late, the loan fee is calculated based on the number 
        //of days late by the late fee
        
        
        if (elapseddays <= MAX_LOAN_PERIOD) {
            latefee = 0.00;
        }
        
        else {
            latefee = (elapseddays - MAX_LOAN_PERIOD) * LATE_FEE;
        }
        
        return latefee;
    }
    
    
    
    // Returns a string representation of the book 
    
    public String toString() {
        return super.toString()+":"+STANDARD_LOAN_FEE+":"+MAX_LOAN_PERIOD+":"+
                                   "BOOK";
    }

}                    
