package pt.donors.club.donors_club.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotAcceptableException extends RuntimeException {
    private static final long serialVersionUID = -3038223729345216106L;

    public NotAcceptableException(String id, String elemType, String idName) {
        super(elemType + " with " + idName + " " + id + " not acceptable.");
    }
}
