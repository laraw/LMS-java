/** 
 Assignment 1, Programming 2, RMIT University. 
 Author: Lara Wilkinson s3342496
 Date: 04/02/2014

 Description:  Multiple borrowing exception is thrown when a member tries to borrow the same holding twice.
**/

package lms.model.exception;

public class MultipleBorrowingException extends LMSException {

	public MultipleBorrowingException() {
		super("Default Multiple Borrowing Exception Message");
	}

	public MultipleBorrowingException(String message) {
		super(message);
		
	}

}
