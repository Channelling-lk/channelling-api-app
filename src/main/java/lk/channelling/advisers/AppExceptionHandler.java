package lk.channelling.advisers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lk.channelling.util.StandardResponse;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class AppExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		return new ResponseEntity<>(
				new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", e.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
