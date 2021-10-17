package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.PrefGeneralPrefEspecifica;
import um.business.entities.PrefGeneralPrefEspecificaKey;
import um.business.entities.PreferenciaGeneral;

public interface PrefGeneralPrefEspecificaRepository extends CrudRepository<PrefGeneralPrefEspecifica, PrefGeneralPrefEspecificaKey> {

    Iterable<PrefGeneralPrefEspecifica> getAllByPreferenciaGeneral(PreferenciaGeneral preferenciaGeneral);
}
