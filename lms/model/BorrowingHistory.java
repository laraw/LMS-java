/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  The borrowing history class stores a collection of a members history records. A history record contains the 
 holding and the fee paid. 
**/

package lms.model;

import java.util.*;

public class BorrowingHistory {

    private List<HistoryRecord> records = new ArrayList<HistoryRecord>();
        
    public BorrowingHistory() {
        
    }
    
    // Add a new history record to the members history record. 
    
    public void addHistoryRecord(HistoryRecord record) {
        records.add(record);
    }
    
    /** To determine the total late fees a member has paid, first get the 
     * standard loan fee for a holding and subtract from
         the total fees paid**/
    
    public int calculateTotalLateFees() {
    
        int totallatefees = 0;
        
        // Get all the history records and calculate the total fee paid, 
        //then subtract the default loan fee to determine the
        // total late fees paid.
        
        for (int i = 0; i < records.size(); i++) {
            totallatefees += (records.get(i).getFeePayed() -
            		records.get(i).getHolding().getDefaultLoanFee());
        }
        
        return totallatefees;
    }
    

    // Provides the ability to search for a specific history record. 
    
    public HistoryRecord getHistoryRecord(int holdingId) {    

        // Search for a specified holding in the members history records 
    	//based on the Holding ID 
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getHolding().getCode() == holdingId) {
                 return records.get(i);
            }
        }
        
        return null;
    }
    
    // Returns the full list of history records for a member. 
    
    public List<HistoryRecord> getAllHistoryRecords() {
        return records;
    }
    
    

}
