package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Turista;
import um.business.entities.User;
import um.persistance.TuristaRepository;
import um.persistance.UserRepository;

import java.time.LocalDate;


@Service
public class UserMgr{

    @Autowired
    private UserRepository UserRepositoryimp;

    @Autowired
    TuristaRepository turistaRepository;

    //Agregar un usuario al sistema:
    public void addUser(String nombre, String apellido, String userName, String mail, LocalDate birthDate,
                        String country, String password)
            throws Exception {

        //verifico si algun dato es nulo, o igual al string nulo:
        if (nombre == null || "".equals(nombre) || userName == null || "".equals(userName)
        || apellido == null || "".equals(apellido) || mail == null || "".equals(mail)
        || password == null || "".equals(password) || country == null || "".equals(country)
        || birthDate == null) {

            throw new Exception("Alguno de los datos ingresados no es correcto");

        }

        // Verifico si el cliente no existe
        if (UserRepositoryimp.findOneByUserName(userName) != null) {
            throw new Exception();
        }

        //Creo un nuevo cliente y lo guardo
        User oClient = new User(mail, userName, password);
        System.out.println(oClient.getUserName());

        UserRepositoryimp.save(oClient);

    }

    //Ingresar a la app con un usuario ya existente:
    public User IngresarUser(String userName, String password) throws Exception {

        User oUser;

        //Si el usuario no existe lanzo una excepcion
        if(UserRepositoryimp.findOneByUserName(userName) == null){
            throw new Exception();
        }

        //Si existe, encuentro a ese usuario, y verifico que la contreseña coincide con la ingresada:
        oUser = UserRepositoryimp.findOneByUserName(userName);

        if(oUser.getPassword().equals(password)){
            return oUser;
        } else{
            return null;
        }

    }

    public Turista obtenerTurista(User user){

        if(user == null){
            return null;
        } else {
            long id = user.getId();
            if (turistaRepository.findTuristaById(id) != null) {
                return turistaRepository.findTuristaById(id);
            } else {
                return null;
            }
        }
    }



}
