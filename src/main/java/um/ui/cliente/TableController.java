package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.OperadorMgr;
import um.business.entities.Operador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TableController implements Initializable {

    @Autowired
    private OperadorMgr operadorMgr;

    @FXML
    private TableView<Operador> operatorsTable;

    @FXML
    private Button btnBlock;

    @FXML
    private Button BtnValidate;

    @FXML
    private TableColumn<Operador, Integer> id;

    @FXML
    public TableColumn<Operador, String> name;

    @FXML
    private TableColumn<Operador, String> foto;

    @FXML
    private TableColumn<Operador, String> descripcion;

    @FXML
    private TableColumn<Operador, String> mail;

    @FXML
    private TableColumn<Operador, String> phone;

    @FXML
    private TableColumn<Operador, String> ubicacion;

    @FXML
    private TableColumn<Operador, Boolean> validado;

    @FXML
    private TextField filterField;

    @FXML
    private Button btnAddOperators;

    @FXML
    void blockUsers(ActionEvent event) {
        operadorMgr.setValidado(seleccionado, false);
        updateTable();
        searchOperator();
    }

    @FXML
    void validateUsers(ActionEvent event) {
        operadorMgr.setValidado(seleccionado, true);
        updateTable();
        searchOperator();
    }

    static Operador seleccionado = new Operador();

    @FXML
    void getSelected(MouseEvent mouse){
        seleccionado = operatorsTable.getSelectionModel().getSelectedItem();
    }

    private ObservableList<Operador> operadores = FXCollections.observableArrayList();
    private ObservableList<Operador> dataList = FXCollections.observableArrayList();


    @FXML
    void agregarAdminOpAction(ActionEvent event) throws Exception {

        if(seleccionado.getMail() == null){
            showAlert("ERROR", "Seleccione un operador");
        }

        else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(TableController.class.getResourceAsStream("agregarAdminoperador.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void updateTable(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        validado.setCellValueFactory(new PropertyValueFactory<>("validado"));
        foto.setCellValueFactory(new PropertyValueFactory<>("foto"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));



        operadores.clear();
        Iterable<Operador> iterableOperadores = operadorMgr.GetOperadores();
        for (Operador s : iterableOperadores) {
            operadores.add(s);
        }

        operatorsTable.setItems(operadores);
    }

    @FXML
    void searchOperator(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        validado.setCellValueFactory(new PropertyValueFactory<>("validado"));
        foto.setCellValueFactory(new PropertyValueFactory<>("foto"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));

        dataList.clear();
        Iterable<Operador> iterableOperadores = operadorMgr.GetOperadores();
        for (Operador s : iterableOperadores) {
            dataList.add(s);
        }

        operatorsTable.setItems(dataList);
        FilteredList<Operador> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
            if (newValue == null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(person.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if (String.valueOf(person.getPhone()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if (person.getDescripcion().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if (person.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }/*else if (person.getFoto().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }*/else if (person.getUbicacion().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else{return false;}
        });
        });

        SortedList<Operador> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(operatorsTable.comparatorProperty());
        operatorsTable.setItems(sortedData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchOperator();
    }

    @FXML
    void agregarOperadorAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(OperadorController.class.getResourceAsStream("agregarOperador.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void close(ActionEvent actionEvent) {
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
}
