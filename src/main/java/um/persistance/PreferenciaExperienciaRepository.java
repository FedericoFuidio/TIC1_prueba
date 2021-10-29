package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Experiencia;
import um.business.entities.PreferenciaExperiencia;
import um.business.entities.PreferenciaExperienciaKey;

public interface PreferenciaExperienciaRepository extends CrudRepository<PreferenciaExperiencia, PreferenciaExperienciaKey> {

    PreferenciaExperiencia findPreferenciaExperienciaByExperiencia(Experiencia experiencia);

}
