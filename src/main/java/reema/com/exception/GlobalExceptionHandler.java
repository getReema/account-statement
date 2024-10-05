package reema.com.exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest ){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), exception.getMessage(), // this will load the message I providded for the class ResourceNotFoundException
				webRequest.getDescription(true),  // if false, e.g. "path": "uri=...{id}/50", 
				"ACCOUNT_NOT_FOUND");
				
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(BadParameters.class)
	public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(BadParameters exception, WebRequest webRequest ){
		
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(true),
				"BAD_PARAMETERS");
				
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}

}


