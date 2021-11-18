package group825.vetapp.exceptions;

import java.util.function.Supplier;

public class ApiExceptions {
	
    /**apiRequestException function
     * @param message = String to be passed into the exception
     * @return Supplier holding a RuntimeException object
     */
    public static Supplier<RuntimeException> apiRequestException(String message) {
        return () -> new ApiRequestException(message);
    }
    
    /** invalidIdException function
     * @return Supplier holding a RuntimeException object
     */
    public static Supplier<RuntimeException> invalidIdException() {
        return () -> new InvalidIdException();
    }

}
