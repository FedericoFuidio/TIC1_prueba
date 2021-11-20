package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.PreferenciaTuristaMgr;
import um.business.PreferenciasMgr;
import um.business.entities.Preferencia;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AgregarPreferenciaController implements Initializable {

    @Autowired
    PreferenciasMgr preferenciasMgr;

    @Autowired
    PreferenciaTuristaMgr preferenciaTuristaMgr;

    @FXML
    private CheckListView<String> preferenciaCheckListView;

    private ObservableList<String> elegidos = FXCollections.observableArrayList();
    private ObservableList<Preferencia> preferencias = FXCollections.observableArrayList();
    private ObservableList<Preferencia> prefElegidas = FXCollections.observableArrayList();

    @FXML
    void addPreferencias(ActionEvent actionEvent){

        elegidos = preferenciaCheckListView.getCheckModel().getCheckedItems();

        for(String prefNombre : elegidos){

            Preferencia pref = preferenciasMgr.getPreferenciasByNombre(prefNombre);
            prefElegidas.add(pref);
            preferenciaTuristaMgr.addPreferenciaTurista(UserController.turistaIngresado, prefNombre);
        }

        showAlert("Intereses ingresados!", "Se actualizó su lista de intereses");
        close(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        preferencias = preferenciasMgr.getPreferenciasAgregar(UserController.turistaIngresado);

        ObservableList<String> nombres = FXCollections.observableArrayList();
        for(Preferencia pref : preferencias){

            nombres.add(pref.getNombre());
        }

        preferenciaCheckListView.setItems(nombres);
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
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
