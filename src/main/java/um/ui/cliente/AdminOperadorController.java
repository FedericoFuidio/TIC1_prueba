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
import um.business.AdminOperadorMgr;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;


@Component
public class AdminOperadorController {

    @Autowired
    AdminOperadorMgr adminOperadorMgr;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    public void addAdministrador(ActionEvent event){
        try{


            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String password = txtPassword.getText();
            String userName = txtUserName.getText();



            if(OperadorController.nuevo != null) {
                adminOperadorMgr.addAdminOperador(nombre, apellido, password, OperadorController.nuevo, userName);
                OperadorController.nuevo = null;
            }else{
                adminOperadorMgr.addAdminOperador(nombre, apellido, password, UserController.operadorAsociado, userName);
            }

            showAlert("Administrador registrado", "Se agrego existosamente el Administrador!");
            close(event);


        } catch (InvalidInformation e){
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        } catch (ClassAlreadyExists e){
            showAlert("Información invalida!",
                    "Ya existe un usuario con el nombre de usuario ingresado");
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
    }

    public void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
