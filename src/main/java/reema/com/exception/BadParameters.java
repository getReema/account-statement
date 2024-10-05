package reema.com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// exception for request with bad paramters
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadParameters extends RuntimeException{
	private String message;
	
	public BadParameters(String message) {
		super(message);
		this.message =message;
	}

}
