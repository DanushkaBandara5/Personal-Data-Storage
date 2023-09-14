package lk.ijse.app.personal.business.exception;

public class DuplicateEntryException extends BusinessException{
    public DuplicateEntryException() {
        super();
    }

    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String message, Throwable e) {
        super(message, e);
    }
}
