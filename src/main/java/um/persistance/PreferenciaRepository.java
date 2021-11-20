package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Preferencia;

public interface PreferenciaRepository extends CrudRepository<Preferencia, Long> {

    Preferencia getPreferenciaByNombre(String nombre);
    Preferencia getPreferenciaById(Long id);

}
