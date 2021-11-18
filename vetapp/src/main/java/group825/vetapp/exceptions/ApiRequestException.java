package group825.vetapp.exceptions;

public class ApiRequestException extends RuntimeException{
	/** Constructor with single parameter
	 * @param message = String to be included in the exception response
	 */
	public ApiRequestException(String message) {
		super(message);
	}
	
	/** Constructor with two parameters
	 * @param message = String to be included in the exception response
	 * @param cause = Throwable object holding details of what caused this exception
	 */
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
