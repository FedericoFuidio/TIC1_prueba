package um.ui.cliente;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.CupoMgr;
import um.business.ReservaMgr;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class ExperienciaCompletaContoller implements Initializable {

    @Autowired
    CupoMgr cupoMgr;

    @Autowired
    ReservaMgr reservaMgr;

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

    private Experiencia exp;

    public void setData(Experiencia experiencia) {
        exp = experiencia;
        if (experiencia.getFoto() != null) {
            Image image = new Image(new ByteArrayInputStream(experiencia.getFoto()));
            imgExperiencia.setImage(image);
        }
        nombreActividad.setText(experiencia.getNombre());
        descActividad.setWrapText(true);
        descActividad.setText(experiencia.getDescripcion());
        contacto.setText(experiencia.getOperador().getMail());
        if (experiencia.getMapa() != null) {
            Image image = new Image(new ByteArrayInputStream(experiencia.getMapa()));
            mapaExperiencia.setImage(image);
        }
        try {
            horaAp.setText(cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraApertura().toString());
            horaCi.setText(cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraCierre().toString());
            ratingActividad.setRating(experiencia.getPuntaje());
        }catch(Exception e){
            e.printStackTrace();
            showAlert(
                    "ERROR!",
                    "No existen cupos para esta experiencia");
        }
    }

    @FXML
    void realizarReserva(ActionEvent event) {
        Cupo c = null;
        Date d = null;
        try{
            if (datePicker.getConverter() != null && people.getText() != null && !people.getText().isEmpty()) {
                DayOfWeek dia = datePicker.getValue().getDayOfWeek();
                c = cupoMgr.getCupo(exp, dia);
                d = java.sql.Date.valueOf(datePicker.getValue());
            }
        }catch(Exception e){
                showAlert(
                        "ERROR!",
                        "LLene todos los campos");
            }
            try {
                if (Integer.parseInt(people.getText()) <= c.getCuposLibres()) {
                    reservaMgr.addReserva(UserController.turistaIngresado, c, Integer.parseInt(people.getText()), d);
                }
            } catch (Exception e) {
                showAlert(
                        "ERROR!",
                        "En cantidad de personas debe poner un número entero");
            }
        }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cupoMgr.getCupo(exp,DayOfWeek.of(2));
    }
}
