package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ReservaMgr;
import um.business.entities.Reserva;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

@Component
public class TableViewReservas implements Initializable {

    @Autowired
    ReservaMgr reservaMgr;

    @FXML
    private TableView<Reserva> reservaTableView;

    @FXML
    private TableColumn<Reserva, String> turista;

    @FXML
    private TableColumn<Reserva, Integer> cantidad;

    @FXML
    private TableColumn<Reserva, Date> fecha;

    @FXML
    private TableColumn<Reserva, Boolean> aceptada;

    @FXML
    private Button btnBloquear;

    @FXML
    private Button btnAceptar;

    @FXML
    private TextField filterField;

    private ObservableList<Reserva> reservas = FXCollections.observableArrayList();
    private ObservableList<Reserva> dataList = FXCollections.observableArrayList();

    static Reserva seleccionada = new Reserva();

    @FXML
    void getSelected(MouseEvent mouseEvent){
        seleccionada = reservaTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void updateTable(){

        turista.setCellValueFactory(new PropertyValueFactory<>("turista"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        aceptada.setCellValueFactory(new PropertyValueFactory<>("aceptada"));


        reservas.clear();
        /*
        Iterable<Reserva> iterableReservas = reservaMgr.getReservas();

        for (Reserva s : iterableReservas) {
            System.out.println(s.getFecha());
            reservas.add(s);
        }

         */
        reservas = reservaMgr.getReservasByExperiencia(TableViewExperiencias.seleccionada);

        reservaTableView.setItems(reservas);

    }

    @FXML
    void bloquear(ActionEvent actionEvent){

        if(seleccionada == null || seleccionada.getTurista() == null){
            showAlert("ERROR", "seleccione una reserva");
        }else{
            reservaMgr.bloquear(seleccionada);
            updateTable();
            searchReserva();
            seleccionada = null;
            showAlert("Bloqueada", "Puede desbloquear la reserva en el boton aceptar");
        }
    }

    @FXML
    void validar(ActionEvent actionEvent){
        if(seleccionada == null || seleccionada.getTurista() == null){
            showAlert("ERROR", "seleccione una reserva");
        }else{
            reservaMgr.validar(seleccionada);
            updateTable();
            searchReserva();
            seleccionada = null;
            showAlert("Reserva aceptada", "Se aceptó la reserva");
        }
    }

    @FXML
    void searchReserva(){

        turista.setCellValueFactory(new PropertyValueFactory<>("turista"));
        cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        aceptada.setCellValueFactory(new PropertyValueFactory<>("aceptada"));


        dataList.clear();

        /*
        Iterable<Reserva> iterableReservas = reservaMgr.getReservas();

        for (Reserva s : iterableReservas) {
            System.out.println(s.getFecha());
            dataList.add(s);
        }

         */

        dataList = reservaMgr.getReservasByExperiencia(TableViewExperiencias.seleccionada);
        reservaTableView.setItems(dataList);

        FilteredList<Reserva> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(person.getCantidad()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (String.valueOf(person.getFecha()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else{return false;}
            });
        });

        SortedList<Reserva> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(reservaTableView.comparatorProperty());
        reservaTableView.setItems(sortedData);

    }

    @FXML
    void close(javafx.event.ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchReserva();

    }



}
