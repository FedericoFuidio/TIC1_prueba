package um.business.exception;

public class RepitedMail extends Exception{

    public RepitedMail(){
        super();
    }

    public RepitedMail(String message){
        super(message);
    }
}
