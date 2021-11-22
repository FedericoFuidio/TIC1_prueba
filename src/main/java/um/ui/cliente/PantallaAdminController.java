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

import java.io.IOException;

@Component
public class PantallaAdminController {

    @FXML
    private Button verOperadores;

    @FXML
    private Button verExperiencias;

    @FXML
    private Button addAdmin;

    static Integer cambiarPassword;

    @FXML
    void verOperadores(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(TableController.class.getResourceAsStream("tablaPrueba.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void  verExperiencias(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(TableViewExperiencias.class.getResourceAsStream("tableViewExperienciaAdministrador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void addAdministrador(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AdminOperadorController.class.getResourceAsStream("agregarAdministrador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, algo salió mal");
        }


    }

    @FXML
    void ajustes(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AjustesAdministrador.class.getResourceAsStream("ajustesAdministrador.fxml"));
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



}
