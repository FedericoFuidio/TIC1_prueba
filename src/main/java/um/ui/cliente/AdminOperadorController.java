package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.AdminOperadorMgr;
import um.business.entities.Operador;

import java.awt.event.ActionEvent;

@Component
public class AdminOperadorController {

    @Autowired
    AdminOperadorMgr adminOperadorMgr;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    void addAdministrador(ActionEvent event, Operador operador){
        try{
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String password = txtPassword.getText();

            adminOperadorMgr.addAdminOperador(nombre, apellido, password, operador);
        } catch ()

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
}
