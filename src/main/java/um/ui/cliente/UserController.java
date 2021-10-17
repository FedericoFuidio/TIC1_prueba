package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.UserMgr;
import um.business.entities.Turista;
import um.business.entities.User;

import java.time.LocalDate;

//import um.edu.uy.business.exceptions.ClientAlreadyExists;
//import um.edu.uy.business.exceptions.InvalidClientInformation;


@Component
public class UserController {


    @Autowired
    private UserMgr userMgr;

    @FXML
    private DatePicker pickerFecha;

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
    private PasswordField txtPassword;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    static User usuarioIngresado;
    static Turista turistaIngresado;

    @FXML
    void addClient(ActionEvent event) {

        //Controlo que las entradas no sean nulas:
        if (txtCountry.getText() == null || txtCountry.getText().equals("") ||
                txtApellido.getText() == null || txtApellido.getText().equals("") ||
                txtUsername.getText() == null || txtUsername.getText().equals("") ||
                txtMail.getText() == null || txtMail.getText().equals("") ||
                txtNombre.getText() == null || txtNombre.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("") ||
                pickerFecha.getValue() == null) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingresaron los datos necesarios para completar el registro");

        } else {

            try {

                LocalDate birthDay = pickerFecha.getValue();
                String userName = txtUsername.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String country = txtCountry.getText();
                String mail = txtMail.getText();
                String password = txtPassword.getText();

                try {

                    //Agrego el usuario, en caso de una excepcion lanzo una alerta de informacion invalida:
                    userMgr.addUser(nombre, apellido, userName, mail, birthDay, country, password);

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

        //Verifico que los datos ingrasados son no nulos:
        if (txtUsername.getText() == null || txtUsername.getText().equals("") ||
                txtPassword.getText() == null || txtPassword.getText().equals("")) {

            showAlert(
                    "Datos faltantes!",
                    "No se ingreso el nombre de usuario o la contraseña");
        }

        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        try {

            //Ingreso el usuario, si el resultado de la operacion es true, la contraseña es correcta,
            //Si se lanza una excepcion, el usuario ingresado no existe:
            usuarioIngresado = userMgr.IngresarUser(userName, password);
            turistaIngresado = userMgr.obtenerTurista(usuarioIngresado);

            if(usuarioIngresado != null && turistaIngresado != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(PantallaPrincipalController.class.getResourceAsStream("pantallaPrincipal.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } else{
                showAlert("Contraseña incorrecta", "Intente nuevamente");
            }
        } catch (Exception e){
            e.printStackTrace();
            showAlert("Usuario no encontrado", "Intente nuevamente");
        }


    }

}
