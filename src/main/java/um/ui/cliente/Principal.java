package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.Main;
//import UI.cliente.ClientController;

@Component
public class Principal {

    @FXML
    private MenuItem mItemAgregarCliente;

    //Defino la accion agregarClient, en agregarUsuario.fxml defino las operaciones que quiero que
    //realize esta accion (addUser), y la clase donde esta la funcion (UserController):
    @FXML
    void agregarClientAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(TuristaController.class.getResourceAsStream("agregarUsuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Defino la accion ingresarUser, en ingresarUsuario.fxml, pongo el nombre de la funcion que quiero usar,
    //(IngresarUser), y la clase donde esta la funcion (UserController)
    @FXML
    void ingresarUserAction(ActionEvent event) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(UserController.class.getResourceAsStream("ingresarUsuario.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }


}
