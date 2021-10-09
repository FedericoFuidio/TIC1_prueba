package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.ExperienciaGeneral;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;
import um.persistance.ExperienciaGeneralRepository;

@Service
public class ExperienciaGeneralMgr {

    @Autowired
    ExperienciaGeneralRepository experienciaGeneralRepository;

    public void addExperiencia(String nombre, String ubicacion, String descripcion, String foto, Operador operador)
            throws InvalidInformation{

        if(nombre == null || nombre.equals("") || ubicacion == null || ubicacion.equals("")){
            throw new InvalidInformation();
        }


        ExperienciaGeneral nueva = new ExperienciaGeneral(nombre, ubicacion, descripcion, foto, operador);
        experienciaGeneralRepository.save(nueva);

    }
}
