package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.CupoRepository;

import java.sql.Time;

@Service
public class CupoMgr {

    @Autowired
    private CupoRepository cupoRepository;

    public void addCupo(int cupos, String dia, Time horaInicio, Time horaFin, Experiencia experiencia)
            throws InvalidInformation, ClassAlreadyExists {

        if(dia == null || horaInicio == null || horaFin == null){
            throw new InvalidInformation();
        }

        Cupo nuevo = new Cupo(cupos, dia, horaInicio, horaFin, experiencia);

        if(cupoRepository.getCupoByDiaAndHoraInicioAndExperiencia(dia, horaInicio, experiencia) != null){

            throw new ClassAlreadyExists();
        }

        cupoRepository.save(nuevo);

    }
}
