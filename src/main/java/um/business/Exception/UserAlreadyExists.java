package um.business.Exception;

public class UserAlreadyExists extends Exception{

    public UserAlreadyExists(String message){
        super(message);
    }

    public UserAlreadyExists(){

    }
}
