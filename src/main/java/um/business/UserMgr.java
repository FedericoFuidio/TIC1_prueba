package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.Entities.User;
import um.persistance.UserRepository;
//import um.edu.uy.business.exceptions.ClientAlreadyExists;
//import um.edu.uy.business.exceptions.InvalidClientInformation;


@Service
public class UserMgr{

    @Autowired
    private UserRepository UserRepositoryimp;

    //Agregar un usuario al sistema:
    public void addUser(String userName, String nombre, String apellido, String mail,
                          String country, String password)
            throws Exception {

        //verifico si algun dato es nulo, o igual al string nulo:
        if (nombre == null || "".equals(nombre) || userName == null || "".equals(userName)
        || apellido == null || "".equals(apellido) || mail == null || "".equals(mail)
        || password == null || "".equals(password) || country == null || "".equals(country)) {

            throw new Exception("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe
        if (UserRepositoryimp.findOneByUserName(userName) != null) {
            throw new Exception();
        }

        //Creo un nuevo cliente y lo guardo
        User oClient = new User(mail, nombre, apellido, userName, password, country);
        System.out.println(oClient.getUserName());

        UserRepositoryimp.save(oClient);

    }

    //Ingresar a la app con un usuario ya existente:
    public boolean IngresarUser(String userName, String password) throws Exception {

        //Si el usuario no existe lanzo una excepcion
        if(UserRepositoryimp.findOneByUserName(userName) == null){
            throw new Exception();
        }

        //Si existe, encuentro a ese usuario, y verifico que la contreseña coincide con la ingresada:
        User oUser = UserRepositoryimp.findOneByUserName(userName);

        if(!oUser.getPassword().equals(password)){
            return false;
        }

        return true;

    }

}
