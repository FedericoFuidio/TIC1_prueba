package um.business.exception;

public class ClassAlreadyExists extends Exception{

    public ClassAlreadyExists(){
        super();
    }

    public ClassAlreadyExists(String message){
        super(message);
    }
}
