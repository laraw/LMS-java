/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description: After a member returns a holding, a history record is created. 
 A history record contains the  holding and the fee paid. 
**/


package lms.model;

public class HistoryRecord {

    private Holding holding;
    private int feepaid;

    
    public HistoryRecord(Holding holding, int feepaid) {
        this.holding = holding;
        this.feepaid = feepaid;
    }
    
    public Holding getHolding() {
        return holding;
    }
    
    public double getFeePayed() {
        return feepaid;
    }
    
    public String toString() {
        return holding.getCode()+":" + feepaid;
    }
    

}
