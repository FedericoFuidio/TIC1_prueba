package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Operador, Integer> id;

    @FXML
    private TableColumn<Operador, String> name;

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


    private ObservableList<Operador> operadores = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
}
