package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import um.business.entities.Reserva;
import um.persistance.ReservaRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class reservasTuristaController implements Initializable {

    @Autowired
    ReservaRepository reservaRepository;

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
    void cancelarReserva(ActionEvent event) {

    }

    public void setData(Reserva r){
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
}