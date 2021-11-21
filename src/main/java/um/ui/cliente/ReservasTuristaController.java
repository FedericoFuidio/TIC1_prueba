package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.ReservaMgr;
import um.business.entities.Reserva;
import um.persistance.ReservaRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Scope("prototype")
@Component
public class ReservasTuristaController implements Initializable {

    @Autowired
    ReservaMgr reservaMgr;

    @FXML
    private Label nombreExp;

    @FXML
    private Label fechaReserva;

    @FXML
    private Label horaReserva;

    @FXML
    private Label personasReserva;

    @FXML
    private Label ubicacion;

    @FXML
    private Label contacto;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCalificar;

    @FXML
    void cancelarReserva(ActionEvent event) {

        reservaMgr.cancelarReserva(re);
        showAlert("Atención", "Su reserva fue cancelada con éxito");
    }

    private Reserva re;

    public void setData(Reserva r){
        re = r;
        fechaReserva.setText(r.getFecha().toString());
        horaReserva.setText(r.getHora().toString());
        personasReserva.setText(String.valueOf(r.getCantidad()));
        nombreExp.setText(r.getCupo().getExperiencia().getNombre());
        ubicacion.setText(r.getCupo().getExperiencia().getUbicacion());
        contacto.setText(r.getCupo().getExperiencia().getOperador().getMail());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    void calificarReserva(ActionEvent event){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            fxmlLoader.setLocation(getClass().getResource("CalificacionController.fxml"));
            //AnchorPane anchorPane = fxmlLoader.load();
            Parent root = fxmlLoader.load(CalificacionController.class.getResourceAsStream("calificarExperiencia.fxml"));

            CalificacionController calController = fxmlLoader.getController();
            calController.setData(re);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }
}