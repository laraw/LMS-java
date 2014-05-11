/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  The library class is the interface between the member and the
  libraries collections of holdings. The library class refers to both the 
  Library Collection object and the Member object and allows the member
 to interact the library collection object to borrow and return holdings. 
 
**/

package lms.model.facade;

import java.util.Iterator;

import lms.model.Book;
import lms.model.HistoryRecord;
import lms.model.Holding;
import lms.model.Library;
import lms.model.LibraryCollection;
import lms.model.Member;
import lms.model.Video;
import lms.model.exception.*;
import lms.model.util.DateUtil;

/**
 * @author Mikhail Perepletchikov
 */
public class LMSFacade implements LMSModel {

    // The library object provides the interface between the libraries objects
	// (the members, collections and holdings)
    // and the rest of the program
    
    private Library newlibrary = new Library();
    
    public LMSFacade() {        
    }
    

    /**
     * Adds new member to library
     */
    public void addMember(Member member) {
        newlibrary.addMember(member);
    };

    /**
     * Adds new collection to library
     */
    public void addCollection(LibraryCollection collection) {
        newlibrary.addCollection(collection);
    }

    /**
     * Returns the (current) member
     */
    public Member getMember() {
        return newlibrary.getMember();
    };

    /**
     * Returns the (current) library collection
     */
    public LibraryCollection getCollection() {
        return newlibrary.getCollection();
    };

    /**
     * Adds new holding to library collection. Returns false if the holding
     * already exists (i.e. only one copy of a given holding can be added to the
     * collection).
     */
    public boolean addHolding(Holding holding) {
        return newlibrary.addHolding(holding);
    };

    /**
     * Removes a holding from library collection. Returns false if the holding
     * is currently on loan and therefore cannot be removed.
     */
    public boolean removeHolding(int holdingId) {
        return newlibrary.getCollection().removeHolding(holdingId);
    };

    /**
     * Retrieves a holding according to the provided holdingId
     */
    public Holding getHolding(int holdingId) {
        if (newlibrary.getCollection().getHolding(holdingId) != null) {
            return newlibrary.getHolding(holdingId);
        }
        
        else {
            return null;
        }
    };

    /**
     * Returns all holdings held in the library collection
     */
    public Holding[] getAllHoldings() {
        if (newlibrary.getAllHoldings().size() != 0) {
            return newlibrary.getAllHoldings().values().toArray(new 
                    Holding[newlibrary.getAllHoldings().size()]);
            
        }
        else {
            return null;
        }
    };
    
    /**
     * Returns the count of Books in the library collection. NOTE: you are allowed
     *  to use the instanceof operator to achieve this
     */
    public int countBooks() {
        int bookcount = 0;
        
        Iterator iter = newlibrary.getAllHoldings().values().iterator();
        
        while (iter.hasNext()) {
            if(iter.next() instanceof Book) {
                bookcount++;
            }
        }
        
        return bookcount;
        
    };  
        
    /**
     * Returns the count of Videos in the library collection. NOTE: you are allowed
     *  to use the instanceof operator to achieve this
     */
    public int countVideos() {
        int videocount = 0;
        
        Iterator iter = newlibrary.getAllHoldings().values().iterator();
        
        while (iter.hasNext()) {
            if(iter.next() instanceof Video) {
                videocount++;
            }
        }
        
        return videocount;
    }
    
    /**
     * Allows a member to borrow a given holding. Deducts loan fee from member's
     * borrowing credit. Throws exceptions when the borrowing eligibility is
     * violated (refer to the assignment specs for further details).
     * @throws Exception 
     */
    public void borrowHolding(int holdingId) throws LMSException {
        newlibrary.borrowHolding(holdingId);
    };

    /**
     * Allows a member to return a given holding. Deducts late fee, if
     * applicable, from member's borrowing credit. Throws exception if the
     * (Standard) member's borrowing credit is overdrawn (refer to the
     * assignment specs for further details).
     * @throws Exception 
     */
    public void returnHolding(int holdingId) throws Exception {
        newlibrary.returnHolding(newlibrary.getHolding(holdingId));
    };

    /**
     * Returns a borrowing history (collection of all history records) of a
     * member
     */
    public HistoryRecord[] getBorrowingHistory() {
        
        HistoryRecord[] records = new HistoryRecord[newlibrary.getMember().getBorrowingHistory().getAllHistoryRecords().size()];
        records = newlibrary.getMember().getBorrowingHistory().getAllHistoryRecords().toArray(records);    
        
        if (records.length != 0) {
            return records;
        }
        
        else {
            return null;
        }
    };

    /**
     * Returns a specific history record from the borrowing history
     */
    public HistoryRecord getHistoryRecord(int holdingId) {
        return newlibrary.getHistoryRecord(holdingId);
    };
    
    /**
     * Returns a collection of all currently borrowed holdings
     */
    public Holding[] getBorrowedHoldings() {
        Holding[] holdings = newlibrary.getBorrowedHoldings().toArray(new Holding[newlibrary.getBorrowedHoldings().size()]);
        if (holdings.length != 0) {
            return holdings;
        }
        
        else {
            return null;
        }
    };

    /**
     * Resets member's credit to the original MAX credit value
     */
    public void resetMemberCredit() {
        newlibrary.getMember().resetCredit();
    };

    /**
     * Returns the remaining credit ($ value) for the member
     */
    public int calculateRemainingCredit() {
        return newlibrary.getMember().calculateRemainingCredit();
    };

    /**
     * Returns the total late fees ($ value) accumulated by the member as
     * captured in the borrowing history
     */
    public int calculateTotalLateFees() {
        return newlibrary.getMember().getBorrowingHistory().calculateTotalLateFees();
    };

    
    // this shows an example of using the provided DateUtil class to set the 
    // current date
    public void setDate(String currentDate) {
        DateUtil.getInstance().setDate(currentDate);
    }
}