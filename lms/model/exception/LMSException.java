package lms.model.exception;

/**
 * @author Mikhail Perepletchikov
 */
@SuppressWarnings("serial")
public abstract class LMSException extends Exception {

	public LMSException() {
		super("Default LMS Exception");
	}

	// NOTE: it is advisable to use this constructor when creating new exceptions
	public LMSException(String message) {
		super(message);
	}
}