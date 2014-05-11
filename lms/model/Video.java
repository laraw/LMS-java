/** 
Assignment 1, Programming 2, RMIT University. 
Author: Lara Wilkinson s3342496
Date: 04/02/2014

Description:  Videos are a type of holding within the library. Videos can be 
borrowed by library members but have variable standard loan fee and a
 shorter maximum loan period. 
**/

package lms.model;

import lms.model.util.DateUtil;

public class Video extends AbstractHolding {
    
    // For a video, the standard loan fee is a variable which is passed to the
	// constructor.  However, the max loan period will always remain the same. 

    int standardLoanFee;
    private static final int MAX_LOAN_PERIOD = 7;
    
    public Video() {
        
    }

    public Video(int code, String title) {
        super(code, title);
    }

    // Provides a constructor for the video that accepts the standard loan 
    // fee as a parameter
    
    public Video(int code, String title, int standardLoanFee) {
        super(code, title);
        this.standardLoanFee = standardLoanFee;
    }
    
    
    // Will return the default loan fee for the video
    
    @Override
    public double getDefaultLoanFee() {
        return standardLoanFee;
    }

    // Will return the default max loan period for the video
    
    @Override
    public int getMaxLoanPeriod() {
        return MAX_LOAN_PERIOD;
    }

    
    // Returns a string representation of the video object
    
    @Override
    public String toString() {
        return super.toString()+":"+standardLoanFee+":"+MAX_LOAN_PERIOD+":"+"VIDEO";
    }
    
    
    // A video has a late fee of 50% of the standard loan fee multiplied by the
    // number of days a video is late
    
    public double calculateLateFee() {    
        
        // The surcharge is 50% of the standard loan fee 
        double surcharge = standardLoanFee * 0.50;
        int latefee;
        
        // The date must be converted to string to pass to the utility method 
        // telling us the number of elapsed days a holding has 
        // been on loan
        
        String borrowDate = (super.getBorrowDate());
        int elapseddays = DateUtil.getInstance().getElapsedDays(borrowDate);
        
        // If the number of lapsed days is less than the max loan period, there
        // is no late fee.
        // However, if the number of days exceeds the max loan period, the late
        // fee is calculated based on the number of days late by the surcharge 
        
        if (elapseddays <= MAX_LOAN_PERIOD) {
            latefee = 0;
        }
        
        else {
            latefee = (elapseddays - MAX_LOAN_PERIOD) * (int)surcharge;
        }
        
        return latefee;
    }    

}
