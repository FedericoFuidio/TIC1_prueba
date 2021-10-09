package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ExperienciaGeneralMgr;
import um.business.OperadorMgr;
import um.business.entities.Operador;
import um.business.exception.InvalidInformation;

@Component
public class ExperienciaController {

    @Autowired
    ExperienciaGeneralMgr experienciaGeneralMgr;

    @Autowired
    OperadorMgr operadorMgr;

    @FXML
    public TextField txtNombre;

    @FXML
    private TextField txtUbicacion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtFoto;

    @FXML
    private TextField txtMailoperador;

    @FXML
    void addExperiencia(ActionEvent actionEvent){

        try{

            String nombre = txtNombre.getText();
            String ubicacion = txtUbicacion.getText();
            String descripcion = txtDescripcion.getText();
            String foto = txtFoto.getText();
            String mail = txtMailoperador.getText();

            System.out.println(descripcion);
            System.out.println(foto);

            Operador operador = operadorMgr.getByMail(mail);

            experienciaGeneralMgr.addExperiencia(nombre, ubicacion, descripcion, foto, operador);

            close(actionEvent);

        } catch(InvalidInformation e){
            showAlert(
                    "Información invalida!",
                    "Todos los datos son oblgatorios");
        } catch (Exception e){
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
        txtFoto.setText(null);
        txtUbicacion.setText(null);
        txtMailoperador.setText(null);
    }


    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
