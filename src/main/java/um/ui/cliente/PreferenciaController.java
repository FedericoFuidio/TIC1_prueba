package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.PreferenciasMgr;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;

@Component
public class PreferenciaController {

    @Autowired
    PreferenciasMgr preferenciasMgr;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    void addPreferenciaGeneral(ActionEvent actionEvent){

        try{
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            preferenciasMgr.addPreferenciaGeneral(nombre, descripcion);

            showAlert("Preferencia registrada", "Se agregó una nueva preferencia general");

            close(actionEvent);

        } catch (ClassAlreadyExists e){

            showAlert("Preferencia ya registrada en el sistema",
                    "ya existe una preferencia con el nombre ingresado");
        } catch (InvalidInformation e){
            showAlert("Información invalida!",
                    "Todos los datos son obligaorios");
        }
    }


    @FXML
    void addPreferenciaEspecifica(ActionEvent actionEvent){

        try{
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            preferenciasMgr.addPreferenciaEspecifica(nombre, descripcion);

            showAlert("Preferencia registrada", "Se agregó una nueva preferencia especifica");

            close(actionEvent);

        } catch (ClassAlreadyExists e){

            showAlert("Preferencia ya registrada en el sistema",
                    "ya existe una preferencia con el nombre ingresado");
        } catch (InvalidInformation e){
            showAlert("Información invalida!",
                    "Todos los datos son obligaorios");
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {
        txtDescripcion.setText(null);
        txtNombre.setText(null);
    }

    @FXML
    void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void choosePreferencias(ActionEvent event){

    }


}
