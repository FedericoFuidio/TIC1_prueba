package business;

import Persistance.UserRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import business.Entities.User;
//import um.edu.uy.business.exceptions.ClientAlreadyExists;
//import um.edu.uy.business.exceptions.InvalidClientInformation;


@Service

public class UserMgr<UserRepository> {

    @Autowired
    private UserRepositoryImp UserRepositoryimp;

    public void addUser(long document, String nombre, String apellido, String mail,
                          String address, String password)
            throws Exception {

        if (nombre == null || "".equals(nombre) || address == null || "".equals(address)
        || apellido == null || "".equals(apellido) || mail == null || "".equals(mail)
        || password == null || "".equals(password)) {

            throw new Exception("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (UserRepositoryimp.findOneByDocument(document) != null) {

            throw new Exception();
        }

        User oClient = new User(document, nombre, apellido, mail, address, password);

        UserRepositoryimp.save(oClient);

    }


}
