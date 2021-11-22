package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.TuristaMgr;
import um.business.exception.InvalidInformation;

@Component
public class CambiarPassword {

    @Autowired
    private TuristaMgr turistaMgr;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void cambiarPassword(ActionEvent actionEvent){

        try {

            String password = txtPassword.getText();

            turistaMgr.cambiarPassword(UserController.turistaIngresado, password);
            showAlert("Exito", "La contraseña se actualizó con exito");
            close(actionEvent);
        }catch (InvalidInformation e){
            showAlert("ERROR", "No puede dejar el campo vacio");
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
