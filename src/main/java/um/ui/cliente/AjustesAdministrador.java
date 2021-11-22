package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.Main;

@Component
public class AjustesAdministrador {

    @FXML
    void cambiarPassword(ActionEvent actionEvent){
        try{
            PantallaAdminController.cambiarPassword = 1;
            //Aca va el fxml a IngresarPasswordAdminOperador
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(IngresarPasswordAdministrador.class.getResourceAsStream("ingresarPasswordAdministrador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            close(actionEvent);
        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, ocurrió un error inesperado");
        }
    }

    @FXML
    void cambiarUserName(ActionEvent actionEvent){
        try{
            PantallaAdminController.cambiarPassword = 2;
            //Aca va el fxml a IngresarPasswordAdminOperador
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(IngresarPasswordAdministrador.class.getResourceAsStream("ingresarPasswordAdministrador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            close(actionEvent);

        }catch (Exception e){
            showAlert("ERROR", "Lo sentimos, ocurrió un error inesperado");
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
