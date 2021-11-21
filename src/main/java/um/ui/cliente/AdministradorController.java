package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.UserMgr;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;

@Component
public class AdministradorController {

    @Autowired
    private UserMgr userMgr;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtMail;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    public void addAdministrador(ActionEvent actionEvent){

        try{
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String mail = txtMail.getText();
            String password = txtPassword.getText();
            String userName = txtUserName.getText();

            userMgr.addAdministrador(nombre, apellido, mail, password, userName);

            showAlert("Admin ingresado", "Se agregó un nuevo administrador de la aplicación");
            close(actionEvent);
        }catch (InvalidInformation e){
            showAlert("Datos faltantes", "Todos los datos son obligatorios");
        }catch (ClassAlreadyExists e){
            showAlert("Usuario existente", "Ya existe un usuario con el nombre de usuario ingresado");
        }catch (Exception e){
            showAlert("Usuario existente", "Ya existe un usuario con el mail ingresado");
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
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtPassword.setText(null);
        txtMail.setText(null);
        txtUserName.setText(null);
    }

    public void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
