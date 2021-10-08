package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Turista;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;
import um.business.exception.RepitedUserName;
import um.persistance.TuristaRepository;

@Service
public class TuristaMgr {

    @Autowired
    private TuristaRepository turistaRepository;

    //Agregamos un Turista al sistema:
    public void addTurista(String mail, String userName, String password, String name,
                      String apellido, String pais, Long passport) throws RepitedUserName,
                        InvalidInformation, RepitedMail {

        if(mail == null || mail.equals("") || userName == null || userName.equals("") ||
            password == null || password.equals("") || name == null || name.equals("") ||
            apellido == null || apellido.equals("") || pais == null || pais.equals("")
            || passport == null){

            throw new InvalidInformation();
        }

        if(turistaRepository.findTuristaByUserName(userName) != null){
            throw new RepitedUserName();
        }

        if(turistaRepository.findTuristaByMail(mail) != null){
            throw new RepitedMail();
        }

        Turista nuevo = new Turista(mail, userName, password, name, apellido, pais, passport);
        turistaRepository.save(nuevo);
        Iterable<Turista> turistaList = turistaRepository.findAll();

    }

    public Iterable<Turista> GetTuristas(){

        return turistaRepository.findAll();

    }

}
