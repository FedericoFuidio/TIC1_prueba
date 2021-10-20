package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.springframework.stereotype.Component;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;

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

    private Experiencia xp;


    public void setData(Experiencia experiencia){
        xp=experiencia;
        if(experiencia.getFoto() != null){
            Image image =   new Image(new ByteArrayInputStream(experiencia.getFoto()));
            foto.setImage(image);}
        nombre.setText(experiencia.getNombre());
        descripcion.setWrapText(true);
        descripcion.setText(experiencia.getDescripcion());
        ubicacion.setText(experiencia.getUbicacion());
        contacto.setText(experiencia.getOperador().getMail());
    }

    @FXML
    public void openExperience(MouseEvent mouseEvent) {
        System.out.println("entro a: " + xp.getNombre());
    }
}
