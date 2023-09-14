package lk.ijse.app.personal.advice;

import lk.ijse.app.personal.business.exception.BusinessException;
import lk.ijse.app.personal.business.exception.DuplicateEntryException;
import lk.ijse.app.personal.business.exception.InvalidAuthentications;
import lk.ijse.app.personal.business.exception.RecordNotFundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public void businessExceptionHandler(BusinessException e) {
        ResponseStatusException exp = null;
        if (e instanceof DuplicateEntryException) {
            exp = new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        if (e instanceof InvalidAuthentications) {
            exp = new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        if (e instanceof RecordNotFundException) {
            exp = new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        e.initCause(exp);
        throw e;
    }
}
