package um.ui.cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import um.business.CupoMgr;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class ExperienciaCompletaContoller {

    @Autowired
    private CupoMgr cupoMgr;

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
    private DatePicker datePicker;

    @FXML
    private Label horaAp;

    @FXML
    private Label horaCi;

    @FXML
    private TextField people;

    @FXML
    private Button btnReservar;

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
        //horaAp.setText(cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraApertura().toString());
        //horaCi.setText(cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraCierre().toString());
        ratingActividad.setRating(experiencia.getPuntaje());
    }

    @FXML
    void realizarReserva(ActionEvent event) {
        if(datePicker.getConverter() != null && people.getText() != null && people.getText().isEmpty() == false){
            DayOfWeek dia = datePicker.getValue().getDayOfWeek();//Tengo que ver como accedere a la exp desde acá

        }
    }

}