package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.Main;
import um.ui.user.JavaFXApplication;

import java.io.IOException;

@Component
public class PantallaAminOperadorController {


    @FXML
    private Button btnVerExperiencias;

    @FXML
    private Button btnAgregarAdministrador;

    static Integer cambiarPassword;

    @FXML
    public void verExperiencias(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(TableViewExperiencias.class.getResourceAsStream("tableViewExperienciaAdminOp.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    public void addAdinistrador(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(AdminOperadorController.class.getResourceAsStream("agregarAdminoperador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void ajustes(ActionEvent actionEvent){
        //Aca va el fxml a ajustes adminOperador
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AjustesAdminOperador.class.getResourceAsStream("ajustesAdminOperador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, algo salió mal");
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

    @FXML
    void volver(ActionEvent actionEvent){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(JavaFXApplication.class.getResourceAsStream("User.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            UserController.adminIngresado = null;
            close(actionEvent);
        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, algo salió mal");
        }
    }




}
