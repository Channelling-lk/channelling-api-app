package lk.channelling.advisers;

import lk.channelling.exception.NoContentException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(status.value());
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, headers, status);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<Object> handleRecordNotFound(RecordNotFoundException ex) {
        return commonExceptionHandler(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return commonExceptionHandler(ex, HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<Object> commonExceptionHandler(Exception ex, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(status.value());

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, status);
    }
}
