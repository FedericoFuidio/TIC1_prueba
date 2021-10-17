package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ExperienciaMgr;
import um.business.OperadorMgr;
import um.business.exception.InvalidInformation;
import um.business.exception.UserNotFound;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

@Component
public class ExperienciaController implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;

    @Autowired
    OperadorMgr operadorMgr;

    @FXML
    private Button btnImg;

    @FXML
    public TextField txtNombre;

    @FXML
    private TextField txtUbicacion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtMailoperador;

    @FXML
    public Label nombreImagen;

    private FileChooser fileChooser;

    @FXML
    void addExperiencia(ActionEvent actionEvent){

        try{

            String nombre = txtNombre.getText();
            String ubicacion = txtUbicacion.getText();
            String descripcion = txtDescripcion.getText();
            byte[] foto = imagen;
            String mail = txtMailoperador.getText();

            if (imagen == null) {

                showAlert("Todos los datos son obligatorios",
                        "Debes ingresar una imagen de la experiencia");
            }

            else {


                experienciaMgr.addExperiencia(nombre, ubicacion, descripcion, foto, mail);

                showAlert("Experiencia registrada", "Se agrego exitosamente la experiencia!");

                close(actionEvent);
            }

        } catch(InvalidInformation e){
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        } catch (UserNotFound e){
            showAlert(
                    "Operador inexistente!",
                    "No pudimos encontrar al operador en la base de datos"
            );


        } catch (Exception e) {
            showAlert(
                    "Algo salio mal!",
                    "Por favor, revise los datos ingresados"
            );
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
        txtNombre.setText(null);
        txtDescripcion.setText(null);
//        txtFoto.setText(null);
        txtUbicacion.setText(null);
        txtMailoperador.setText(null);
    }


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addImagen(ActionEvent actionEvent) {
        Scene sceneActual =((Node)actionEvent.getSource()).getScene();
        Stage stage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        /*
        VBox vBox= new VBox();
        Scene scene = new Scene(vBox,960,600);
        stage.setScene(scene);
        stage.show();
        while(selectedFile==null);
        stage.setScene(sceneActual);
        stage.show();

         */
        Path url = selectedFile.toPath();
        nombreImagen.setText(url.getFileName().toString());
        try {
            imagen = Files.readAllBytes(url);
        }catch (IOException e){

        }
    }

    private byte[] imagen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser = new FileChooser();
    }
}
