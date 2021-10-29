package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Experiencia;
import um.business.entities.Preferencia;
import um.business.entities.PreferenciaExperiencia;
import um.business.entities.PreferenciaExperienciaKey;
import um.persistance.PreferenciaExperienciaRepository;
import um.persistance.PreferenciaRepository;

@Service
public class PreferenciaExperienciaMgr {

    @Autowired
    private PreferenciaExperienciaRepository preferenciaExperienciaRepository;

    @Autowired
    private PreferenciaRepository preferenciaRepository;

    public void addPreferenciaExperiencia(Experiencia experiencia, String preferencia){

        Preferencia pref = preferenciaRepository.getPreferenciaByNombre(preferencia);
        PreferenciaExperienciaKey id = new PreferenciaExperienciaKey(experiencia.getId(), pref.getId());

        PreferenciaExperiencia nuevo = new PreferenciaExperiencia(id, pref, experiencia);
        preferenciaExperienciaRepository.save(nuevo);
    }
}
