package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Preferencia;
import um.business.entities.PreferenciaEspecifica;
import um.business.entities.PreferenciaGeneral;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.PreferenciaEspecificaRepository;
import um.persistance.PreferenciaGeneralRepository;
import um.persistance.PreferenciaRepository;

@Service
public class PreferenciasMgr {

    @Autowired
    PreferenciaGeneralRepository preferenciaGeneralRepository;

    @Autowired
    PreferenciaEspecificaRepository preferenciaEspecificaRepository;

    @Autowired
    PreferenciaRepository preferenciaRepository;

    public void addPreferenciaGeneral(String nombre, String descripcion)
        throws InvalidInformation, ClassAlreadyExists {
        if(nombre == null || nombre.equals("") || descripcion == null || descripcion.equals("")){
            throw new InvalidInformation();
        }


        Preferencia temp = preferenciaRepository.getPreferenciaByNombre(nombre);

        if(temp != null){
            throw new ClassAlreadyExists();
        }

        PreferenciaGeneral nueva = new PreferenciaGeneral(nombre, descripcion);

        preferenciaGeneralRepository.save(nueva);
    }

    public void addPreferenciaEspecifica(String nombre, String descripcion)
        throws InvalidInformation, ClassAlreadyExists{

        if(nombre == null || nombre.equals("") || descripcion == null || descripcion.equals("")){
            throw new InvalidInformation();
        }


        Preferencia temp = preferenciaRepository.getPreferenciaByNombre(nombre);

        if(temp != null){
            throw new ClassAlreadyExists();
        }

        PreferenciaEspecifica nueva = new PreferenciaEspecifica(nombre, descripcion);

        preferenciaEspecificaRepository.save(nueva);

    }
}
