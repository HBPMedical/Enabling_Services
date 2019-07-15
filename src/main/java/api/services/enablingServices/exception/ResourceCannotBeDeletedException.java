package api.services.enablingServices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceCannotBeDeletedException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResourceCannotBeDeletedException(String message) {
        super(message);
    }
}