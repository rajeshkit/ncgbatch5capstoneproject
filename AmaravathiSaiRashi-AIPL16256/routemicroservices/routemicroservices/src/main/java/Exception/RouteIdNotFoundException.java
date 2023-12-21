package Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteIdNotFoundException extends RuntimeException {

    public RouteIdNotFoundException(String message) {
        super(message);
    }
}
