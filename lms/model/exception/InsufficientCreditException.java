/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  Insufficient credit exception is thrown when a member does not have sufficient credit to borrow a holding.
**/

package lms.model.exception;

@SuppressWarnings("serial")
public class InsufficientCreditException extends LMSException {

	public InsufficientCreditException() {
		super("Default InsufficientCreditException Message");
	}

	public InsufficientCreditException(String message) {
		super(message);
	}

}
