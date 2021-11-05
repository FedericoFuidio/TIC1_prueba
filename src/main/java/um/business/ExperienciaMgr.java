package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Experiencia;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.business.exception.UserNotFound;
import um.persistance.ExperienciaGeneralRepository;

@Service
public class ExperienciaMgr {

    @Autowired
    ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    OperadorMgr operadorMgr;

    public void addExperiencia(String nombre, String ubicacion, String descripcion, byte[] foto, byte[] mapa,String mailOperador)
            throws InvalidInformation, UserNotFound {

        if(nombre == null || nombre.equals("") || ubicacion == null || ubicacion.equals("")){
            throw new InvalidInformation();
        }

        Operador operador = operadorMgr.getByMail(mailOperador);

        if(operador == null){
            throw new UserNotFound();
        }

        Experiencia nueva = new Experiencia(nombre, ubicacion, descripcion, foto, mapa, operador);
        experienciaGeneralRepository.save(nueva);

    }

    public Iterable<Experiencia> getExperiencias(){

        return experienciaGeneralRepository.findAll();
    }

    public Experiencia getExperienciaByNombre(String nombre){

        return experienciaGeneralRepository.getExperienciaByNombre(nombre);
    }

    public Iterable<Experiencia> getExperienciaByOperador(Operador operador){

        return  experienciaGeneralRepository.getExperienciasByOperador(operador);

    }

    public void addCupoGeneral(Experiencia experiencia, Integer hora_inicial, Integer hora_final) throws InvalidInformation{

        if(hora_inicial >= hora_final){
            throw new InvalidInformation();
        }

        experiencia.setHoraInicio(hora_inicial);
        experiencia.setHoraFin(hora_final);
        experienciaGeneralRepository.save(experiencia);

    }
}
