package lk.ijse.app.personal.business.exception;

public class InvalidAuthentications extends BusinessException {
    public InvalidAuthentications() {
        super();
    }

    public InvalidAuthentications(String message) {
        super(message);
    }

    public InvalidAuthentications(String message, Throwable e) {
        super(message, e);
    }
}
