package schedulermicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerClass {
    @ExceptionHandler(TrainNoDoesNotExistException.class)
    public ResponseEntity<?> handlerTrainNoDoesNotExists(TrainNoDoesNotExistException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RouteIdDoesNotExistException.class)
    public ResponseEntity<?> handlerRouteIdDoesNotExists(RouteIdDoesNotExistException e){
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
