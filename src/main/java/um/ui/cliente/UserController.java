package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import um.business.UserMgr;
//import um.edu.uy.business.exceptions.ClientAlreadyExists;
//import um.edu.uy.business.exceptions.InvalidClientInformation;
import org.springframework.stereotype.Component;


@Component
public class UserController {

    @Autowired
    private UserMgr userMgr;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtPassword;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addClient(ActionEvent event) {
        if (txtCountry.getText() == null || txtCountry.getText().equals("") ||
                txtApellido.getText() == null || txtApellido.getText().equals("") ||
                txtUsername.getText() == null || txtUsername.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtNombre.getText() == null || txtNombre.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el registro");

        } else {

            try {

                String userName = txtUsername.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String country = txtCountry.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();

                try {

                    userMgr.addUser(userName, nombre, apellido, mail, country, password);

                    showAlert("Usuario registrado", "Se agrego existosamente el usuario!");

                    close(event);
                } catch (Exception e) {
                    showAlert(
                            "Informacion invalida!",
                            "Hay un error en los datos ingresados");
                }
            } catch (Exception e){
                showAlert(
                        "Informacion invalida!",
                        "Hay un error en los datos ingresados");
            }

        }

    }

    private void clean() {
        txtCountry.setText(null);
        txtUsername.setText(null);
        txtNombre.setText(null);
        txtApellido.setText(null);
        txtMail.setText(null);
        txtPassword.setText(null);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void IngresarUser(ActionEvent event) {
        if (txtUsername.getText() == null || txtUsername.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingreso el nombre de usuario o la contraseña");
        }

        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            userMgr.IngresarUser(userName, password);

            showAlert("Bienvenido " + userName, "");
        } catch (Exception e){

            showAlert("Conraseña incorrecta", "Intente nuevamente");
        }
    }

}
