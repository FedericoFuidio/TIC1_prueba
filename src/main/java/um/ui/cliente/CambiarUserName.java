package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.TuristaMgr;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedUserName;

@Component
public class CambiarUserName {

    @Autowired
    private TuristaMgr turistaMgr;

    @FXML
    private TextField txtuserName;

    @FXML
    void cambiarUserName(ActionEvent actionEvent){

        try{

            String userName = txtuserName.getText();

            turistaMgr.cambiarUserName(UserController.turistaIngresado, userName);


            showAlert("Exito", "El nombre de usuario se actualizo con exito");
            close(actionEvent);
        }catch (InvalidInformation e){
            showAlert("ERROR", "No puedes dejar el campo vacío");
        }catch (ClassAlreadyExists e){
            showAlert("Usuario existente", "Ya existe un usuario con el nombre de usuario ingresado");
        }catch (RepitedUserName e){
            showAlert("ERROR", "Debes ingresar un nombre de usuario distinto al actual");
        }
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }



    public void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
