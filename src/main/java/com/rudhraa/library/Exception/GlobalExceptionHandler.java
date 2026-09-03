package com.rudhraa.library.Exception;
import com.rudhraa.library.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.naming.AuthenticationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException exception){
       return ResponseEntity.status(HttpStatus.NOT_FOUND)
               .body(new ErrorResponse(404, exception.getMessage()));
   }

    @ExceptionHandler(BookNotAvailableException.class)
   public ResponseEntity<?> handleBookNotAvailable(BookNotAvailableException exception){
       return ResponseEntity.status(HttpStatus.CONFLICT)
               .body(new ErrorResponse(409, exception.getMessage()));
   }

   @ExceptionHandler(BookAlreadyReturnedException.class)
   public ResponseEntity<?> handleBookAlreadyReturned(BookAlreadyReturnedException exception){
       return ResponseEntity.status(HttpStatus.CONFLICT)
               .body(new ErrorResponse(409, exception.getMessage()));
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, message));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException exception) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(401, "Invalid username or password"));
    }

    @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleOtherException(Exception exception){
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(new ErrorResponse(500, exception.getMessage()));
   }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException exception) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                        401,
                        "Invalid username or password"
                ));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(
            ResourceAlreadyExistsException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(
                        400,
                        exception.getMessage()
                ));
    }

}
