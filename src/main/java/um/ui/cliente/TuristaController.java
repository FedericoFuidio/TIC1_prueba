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
import um.business.TuristaMgr;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;
import um.business.exception.RepitedUserName;

@Component
public class TuristaController {

    @Autowired
    TuristaMgr turistaMgr;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordValidation;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtPais;

    @FXML
    private TextField txtPassport;


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addTurista(ActionEvent actionEvent) {

        try {

            String mail = txtMail.getText();
            String userName = txtUserName.getText();
            String password = txtPassword.getText();
            String name = txtName.getText();
            String apellido = txtApellido.getText();
            String pais = txtPais.getText();
            Long passport = Long.parseLong(txtPassport.getText());


            turistaMgr.addTurista(mail, userName, password, name, apellido, pais, passport);

            showAlert("Usuario registrado", "Se agrego existosamente el usuario!");

            close(actionEvent);

        } catch (RepitedMail e) {
            showAlert(
                    "Mail ya usado por otro usuario",
                    "Ingrese otro mail");
        } catch (RepitedUserName e) {
            showAlert(
                    "Nombre de usuario ya existente",
                    "Ingrese otro nombre de usuario");
        } catch (InvalidInformation e) {
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        } //catch (Exception e){
        //showAlert(
        //"Información invalida!",
        //"Revise los datos ingresados");
        //}

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {
        txtPais.setText(null);
        txtUserName.setText(null);
        txtName.setText(null);
        txtApellido.setText(null);
        txtMail.setText(null);
        txtPassword.setText(null);
        txtPassport.setText(null);
    }
}
