package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.business.exception.UserNotFound;
import um.persistance.CupoRepository;
import um.persistance.ExperienciaGeneralRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
public class ExperienciaMgr {

    @Autowired
    ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    CupoRepository cupoRepository;

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

        if(operador != null) {
            System.out.println(operador.getDescripcion());
            return experienciaGeneralRepository.getAllByOperador(operador);
        }else{
            return getExperiencias();
        }

    }

    public void addCupoGeneral(Experiencia experiencia, LocalTime horaApertura, LocalTime horaCierre, DayOfWeek dia, Integer personas) throws InvalidInformation{

        if(horaApertura.compareTo(horaCierre) >= 0){
            throw new InvalidInformation();
        }

        Cupo cupo = cupoRepository.getCupoByDiaAndHoraAperturaAndExperiencia(dia, horaApertura, experiencia);

        if(cupo != null){

            cupo.setHoraApertura(horaApertura);
            cupo.setHoraCierre(horaCierre);
            cupoRepository.save(cupo);

        }

        Cupo nuevo = new Cupo(personas, dia, horaApertura, horaCierre, experiencia);
        cupoRepository.save(nuevo);


    }

    public void validar(Experiencia experiencia){

        experiencia.setValidado(true);
        experienciaGeneralRepository.save(experiencia);
    }

    public void bloquear(Experiencia experiencia){
        experiencia.setValidado(false);
        experienciaGeneralRepository.save(experiencia);
    }

}
