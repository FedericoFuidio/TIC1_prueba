package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.OperadorMgr;
import um.business.entities.Operador;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class tableController implements Initializable {

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
    void blockUsers(ActionEvent event) {
        operadorMgr.setValidado(seleccionado, false);
        updateTable();
    }

    @FXML
    void validateUsers(ActionEvent event) {
        operadorMgr.setValidado(seleccionado, true);
        updateTable();
    }

    Operador seleccionado = new Operador();
    @FXML
    void getSelected(MouseEvent mouse){
        seleccionado = operatorsTable.getSelectionModel().getSelectedItem();
    }

    private ObservableList<Operador> operadores = FXCollections.observableArrayList();


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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTable();
    }
}
