package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;
import um.business.entities.Operador;

@Component
public class tableController {

    @FXML
    private TableView<Operador> operatorsTable;

    @FXML
    private TableColumn<Operador, Integer> id;

    @FXML
    private TableColumn<Operador, String> name;

    @FXML
    private TableColumn<Operador, String> picture;

    @FXML
    private TableColumn<Operador, String> description;

    @FXML
    private TableColumn<Operador, String> mail;

    @FXML
    private TableColumn<Operador, String> phone;

    @FXML
    private TableColumn<Operador, String> location;

    @FXML
    private TableColumn<Operador, Boolean> valid;

    @FXML
    void mostrarOperadores(ActionEvent actionEvent){


    }

}
