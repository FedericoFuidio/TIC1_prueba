package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import um.business.entities.Operador;

public class tableController {

    @FXML
    private TableView<Operador> operatorsTable;

    @FXML
    private TableColumn<Operador, Integer> id;

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

}
