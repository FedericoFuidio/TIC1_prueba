package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;
import um.persistance.OperadorRepository;


@Service
public class OperadorMgr {

    @Autowired
    private OperadorRepository operadorRepository;

    //Agregamos un Operador al sistema:
    public void addOperador(String mail, String name,
                            String phone, String descripcion, String sitioWeb, String ubicacion) throws RepitedMail,

            InvalidInformation, RepitedMail {

        if(mail == null || mail.equals("") || name == null || name.equals("") ||
                phone == null || phone.equals("") || descripcion == null || descripcion.equals("")
                || sitioWeb == null || sitioWeb.equals("") || ubicacion == null || ubicacion.equals("")){

            throw new InvalidInformation();
        }

        if(operadorRepository.findOperadorByMail(mail) != null){
            throw new RepitedMail();
        }


        Operador nuevo = new Operador(mail, name, phone, descripcion, sitioWeb, ubicacion);
        operadorRepository.save(nuevo);


    }
    public Iterable<Operador> GetOperadores(){

        return operadorRepository.findAll();

    }

    public void setValidado(Operador op, boolean b){
        op.setValidado(b);
        operadorRepository.save(op);
    }

    public Operador getByMail(String mail){
        return operadorRepository.findOperadorByMail(mail);
    }

}
