package um.business.exception;

//Creacion de excepcion:
public class RepitedUserName extends Exception{

    public RepitedUserName(String message){
        super(message);
    }

    public RepitedUserName(){

    }
}
