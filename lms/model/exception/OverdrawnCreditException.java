/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  Overdrawn credit exception is thrown when a member does not have sufficient credit to borrow a holding.
**/

package lms.model.exception;

@SuppressWarnings("serial")
public class OverdrawnCreditException extends LMSException {

	public OverdrawnCreditException() {
		super("Default OverdrawnCreditException Message");
	}
	
	public OverdrawnCreditException(String message) {
		super(message);
		
	}

}
