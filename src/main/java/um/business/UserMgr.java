package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.Entities.User;
import um.persistance.UserRepository;
import um.business.Exception.IncorrectPassword;
//import um.edu.uy.business.exceptions.ClientAlreadyExists;
//import um.edu.uy.business.exceptions.InvalidClientInformation;


@Service
public class UserMgr{

    @Autowired
    private UserRepository UserRepositoryimp;

    public void addUser(String userName, String nombre, String apellido, String mail,
                          String country, String password)
            throws Exception {

        if (nombre == null || "".equals(nombre) || userName == null || "".equals(userName)
        || apellido == null || "".equals(apellido) || mail == null || "".equals(mail)
        || password == null || "".equals(password) || country == null || "".equals(country)) {

            throw new Exception("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe

        if (UserRepositoryimp.findOneByUserName(userName) != null) {

            throw new Exception();
        }

        User oClient = new User(mail, nombre, apellido, userName, password, country);

        UserRepositoryimp.save(oClient);

    }

    public boolean IngresarUser(String userName, String password) throws incorrectPassword, {

        if(UserRepositoryimp.findOneByUserName(userName) == null){
            throw new Exception();
        }

        User oUser = UserRepositoryimp.findOneByUserName(userName);

        if(!oUser.getPassword().equals(password)){
            throw new Exception();
        }

        return true;

    }

}
