package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.PreferenciaEspecifica;

public interface PreferenciaEspecificaRepository extends CrudRepository<PreferenciaEspecifica, Long> {

    PreferenciaEspecifica getPreferenciaEspecificaByNombre(String nombre);
}
