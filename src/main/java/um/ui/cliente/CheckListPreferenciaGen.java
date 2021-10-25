package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.CheckListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.PreferenciaTuristaMgr;
import um.business.PreferenciasMgr;
import um.business.entities.PreferenciaGeneral;
import um.business.entities.Turista;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CheckListPreferenciaGen implements Initializable {

    @Autowired
    PreferenciasMgr preferenciasMgr;

    @Autowired
    PreferenciaTuristaMgr preferenciaTuristaMgr;

    @FXML
    private Button btnSiguiente;

    @FXML
    private Button btnAtras;

    ObservableList<String> elegidos = FXCollections.observableArrayList();

    @FXML
    private CheckListView<String> preferenciaGeneralCheckListView;

    private ObservableList<PreferenciaGeneral> preferencias = FXCollections.observableArrayList();
    static ObservableList<PreferenciaGeneral> prefElegidas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        preferencias = preferenciasMgr.getPreferenciasGenerales();

        ObservableList<String> nombre = FXCollections.observableArrayList();
        for(PreferenciaGeneral pg : preferencias){
            nombre.add(pg.getNombre());
        }

        preferenciaGeneralCheckListView.setItems(nombre);

    }

    @FXML
    void addPreferencias(ActionEvent event) throws IOException {

        Turista turista = TuristaController.turistaIngresado;

        elegidos = preferenciaGeneralCheckListView.getCheckModel().getCheckedItems();
        for(String pgNombre : elegidos){

            PreferenciaGeneral pg = preferenciasMgr.getPreferenciaGeneralByNombre(pgNombre);
            prefElegidas.add(pg);
            preferenciaTuristaMgr.addPreferenciaTurista(turista, pgNombre);

        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(CheckListPreferenciaEspecifica.class.getResourceAsStream("ElegirPreferenciasEspecificas.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

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
