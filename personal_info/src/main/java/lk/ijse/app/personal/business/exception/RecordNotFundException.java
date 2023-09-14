package lk.ijse.app.personal.business.exception;

public class RecordNotFundException extends BusinessException{
    public RecordNotFundException() {
        super();
    }

    public RecordNotFundException(String message) {
        super(message);
    }

    public RecordNotFundException(String message, Throwable e) {
        super(message, e);
    }
}
