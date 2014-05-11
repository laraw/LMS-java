/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  The library class is the interface between the member and the
  libraries collections of holdings. The library class refers to both the 
  Library Collection object and the Member
  object and allows the member to interact the library collection object to
   borrow and return holdings. 
 
**/

package lms.model;

import java.util.*;

import lms.model.exception.*;

public class Library {
    
    private LibraryCollection collection;
    
    private Member member;
    
    
    public Library() {
    }
    
    
    // Adds a specified library collection to the library
    
    public void addCollection(LibraryCollection collection){
        this.collection = collection;
    }
    
    // Adds a specified holding to a collection
    
    public boolean addHolding(Holding holding) {
        return collection.addHolding(holding);
    }
    
    // Adds a member to the library
    
    public void addMember(Member member) {
        this.member = member;
    }
    
    // Calls the members borrow holding method
    
    public void borrowHolding (int holdingId) throws LMSException {
        member.borrowHolding(collection.getHolding(holdingId));
        
    }
    
    // Calls the members calculate remaining credit method
    
    public double calculateRemainingCredit() {
        return member.calculateRemainingCredit();
    }
    
    // Calculates the total late fees for a member based on their borrowing 
    // history.
    
    public double calculateTotalLateFees() {
        return member.getBorrowingHistory().calculateTotalLateFees();
    }
    
    // Returns all the current holdings in a library collection
    
    public Map<Integer, Holding> getAllHoldings () { 
        return collection.getAllHoldings();
        
    }
    // Returns all the members current holdings

    public ArrayList<Holding> getBorrowedHoldings () {
        return member.getCurrentHoldings();
    }
    
    // Returns the library collection
    
    public LibraryCollection getCollection() {
        return collection;
    }
    
    // Gets a specific history record for a member based on the holding ID
    
    public HistoryRecord getHistoryRecord(int holdingId) {
        return member.getBorrowingHistory().getHistoryRecord(holdingId);
    }
    
    // Gets a specified holding based on the holding ID
    
    public Holding getHolding(int code) {
        return collection.getHolding(code);
    }
    
    // Returns the Library member
    
    public Member getMember() {
        return member;
    }
    
    // Removes a holding from the library collection
    
    public boolean removeHolding (int code)  {
        return collection.removeHolding(code);
    }
    
    // Resets the members credit
    
    public void resetCredit() {
        member.resetCredit();
    }
    
    // Returns a holding 
    
    public void returnHolding(Holding holding) throws Exception {
        member.returnHolding(holding);
        
    }
    
    
}