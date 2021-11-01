package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;

public interface CupoRepository extends CrudRepository<Cupo, Long> {

    Cupo getCupoByDiaAndHoraInicioAndExperiencia(String dia, String horaInicio, Experiencia experiencia);
}
