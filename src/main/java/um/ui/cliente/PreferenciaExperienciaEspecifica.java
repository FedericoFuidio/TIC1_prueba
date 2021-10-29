package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.PreferenciaExperienciaMgr;
import um.business.PreferenciasMgr;
import um.business.entities.Experiencia;
import um.business.entities.PreferenciaEspecifica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class PreferenciaExperienciaEspecifica implements Initializable {

    @Autowired
    private PreferenciasMgr preferenciasMgr;

    @Autowired
    private PreferenciaExperienciaMgr preferenciaExperienciaMgr;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnAtras;

    static ObservableList<String> elegidos = FXCollections.observableArrayList();
    private ObservableList<PreferenciaEspecifica> preferencias = FXCollections.observableArrayList();
    static ObservableList<PreferenciaEspecifica> prefElegidas = FXCollections.observableArrayList();


    @FXML
    private CheckListView<String> preferenciaEspecificaCheckListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        preferencias = preferenciasMgr.getPreferenciasEspecificas();

        ObservableList<String> nombre = FXCollections.observableArrayList();
        for(PreferenciaEspecifica pe : preferencias){

            nombre.add(pe.getNombre());

        }

        preferenciaEspecificaCheckListView.setItems(nombre);

    }


    @FXML
    void addPreferencias(ActionEvent event) throws IOException {

        Experiencia experiencia = ExperienciaController.experienciaIngresada;


        elegidos = preferenciaEspecificaCheckListView.getCheckModel().getCheckedItems();
        for(String peNombre : elegidos){

            PreferenciaEspecifica pe = preferenciasMgr.getPreferenciaEspecificaByNombre(peNombre);
            prefElegidas.add(pe);
            preferenciaExperienciaMgr.addPreferenciaExperiencia(experiencia, peNombre);

        }

        showAlert("Experiencia registrada", "Se agrego exitosamente la experiencia!");

        close(event);


    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
