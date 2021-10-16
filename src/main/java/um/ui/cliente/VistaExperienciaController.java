package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;
import um.business.entities.Experiencia;

@Component
public class VistaExperienciaController {

    @FXML
    private ImageView foto;

    @FXML
    private Label nombre;

    @FXML
    private Label descripcion;

    @FXML
    private Label ubicacion;

    @FXML
    private Label contacto;


    public void setData(Experiencia experiencia){
       // foto.setImage(experiencia.getFoto());
        nombre.setText(experiencia.getNombre());
        descripcion.setText(experiencia.getDescripcion());
        ubicacion.setText(experiencia.getUbicacion());
        contacto.setText(experiencia.getOperador().getMail());
    }
}
