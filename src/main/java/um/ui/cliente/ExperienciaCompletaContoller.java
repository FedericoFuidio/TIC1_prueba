package um.ui.cliente;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.CupoMgr;
import um.business.ReservaMgr;
import um.business.entities.Calificacion;
import um.business.entities.Cupo;
import um.business.entities.Experiencia;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;


@Component
public class ExperienciaCompletaContoller {

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
    private Label cLibres;

    @FXML
    private ComboBox<String> hourPicker;

    @FXML
    private TextField people;

    @FXML
    private Button btnReservar;

    static Experiencia exp;

    private Iterable<Calificacion> calificaciones;

    @FXML
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
            String hora_inicial;
            ratingActividad.setRating(experiencia.getPuntaje());
            datePicker.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    Cupo c = cupoMgr.getCupoByExperienciaAndDia(exp, date.getDayOfWeek());

                    setDisable(empty || date.compareTo(today) < 0 || c == null);
                }
            });
            int horaAp = cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraApertura().getHour();
            int horaCi = cupoMgr.getCupo(experiencia, DayOfWeek.from(LocalDate.now())).getHoraCierre().getHour();
            if(horaAp < 10){
                hora_inicial = "0"+horaAp+":00";
            }else{
                hora_inicial = horaCi+":00";
            }

            ObservableList<String> o = FXCollections.observableArrayList();
            for (int i = horaAp; i< horaCi; i++){
                if(i < 10) {
                    o.add("0"+i+":00:00");
                }else{
                    o.add(i+":00:00");
                }
            }
            hourPicker.setItems(o);
        }catch(NullPointerException npe){
            //esto ya lo tengo en cuenta en la otra, deberíamos hacer otro set data u otro fxml
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
            Time t = Time.valueOf(hourPicker.getValue());
            reservaMgr.addReserva(UserController.turistaIngresado, c, Integer.parseInt(people.getText()), d, t);
            c.setCuposLibres(c.getCuposLibres()-Integer.parseInt(people.getText()));
            showAlert("Reserva realizada!",
                    "Se realizó con exito la reserva");

        } catch (ClassAlreadyExists e){
            showAlert(
                    "ERROR!",
                    "Ya tienes una reserva para el dia seleccionado");
        } catch (InvalidInformation e){
            showAlert(
                    "ERROR!",
                    "No hay suficiente cupo disponible para esta hora del día");
        }

        catch (Exception e) {
            e.printStackTrace();
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

    @FXML
    private void updatePicker(){
        String hora_inicial;
        int horaAp = cupoMgr.getCupo(exp, DayOfWeek.from(datePicker.getValue())).getHoraApertura().getHour();
        int horaCi = cupoMgr.getCupo(exp, DayOfWeek.from(datePicker.getValue())).getHoraCierre().getHour();
        if(horaAp < 10){
            hora_inicial = "0"+horaAp+":00";
        }else{
            hora_inicial = horaCi+":00";
        }

        ObservableList<String> o = FXCollections.observableArrayList();
        for (int i = horaAp; i< horaCi; i++){
            if(i < 10) {
                o.add("0"+i+":00:00");
            }else{
                o.add(i+":00:00");
            }
        }
        hourPicker.setItems(o);

    }

    @FXML
    public void updateLibres(){
        try{
            Cupo cupo = cupoMgr.getCupo(exp, DayOfWeek.from(datePicker.getValue()));
            Date dia = Date.valueOf(datePicker.getValue());
            Time hora = Time.valueOf(hourPicker.getValue());
            int cuposLibres = reservaMgr.cuposLibresFechaHora(cupo, dia, hora);
            cLibres.setText(String.valueOf(cuposLibres));
        }catch (IllegalArgumentException e){
            //Cuando cambio de día
        }catch (Exception e){
            //e.printStackTrace();
        }
    }

    public void comentarios(ActionEvent actionEvent){

        try {


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(ListaCalificacionesController.class.getResourceAsStream("vistaCalificaciones.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
            showAlert("ERROR", "Algo salio mal");
        }

    }

}
