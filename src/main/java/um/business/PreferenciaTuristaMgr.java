package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Preferencia;
import um.business.entities.PreferenciaTurista;
import um.business.entities.PreferenciaTuristaKey;
import um.business.entities.Turista;
import um.persistance.PreferenciaRepository;
import um.persistance.PreferenciaTuristaRepository;

@Service
public class PreferenciaTuristaMgr {

    @Autowired
    private PreferenciaTuristaRepository preferenciaTuristaRepository;

    @Autowired
    private PreferenciaRepository preferenciaRepository;

    public void addPreferenciaTurista(Turista turista, String preferencia){

        Preferencia pref = preferenciaRepository.getPreferenciaByNombre(preferencia);
        PreferenciaTuristaKey id = new PreferenciaTuristaKey(turista.getId(), pref.getId());

        PreferenciaTurista nuevo = new PreferenciaTurista(id, turista, pref);
        preferenciaTuristaRepository.save(nuevo);

    }
}
