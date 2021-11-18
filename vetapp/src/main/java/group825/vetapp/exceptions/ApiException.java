package group825.vetapp.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiException {
	
	private final String message;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	
	/** Constructor
	 * @param message = String to be included in the message of the exception
	 * @param httpStatus = Status of http request
	 * @param timestamp = timestamp of when the http request was made
	 */
	public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
		this.message = message;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
	}

	/** getter for message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/** getter for HttpStatus
	 * @return httpstatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/** getter for TimeStamp
	 * @return timestamp
	 */
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

}
