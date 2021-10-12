package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.PreferenciaGeneral;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.PreferenciaGeneralRepository;

@Service
public class PreferenciasMgr {

    @Autowired
    PreferenciaGeneralRepository preferenciaGeneralRepository;

    public void addPreferenciaGeneral(String nombre, String descripcion)
        throws InvalidInformation, ClassAlreadyExists {
        if(nombre == null || nombre.equals("") || descripcion == null || descripcion.equals("")){
            throw new InvalidInformation();
        }

        PreferenciaGeneral nuevo = preferenciaGeneralRepository.getPreferenciaGeneralByDescripcionAndAndNombre(descripcion, nombre);

        if(nuevo == null){
            throw new ClassAlreadyExists();
        }

        preferenciaGeneralRepository.save(nuevo);
    }
}
