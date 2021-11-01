package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Experiencia;
import um.business.entities.Operador;

public interface ExperienciaGeneralRepository extends CrudRepository<Experiencia, Long> {

    Experiencia getExperienciaByNombre(String nombre);
    Iterable<Experiencia> getExperienciasByOperador(Operador operador);

}
