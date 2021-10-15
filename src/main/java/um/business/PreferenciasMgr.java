package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.PreferenciaEspecifica;
import um.business.entities.PreferenciaGeneral;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.PreferenciaEspecificaRepository;
import um.persistance.PreferenciaGeneralRepository;

@Service
public class PreferenciasMgr {

    @Autowired
    PreferenciaGeneralRepository preferenciaGeneralRepository;

    @Autowired
    PreferenciaEspecificaRepository preferenciaEspecificaRepository;

    public void addPreferenciaGeneral(String nombre, String descripcion)
        throws InvalidInformation, ClassAlreadyExists {
        if(nombre == null || nombre.equals("") || descripcion == null || descripcion.equals("")){
            throw new InvalidInformation();
        }

        PreferenciaGeneral nueva = new PreferenciaGeneral(nombre, descripcion);
        PreferenciaGeneral temp = preferenciaGeneralRepository.getPreferenciaGeneralByDescripcionAndAndNombre(descripcion, nombre);

        if(temp != null){
            throw new ClassAlreadyExists();
        }

        preferenciaGeneralRepository.save(nueva);
    }

    public void addPreferenciaEspecifica(String nombre, String descripcion)
        throws InvalidInformation, ClassAlreadyExists{

        if(nombre == null || nombre.equals("") || descripcion == null || descripcion.equals("")){
            throw new InvalidInformation();
        }

        PreferenciaEspecifica nueva = new PreferenciaEspecifica(nombre, descripcion);
        PreferenciaEspecifica temp = preferenciaEspecificaRepository.getPreferenciaEspecificaByNombreAndAndDescripcion(nombre, descripcion);

        if(temp != null){
            throw new ClassAlreadyExists();
        }

        preferenciaEspecificaRepository.save(nueva);

    }
}
