package group825.vetapp.exceptions;

public class InvalidIdException extends RuntimeException{
	/** Constructor
	 *  Passes the invalid Id message to the RuntimeException superclass
	 */
	public InvalidIdException() {
		super("Invalid Id. Please check that the Id is a valid Id for an existing Animal");
	}
	
	/** Constructor with single parameter
	 *  Passes the invalid Id message to the RuntimeException superclass
	 * @param cause = Throwable object holding details of what caused this exception
	 */
	public InvalidIdException(Throwable cause) {
		super("Invalid Id. Please check that the Id is a valid Id for an existing Animal", cause);
	}
}
