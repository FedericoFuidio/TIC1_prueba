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
import java.util.ResourceBundle;

@Component
public class ElegirCupoGeneral implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;

    @FXML
    private ComboBox<Integer> hora_inicial_txt;

    @FXML
    private ComboBox<Integer> hora_final_txt;

    @FXML
    private Button agregar;

    @FXML
    private Button cancelar;

    @FXML
    void addCupoGeneral(ActionEvent actionEvent){

        try{

            Integer hora_inicial = hora_inicial_txt.getValue();
            Integer hora_final = hora_final_txt.getValue();

            experienciaMgr.addCupoGeneral(TableViewExperiencias.seleccionada, hora_inicial, hora_final);
            close(actionEvent);

            showAlert("Cupo ingresado correctamente", "Se ingreso correctamente el cupo");




        }catch (Exception e){

            showAlert("Ocurrio un error", "Revise los datos, recuerde que la hora inicial debe ser menor que la hora final");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Integer> temp = FXCollections.observableArrayList();
        for(int i = 0; i < 25; i++){
            temp.add(i);
        }

        hora_inicial_txt.setItems(temp);
        hora_final_txt.setItems(temp);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {
        hora_inicial_txt.setValue(null);
        hora_final_txt.setValue(null);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
