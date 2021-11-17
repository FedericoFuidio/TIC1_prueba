package um.ui.cliente;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.AdminOperadorMgr;
import um.business.OperadorMgr;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;

@Component
public class OperadorController {

    @Autowired
    OperadorMgr operadorMgr;

    @Autowired
    AdminOperadorMgr adminOperadorMgr;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtDescriprtion;

    @FXML
    private TextField txtMail;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtWebsite;


    @FXML
    private TextField txtPicture;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClose;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addOperador(ActionEvent event) {
        try{

            String mail = txtMail.getText();
            String name = txtName.getText();
            String foto = txtPicture.getText();
            String phone = txtPhone.getText();
            String descripcion = txtDescriprtion.getText();
            String sitioWeb = txtWebsite.getText();
            String ubicacion = txtLocation.getText();


            operadorMgr.addOperador(mail, foto, name, phone, descripcion, sitioWeb, ubicacion);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AdminOperadorController.class.getResourceAsStream("agregarAdminoperador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            showAlert("Usuario registrado", "Se agrego existosamente el usuario!");

            close(event);

        } catch (RepitedMail e){
            showAlert(
                    "Mail ya usado por otro usuario",
                    "Ingrese otro mail");
        }
        catch (InvalidInformation e){
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        } catch(Exception e){
            showAlert("Ocurrio un error", "Estamos trabajando para solucionar el problema");
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
        txtLocation.setText(null);
        txtName.setText(null);
        txtPhone.setText(null);
        txtMail.setText(null);
        txtPassword.setText(null);
        txtPicture.setText(null);
        txtDescriprtion.setText(null);
        txtWebsite.setText(null);
    }
}
