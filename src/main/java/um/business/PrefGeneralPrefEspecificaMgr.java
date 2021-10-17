package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.PrefGeneralPrefEspecifica;
import um.business.entities.PreferenciaEspecifica;
import um.business.entities.PreferenciaGeneral;
import um.persistance.PrefGeneralPrefEspecificaRepository;

@Service
public class PrefGeneralPrefEspecificaMgr {

    @Autowired
    private PrefGeneralPrefEspecificaRepository prefGeneralPrefEspecificaRepository;

    public ObservableList<PreferenciaEspecifica> getAllByPrefGeneral(PreferenciaGeneral preferenciaGeneral){

        ObservableList<PreferenciaEspecifica> preferencias = FXCollections.observableArrayList();
        Iterable<PrefGeneralPrefEspecifica> temp = prefGeneralPrefEspecificaRepository.getAllByPreferenciaGeneral(preferenciaGeneral);

        for(PrefGeneralPrefEspecifica pgpe : temp){
            preferencias.add(pgpe.getPreferenciaEspecifica());
        }

        return preferencias;

    }
}
