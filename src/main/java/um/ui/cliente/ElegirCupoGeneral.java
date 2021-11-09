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
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ExperienciaMgr;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class ElegirCupoGeneral implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;

    @FXML
    private ComboBox<LocalTime> cmbHoraApertura;

    @FXML
    private ComboBox<LocalTime> cmbHoraCierre;

    @FXML
    private Button agregar;

    @FXML
    private Button cancelar;

    private ObservableList<String> horas = FXCollections.observableArrayList();
    private ObservableList<LocalTime> horas_time = FXCollections.observableArrayList();

    @FXML
    void addCupoGeneral(ActionEvent actionEvent){

        try{

            LocalTime horaApertura = cmbHoraApertura.getValue();
            LocalTime horaCierre = cmbHoraCierre.getValue();

            experienciaMgr.addCupoGeneral(TableViewExperiencias.seleccionada, horaApertura, horaCierre);
            close(actionEvent);

            showAlert("Cupo ingresado correctamente", "Se ingreso correctamente el cupo");




        }catch (Exception e){

            showAlert("Ocurrio un error", "Revise los datos, recuerde que la hora inicial debe ser menor que la hora final");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < 100; i++){

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
        cmbHoraApertura.setValue(null);
        cmbHoraCierre.setValue(null);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
