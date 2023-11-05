package lk.channelling.advisers;

import lk.channelling.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@CrossOrigin(origins = "*")
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        StandardResponse sr = new StandardResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", e.getMessage());
        return new ResponseEntity<>(sr, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
