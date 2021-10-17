package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.controlsfx.control.CheckListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.PreferenciasMgr;
import um.business.PrefGeneralPrefEspecificaMgr;
import um.business.entities.PreferenciaEspecifica;
import um.business.entities.PreferenciaGeneral;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CheckListPreferenciaEspecifica implements Initializable {

    @Autowired
    private PreferenciasMgr preferenciasMgr;

    @Autowired
    private PrefGeneralPrefEspecificaMgr prefGeneralPrefEspecificaMgr;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnAtras;

    static ObservableList<String> espElegidas = FXCollections.observableArrayList();
    private ObservableList<PreferenciaEspecifica> preferencias = FXCollections.observableArrayList();


    @FXML
    private CheckListView<String> preferenciaEspecificaCheckListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(PreferenciaGeneral pg : CheckListPreferenciaGen.prefElegidas){

            ObservableList<PreferenciaEspecifica> temp = prefGeneralPrefEspecificaMgr.getAllByPrefGeneral(pg);



        }


    }
}
