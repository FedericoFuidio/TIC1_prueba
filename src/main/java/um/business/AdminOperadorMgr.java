package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.AdminOperador;
import um.business.entities.Operador;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.AdminOperadorRepository;
import um.persistance.AdministradorRepository;
import um.persistance.TuristaRepository;

@Service
public class AdminOperadorMgr {

    @Autowired
    private AdminOperadorRepository adminOperadorRepository;

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    public void addAdminOperador(String nombre, String apellido, String password, Operador operador, String username)
        throws InvalidInformation, ClassAlreadyExists{

        if(nombre == null || nombre.equals("") || apellido == null || apellido.equals("")
            || password == null || password.equals("") || username == null || username.equals("")){
            throw new InvalidInformation();
        }

        if(adminOperadorRepository.findAdminOperadorByUsername(username) != null ||
        turistaRepository.findTuristaByUserName(username) != null ||
        administradorRepository.findAdministradorByUserName(username) != null){
            throw new ClassAlreadyExists();
        }

        AdminOperador oNuevo = new AdminOperador(nombre, apellido, password, operador, username);

        adminOperadorRepository.save(oNuevo);

    }

    public AdminOperador ingresarAdmin(String userName, String password){

        return adminOperadorRepository.findAdminOperadorByUsernameAndPassword(userName, password);
    }
}
