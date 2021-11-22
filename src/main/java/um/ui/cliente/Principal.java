package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.Main;

import java.net.URL;
import java.util.ResourceBundle;
//import UI.cliente.ClientController;

@Component
public class Principal implements Initializable {

    @FXML
    private MenuItem mItemAgregarCliente;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button registrarse;

    @FXML
    private Button ingresar;

    @FXML
    private Button tablaOperadores;

    @FXML
    private Button agregarOperador;

    @FXML
    private Button agregarExperiencia;

    //Defino la accion agregarClient, en agregarTurista.fxml defino las operaciones que quiero que
    //realize esta accion (addUser), y la clase donde esta la funcion (UserController):
    @FXML
    void agregarClientAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(TuristaController.class.getResourceAsStream("agregarTurista.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        close(event);


    }


    //Defino la accion ingresarUser, en ingresarUsuario.fxml, pongo el nombre de la funcion que quiero usar,
    //(IngresarUser), y la clase donde esta la funcion (UserController)
    @FXML
    void ingresarUserAction(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(UserController.class.getResourceAsStream("ingresarUsuario.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        close(event);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        URL ur = this.getClass().getResource("Estilo.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = ur.toExternalForm();
        anchorPane.getStylesheets().add(css);

         */

        /*

        String botones = "    -fx-background-color: \n" +
                "        linear-gradient(#00008b, #4b0082);" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,2,3,0;\n" +
                "    -fx-text-fill: #f5fffa;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 14px;\n" +
                "    -fx-padding: 10 20 10 20;";

        ingresar.setStyle(botones);
        registrarse.setStyle(botones);
        tablaOperadores.setStyle(botones);
        agregarExperiencia.setStyle(botones);

         */



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
