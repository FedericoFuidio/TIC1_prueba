package um.ui.user;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import um.business.entities.Operador;

public class tableController {

    @FXML
    private TableView<Operador> operatorsTable;

    @FXML
    private TableColumn<Operador, ?> id;

    @FXML
    private TableColumn<Operador, ?> name;

    @FXML
    private TableColumn<Operador, ?> picture;

    @FXML
    private TableColumn<Operador, ?> description;

    @FXML
    private TableColumn<Operador, ?> mail;

    @FXML
    private TableColumn<Operador, ?> phone;

    @FXML
    private TableColumn<Operador, ?> location;

    @FXML
    private TableColumn<Operador, ?> valid;

}
