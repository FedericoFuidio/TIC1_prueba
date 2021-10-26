package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import org.controlsfx.control.Rating;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;

public class ExperienciaCompletaContoller {

    @FXML
    private ImageView imgExperiencia;

    @FXML
    private ImageView mapaExperiencia;

    @FXML
    private Label nombreActividad;

    @FXML
    private Rating ratingActividad;

    @FXML
    private Label descActividad;

    @FXML
    private Label precioActividad;

    @FXML
    private Label contacto;

    @FXML
    private ComboBox<?> seleccionadorHora;

    @FXML
    private ComboBox<?> seleccionadorPersonas;


    public void setData(Experiencia experiencia){
        if(experiencia.getFoto() != null){
            Image image =   new Image(new ByteArrayInputStream(experiencia.getFoto()));
            imgExperiencia.setImage(image);}
        nombreActividad.setText(experiencia.getNombre());
        descActividad.setWrapText(true);
        descActividad.setText(experiencia.getDescripcion());
        contacto.setText(experiencia.getOperador().getMail());
        if(experiencia.getMapa() != null){
            Image image =   new Image(new ByteArrayInputStream(experiencia.getMapa()));
            mapaExperiencia.setImage(image);}
        //ratingActividad.setRating(experiencia.getRating());
        //seleccionadorHora.setItems(experiencia.);
    }

}