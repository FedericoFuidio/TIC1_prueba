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
    private TextField txtAddress;

    @FXML
    private TextField txtDocument;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

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
        if (txtDocument.getText() == null || txtDocument.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("") ||
                txtAddress.getText() == null || txtAddress.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el ingreso.");

        } else {

            try {

                Long document = Long.valueOf(txtDocument.getText());
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String address = txtAddress.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();

                try {

                    userMgr.addUser(document, nombre, apellido, mail, address, password);

                    showAlert("Cliente agregado", "Se agrego con exito el cliente!");

                    close(event);
                } catch (Exception e) {
                    showAlert(
                            "Informacion invalida !",
                            "Se encontro un error en los datos ingresados.");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos !",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }

    private void clean() {
        txtDocument.setText(null);
        txtAddress.setText(null);
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

}
