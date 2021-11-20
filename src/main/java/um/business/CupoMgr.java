package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.CupoRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
public class CupoMgr {

    @Autowired
    private CupoRepository cupoRepository;

    public void addCupo(int cupos, DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin, Experiencia experiencia)
            throws InvalidInformation, ClassAlreadyExists {

        if(dia == null || horaInicio == null || horaFin == null){
            throw new InvalidInformation();
        }

        Cupo nuevo = new Cupo(cupos, dia, horaInicio, horaFin, experiencia);

        if(cupoRepository.getCupoByDiaAndHoraAperturaAndExperiencia(dia, horaInicio, experiencia) != null){

            throw new ClassAlreadyExists();
        }

        cupoRepository.save(nuevo);

    }

    public Cupo getCupo(Experiencia exp, DayOfWeek dia){
        return cupoRepository.getCupoByExperienciaAndDia(exp, dia);
    }

    public Cupo getCupoByExperienciaAndDia(Experiencia exp, DayOfWeek dia){
        return cupoRepository.getCupoByExperienciaAndDia(exp, dia);
    }
}
