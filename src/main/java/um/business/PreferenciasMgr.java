package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.*;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.PreferenciaEspecificaRepository;
import um.persistance.PreferenciaGeneralRepository;
import um.persistance.PreferenciaRepository;
import um.persistance.PreferenciaTuristaRepository;

@Service
public class PreferenciasMgr {

    @Autowired
    PreferenciaGeneralRepository preferenciaGeneralRepository;

    @Autowired
    PreferenciaTuristaRepository preferenciaTuristaRepository;

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

    public ObservableList<PreferenciaGeneral> getPreferenciasGenerales(){

        ObservableList<PreferenciaGeneral> preferencias = FXCollections.observableArrayList();
        Iterable<PreferenciaGeneral> temp = preferenciaGeneralRepository.findAll();

        for(PreferenciaGeneral pg : temp){
            preferencias.add(pg);
        }

        return preferencias;
    }

    public ObservableList<PreferenciaEspecifica> getPreferenciasEspecificas(){

        ObservableList<PreferenciaEspecifica> preferencias = FXCollections.observableArrayList();
        Iterable<PreferenciaEspecifica> temp = preferenciaEspecificaRepository.findAll();

        for(PreferenciaEspecifica pe : temp){
            preferencias.add(pe);
        }


        return preferencias;
    }

    public PreferenciaGeneral getPreferenciaGeneralByNombre(String nombre){

        return preferenciaGeneralRepository.getPreferenciaGeneralByNombre(nombre);
    }

    public PreferenciaEspecifica getPreferenciaEspecificaByNombre(String nombre){
        return preferenciaEspecificaRepository.getPreferenciaEspecificaByNombre(nombre);
    }

    public ObservableList<Preferencia> getPreferenciasAgregar(Turista turista){

        ObservableList<Preferencia> resultado = FXCollections.observableArrayList();
        Iterable<Preferencia> temp = preferenciaRepository.findAll();
        for(Preferencia pref : temp){

            if(preferenciaTuristaRepository.findPreferenciaTuristaByPreferenciaAndTurista(pref, turista) == null){

                resultado.add(pref);
            }
        }

        return resultado;
    }

    public ObservableList<Preferencia> getPreferenciasEliminar(Turista turista){
        ObservableList<Preferencia> resultado = FXCollections.observableArrayList();
        Iterable<PreferenciaTurista> temp = preferenciaTuristaRepository.findPreferenciaTuristaByTurista(turista);

        for(PreferenciaTurista pt : temp){

            resultado.add(pt.getPreferencia());
        }

        return resultado;
    }

    public Preferencia getPreferenciasByNombre(String nombre){

        return preferenciaRepository.getPreferenciaByNombre(nombre);
    }


}
