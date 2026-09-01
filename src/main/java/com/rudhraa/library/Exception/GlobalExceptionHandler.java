package com.rudhraa.library.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
   public ResponseEntity<?> handleOtherException(Exception exception){
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
               .body(new ErrorResponse(500, exception.getMessage()));
   }
}
