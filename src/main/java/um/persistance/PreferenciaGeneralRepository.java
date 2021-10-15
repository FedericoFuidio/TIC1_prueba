package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.PreferenciaGeneral;

public interface PreferenciaGeneralRepository extends CrudRepository<PreferenciaGeneral, Long> {

    PreferenciaGeneral getPreferenciaGeneralByNombre(String nombre);
}
