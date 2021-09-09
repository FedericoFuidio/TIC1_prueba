package um.business.Exception;

//Creacion de excepcion:
public class UserAlreadyExists extends Exception{

    public UserAlreadyExists(String message){
        super(message);
    }

    public UserAlreadyExists(){

    }
}
