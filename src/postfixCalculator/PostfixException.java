package postfixCalculator;

// Implement a custom exception class for displaying error messages
public class PostfixException extends Exception {
	
	// Implement method that takes error message from PostfixCalculator methods
	public PostfixException(String message) {
		
		// Call super to pass correct message from PostfixCalculator
		super(message);
	}
}
