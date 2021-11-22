package um.ui.cliente;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.UserMgr;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;

@Component
public class CambiarUserNameAdministrador {

    @Autowired
    private UserMgr userMgr;

    @FXML
    private TextField txtUserName;

    @FXML
    void cambiarUserName(ActionEvent actionEvent){
        try{
            String userName = txtUserName.getText();
            userMgr.cambiarUserName(UserController.adminIngresado, userName);

            showAlert("Cambios ingresados", "Se actualizó correctamente el nombre de usuario");
            close(actionEvent);

        }catch (InvalidInformation e){
            showAlert("ERROR", "No puede dejar vacío el campo");
        }catch (ClassAlreadyExists e){
            showAlert("ERROR", "Ya existe un usuario con el nombre de usuario ingresado");
        }catch (RepitedMail e){
            showAlert("ERROR", "No puedes ingresar el mismo nombre de usuario que tenías");
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
