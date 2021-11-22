package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.TuristaMgr;

@Component
public class PerfilTuristaController {

    @Autowired
    private TuristaMgr turistaMgr;

    @FXML
    private Button btnChangePassword;

    @FXML
    private Button btnChangeUsername;

    @FXML
    void cambiarPassword(ActionEvent actionEvent){

        PantallaPrincipalController.cambiarUserName = 1;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(IngresarPassword.class.getResourceAsStream("ingresarPassword.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            close(actionEvent);
        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, algo salio mal");
        }


    }


    @FXML
    void cambiarUserName(ActionEvent actionEvent){

        PantallaPrincipalController.cambiarUserName = 2;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(IngresarPassword.class.getResourceAsStream("ingresarPassword.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            close(actionEvent);
        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, algo salio mal");
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
