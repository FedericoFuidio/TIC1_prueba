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
import um.business.UserMgr;
import um.business.exception.InvalidInformation;

@Component
public class IngresarPasswordAdministrador {



    @Autowired
    private UserMgr userMgr;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void continuar(ActionEvent actionEvent){

        try{
            String password = txtPassword.getText();
            userMgr.validarPassword(UserController.adminIngresado, password);
            if(PantallaAdminController.cambiarPassword == 1){
                try{
                    //Aca va el fxml a cambiar password
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                    Parent root = fxmlLoader.load(CambiarPasswordAdministrador.class.getResourceAsStream("cambiarPasswordAdministrador.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    PantallaAminOperadorController.cambiarPassword = null;
                    close(actionEvent);
                }catch(Exception e){
                    showAlert("ERROR", "Lo sentimos, algo salió mal");
                }
            }
            else if(PantallaAdminController.cambiarPassword == 2) {
                //Aca va el fxml a cambiar username:
                try {
                    //Aca va el fxml a cambiar password
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                    Parent root = fxmlLoader.load(CambiarUserNameAdministrador.class.getResourceAsStream("cambiarUserNameAdministrador.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();

                    PantallaAminOperadorController.cambiarPassword = null;
                    close(actionEvent);
                } catch (Exception e) {
                    showAlert("ERROR", "Lo sentimos, algo salió mal");
                }
            }
        }catch (InvalidInformation e){
            showAlert("ERROR", "Contraseña incorrecta");
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
