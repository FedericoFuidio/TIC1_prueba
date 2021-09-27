package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;
import um.business.exception.RepitedUserName;
import um.persistance.OperadorRepository;


@Service
public class OperadorMgr {

    @Autowired
    private OperadorRepository operadorRepository;

    //Agregamos un Operador al sistema:
    public void addOperador(String mail, String userName, String password, String foto, String name,
                            String phone, String descripcion, String sitioWeb, String ubicacion) throws RepitedUserName,

            InvalidInformation, RepitedMail {

        if(mail == null || mail.equals("") || userName == null || userName.equals("") ||
                password == null || password.equals("") || name == null || name.equals("") ||
                phone == null || phone.equals("") || descripcion == null || descripcion.equals("")
                || sitioWeb == null || sitioWeb.equals("") || ubicacion == null || ubicacion.equals("")){

            throw new InvalidInformation();
        }

        if(operadorRepository.findOperadorByUserName(userName) != null){
            throw new RepitedUserName();
        }

        if(operadorRepository.findOperadorByMail(mail) != null){
            throw new RepitedMail();
        }

        Operador nuevo = new Operador(mail, userName, password, foto, name, phone, descripcion, sitioWeb, ubicacion);
        operadorRepository.save(nuevo);
        //System.out.println(operadorRepository.findAllByIdExists());

    }
}
