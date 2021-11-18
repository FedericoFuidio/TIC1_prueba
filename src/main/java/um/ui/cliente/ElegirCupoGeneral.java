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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ExperienciaMgr;
import um.business.exception.InvalidInformation;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class ElegirCupoGeneral implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;


    @FXML
    private ComboBox<String> horaAperturaString;

    @FXML
    private ComboBox<String> horaCierreString;

    @FXML
    private ComboBox<String> cmbDia;

    @FXML
    private TextField txt_personas;

    @FXML
    private Button agregar;

    @FXML
    private Button cancelar;



    @FXML
    void addCupoGeneral(ActionEvent actionEvent){

        try{

            Integer personas = Integer.parseInt(txt_personas.getText());
            LocalTime horaApertura = LocalTime.parse(horaAperturaString.getValue());
            LocalTime horaCierre = LocalTime.parse(horaCierreString.getValue());
            String dia_elegido = cmbDia.getValue();

            //Vemos que numero de dia elegio:
            int numero = 1;
            ObservableList<String> dias = FXCollections.observableArrayList();
            dias.add("Lunes");
            dias.add("Martes");
            dias.add("Miercoles");
            dias.add("Jueves");
            dias.add("Viernes");
            dias.add("Sabado");
            dias.add("Domingo");

            for(int i = 0; i < 6; i++){

                if(dia_elegido.equals(dias.get(i))){

                    numero = i+1;
                }
            }

            DayOfWeek dia = DayOfWeek.of(numero);


            experienciaMgr.addCupoGeneral(TableViewExperiencias.seleccionada, horaApertura, horaCierre, dia, personas);
            close(actionEvent);

            showAlert("Cupo ingresado correctamente", "Se ingreso correctamente el cupo");




        }catch (InvalidInformation e){

            showAlert("Ocurrio un error", "La hora inicial debe ser menor a la hora final");

        }catch (Exception e){
            e.printStackTrace();
            showAlert("ERROR", "En el campo cupo debe ingresar un numero entero");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> lista_horas = FXCollections.observableArrayList();
        for(int i = 0; i < 24; i++){

            if(i < 10){

                String hora = "0" + i + ":00";
                lista_horas.add(hora);

            }else{
                String hora = i + ":00";
                lista_horas.add(hora);
            }

        }

        horaAperturaString.setItems(lista_horas);
        horaCierreString.setItems(lista_horas);

        ObservableList<String> dias = FXCollections.observableArrayList();
        dias.add("Lunes");
        dias.add("Martes");
        dias.add("Miercoles");
        dias.add("Jueves");
        dias.add("Viernes");
        dias.add("Sabado");
        dias.add("Domingo");

        cmbDia.setItems(dias);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {

    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
