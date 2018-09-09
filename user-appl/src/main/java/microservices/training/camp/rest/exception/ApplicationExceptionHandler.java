package microservices.training.camp.rest.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		ResquestResponse requestResponse = 
				new ResquestResponse(new Date(), ex.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<Object>(requestResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleAllUserNotFoundException(Exception ex, WebRequest request){
		ResquestResponse requestResponse = 
				new ResquestResponse(new Date(), ex.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<Object>(requestResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override//Override this method to filter input validations
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResquestResponse requestResponse = 
				new ResquestResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<Object>(requestResponse, HttpStatus.BAD_REQUEST);
	}
}
