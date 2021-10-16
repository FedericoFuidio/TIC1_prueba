package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Experiencia;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.business.exception.UserNotFound;
import um.persistance.ExperienciaGeneralRepository;

import java.util.List;

@Service
public class ExperienciaMgr {

    @Autowired
    ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    OperadorMgr operadorMgr;

    public void addExperiencia(String nombre, String ubicacion, String descripcion, byte[] foto, String mailOperador)
            throws InvalidInformation, UserNotFound {

        if(nombre == null || nombre.equals("") || ubicacion == null || ubicacion.equals("")){
            throw new InvalidInformation();
        }

        Operador operador = operadorMgr.getByMail(mailOperador);

        if(operador == null){
            throw new UserNotFound();
        }

        Experiencia nueva = new Experiencia(nombre, ubicacion, descripcion, foto, operador);
        experienciaGeneralRepository.save(nueva);

    }

    public Iterable<Experiencia> getExperiencias(){

        return experienciaGeneralRepository.findAll();
    }
}