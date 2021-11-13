package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ReservaMgr;
import um.business.entities.Reserva;
import um.business.exception.InvalidInformation;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CalificacionController implements Initializable {

    @Autowired
    private ReservaMgr reservaMgr;

    @FXML
    private ComboBox<String> cmbPuntaje;

    @FXML
    private TextArea txtComentario;

    @FXML
    private ComboBox<String> cmbEsPublica;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;

    private Reserva res;

    public void setData(Reserva reserva){
        res = reserva;
    }

    @FXML
    void addCalificacion(ActionEvent actionEvent){

        try{
            int puntaje = Integer.parseInt(cmbPuntaje.getValue());
            String comentario = txtComentario.getText();
            boolean esPublica = false;
            if(cmbEsPublica.getValue().equals("si")){
                esPublica = true;
            }

            reservaMgr.calificar(res, UserController.turistaIngresado, puntaje, comentario, esPublica);

            showAlert("Calificación agregada", "Se agregó la calificacion");

            close(actionEvent);

        }catch (InvalidInformation e){
            showAlert("ERROR", "Debes ingresar todos los datos");
        }catch (Exception e){
            showAlert("ERROR", "Ocurrio un error inesperado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> puntajes = FXCollections.observableArrayList();
        puntajes.add("1");
        puntajes.add("2");
        puntajes.add("3");
        puntajes.add("4");
        puntajes.add("5");
        cmbPuntaje.setItems(puntajes);

        ObservableList<String> publico = FXCollections.observableArrayList();
        publico.add("si");
        publico.add("no");
        cmbEsPublica.setItems(publico);



    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
