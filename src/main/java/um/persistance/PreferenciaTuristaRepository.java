package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Preferencia;
import um.business.entities.PreferenciaTurista;
import um.business.entities.PreferenciaTuristaKey;
import um.business.entities.Turista;

public interface PreferenciaTuristaRepository extends CrudRepository<PreferenciaTurista, PreferenciaTuristaKey> {

    Iterable<PreferenciaTurista> findPreferenciaTuristaByTurista(Turista turista);
    PreferenciaTurista findPreferenciaTuristaByPreferencia(Preferencia preferencia);
    Iterable<PreferenciaTurista> getAllByTurista(Turista turista);
    PreferenciaTurista findPreferenciaTuristaByPreferenciaAndTurista(Preferencia preferencia, Turista turista);
}
