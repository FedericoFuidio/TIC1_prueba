package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.AdminOperador;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.persistance.AdminOperadorRepository;

@Service
public class AdminOperadorMgr {

    @Autowired
    private AdminOperadorRepository adminOperadorRepository;

    public void addAdminOperador(String nombre, String apellido, String password, Operador operador, String username)
        throws InvalidInformation{

        if(nombre == null || nombre.equals("") || apellido == null || apellido.equals("")
            || password == null || password.equals("") || username == null || username.equals("")){
            throw new InvalidInformation();
        }

        AdminOperador oNuevo = new AdminOperador(nombre, apellido, password, operador, username);

        adminOperadorRepository.save(oNuevo);

    }

    public AdminOperador ingresarAdmin(String userName, String password){
        System.out.println(userName);
        System.out.println(password);
        return adminOperadorRepository.findAdminOperadorByUsernameAndPassword(userName, password);
    }
}
