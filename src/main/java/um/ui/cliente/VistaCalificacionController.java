package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;
import um.business.ExperienciaMgr;
import um.business.entities.Calificacion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class VistaCalificacionController implements Initializable {

    @FXML
    private ExperienciaMgr experienciaMgr;

    @FXML
    private Label nombreUsuario;

    @FXML
    private Label pais;

    @FXML
    private Label puntaje;

    @FXML
    private Label comentario;

    private List<Calificacion> calificaciones;

    @FXML
    private Rating calificacion;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setData(ListaCalificacionesController.calificacion);
    }


    public void setData(Calificacion c){

        nombreUsuario.setText("Usuario: " + c.getTurista().getUserName());

        calificacion.setRating(c.getPuntaje());

        comentario.setText(c.getCometario());

        //Aca agarro los datos
    }
}
