package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.PreferenciasMgr;
import um.business.entities.PreferenciaGeneral;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class TablePreferencias implements Initializable {

    @Autowired
    PreferenciasMgr preferenciasMgr;

    @FXML
    private TableView<PreferenciaGeneral> preferenciaGeneralTableView;

    @FXML
    private TableColumn<PreferenciaGeneral, Integer> id;

    @FXML
    private TableColumn<PreferenciaGeneral, String> nombre;

    @FXML
    private TableColumn<PreferenciaGeneral, String> descripcion;

    @FXML
    private TextField filterField;

    static PreferenciaGeneral seleccionado = new PreferenciaGeneral();
    @FXML
    void getSelected(MouseEvent mouse){
        seleccionado = preferenciaGeneralTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    void agregarPreferenciaGeneral(ActionEvent actionEvent) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Main.getContext()::getBean);

        Parent root = fxmlLoader.load(ExperienciaController.class.getResourceAsStream("agregarExperienciaGen.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
