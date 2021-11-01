package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.CupoMgr;



@Component
public class CupoController {

    @Autowired
    CupoMgr cupoMgr;

    private final ObservableList<String> dias = FXCollections.observableArrayList("Lunes",
            "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");



    @FXML
    private ComboBox<String> cmbDias;


}
