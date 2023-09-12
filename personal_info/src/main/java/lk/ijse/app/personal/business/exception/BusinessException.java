package lk.ijse.app.personal.business.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(){
        super();
    }

   public BusinessException(String message){
        super(message);
   }
   public BusinessException(String message,Throwable e){
        super(message,e);
   }
}