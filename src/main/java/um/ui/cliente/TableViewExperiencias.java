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
import um.business.ExperienciaMgr;
import um.business.entities.Experiencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TableViewExperiencias implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;

    @FXML
    private TableView<Experiencia> experienciaTableView;

    @FXML
    private TableColumn<Experiencia, String> nombre;

    @FXML
    private TableColumn<Experiencia, String> ubicacion;

    @FXML
    private TableColumn<Experiencia, String> descripcion;

    @FXML
    private TableColumn<Experiencia, Boolean> validado;

    @FXML
    private TableColumn<Experiencia, Float> puntaje;

    @FXML
    private TableColumn<Experiencia, Integer> calificaciones;

    @FXML
    private Button BtnAddExperiencia;

    @FXML
    private TextField filterField;

    private ObservableList<Experiencia> experiencias = FXCollections.observableArrayList();
    private ObservableList<Experiencia> dataList = FXCollections.observableArrayList();


    static Experiencia seleccionada = new Experiencia();

    @FXML
    void getSelected(MouseEvent mouseEvent){
        seleccionada = experienciaTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    void agregarExperienciaAction(ActionEvent actionEvent) throws IOException {
        if(seleccionada.getNombre() == null){
            showAlert("ERROR", "Seleccione un operador");
        } else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(ExperienciaController.class.getResourceAsStream("agregarExperiencia.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    void agregarCupoAction(ActionEvent actionEvent) throws IOException {
        if (seleccionada.getNombre() == null) {
            showAlert("ERROR", "Seleccione un operador");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(ExperienciaController.class.getResourceAsStream("agregarExperiencia.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    public void updateTable(){
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        validado.setCellValueFactory(new PropertyValueFactory<>("validado"));
        puntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        calificaciones.setCellValueFactory(new PropertyValueFactory<>("calificaciones"));




        experiencias.clear();
        Iterable<Experiencia> iterableExperiencias = experienciaMgr.getExperienciaByOperador(UserController.operadorAsociado);
        for (Experiencia s : iterableExperiencias) {
            experiencias.add(s);
        }

        experienciaTableView.setItems(experiencias);
    }

    @FXML
    void searchExperiencia(){

        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ubicacion.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        validado.setCellValueFactory(new PropertyValueFactory<>("validado"));
        puntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));
        calificaciones.setCellValueFactory(new PropertyValueFactory<>("calificaciones"));

        dataList.clear();
        Iterable<Experiencia> iterableExperiencias = experienciaMgr.getExperienciaByOperador(UserController.operadorAsociado);
        for (Experiencia s : iterableExperiencias) {
            dataList.add(s);
        }

        experienciaTableView.setItems(dataList);

        FilteredList<Experiencia> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getNombre()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (person.getDescripcion().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if (String.valueOf(person.getUbicacion()).toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else{return false;}
            });
        });

        SortedList<Experiencia> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(experienciaTableView.comparatorProperty());
        experienciaTableView.setItems(sortedData);

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
        searchExperiencia();

    }
}