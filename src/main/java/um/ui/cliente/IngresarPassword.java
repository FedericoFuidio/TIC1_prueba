package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.TuristaMgr;
import um.business.exception.InvalidInformation;

@Component
public class IngresarPassword {

    @Autowired
    private TuristaMgr turistaMgr;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void decicion(ActionEvent actionEvent){

        if(PantallaPrincipalController.cambiarUserName == 1) {

            try {

                String password = txtPassword.getText();
                turistaMgr.validar(UserController.turistaIngresado, password);
                //Aca va el fxml al cambiarPassword
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(CambiarPassword.class.getResourceAsStream("cambiarPassword.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();


                PantallaPrincipalController.cambiarUserName = null;
                close(actionEvent);
            } catch (InvalidInformation e) {
                showAlert("ERROR", "Contraseña incorrecta");
            } catch (Exception e) {
                showAlert("ERROR", "Lo sentimos, sucedio un error inesperado");
            }
        }

        else if(PantallaPrincipalController.cambiarUserName == 2){
            try{
                String password = txtPassword.getText();
                turistaMgr.validar(UserController.turistaIngresado, password);
                //Aca va el fxml a cambiar username

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(CambiarUserName.class.getResourceAsStream("cambiarUserName.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                PantallaPrincipalController.cambiarUserName = null;
                close(actionEvent);

            }catch (InvalidInformation e){
                showAlert("ERROR", "Contraseña incorrecta");
            }catch (Exception e){
                showAlert("ERROR", "Lo sentimos, ocurrio un error inesperado");
            }
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
