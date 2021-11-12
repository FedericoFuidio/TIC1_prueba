package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface CupoRepository extends CrudRepository<Cupo, Long> {

    Cupo getCupoByDiaAndHoraAperturaAndExperiencia(DayOfWeek dia, LocalTime horaInicio, Experiencia experiencia);

    Cupo getCupoByExperienciaAndDia(Experiencia exp, DayOfWeek dia);

    Cupo getCupoByDia(DayOfWeek dia);
}
