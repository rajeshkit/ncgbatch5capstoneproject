package routemicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(RouteIdDoesNotExistException.class)
    public ResponseEntity<?> handlerRouteIdNotExists(RouteIdDoesNotExistException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
