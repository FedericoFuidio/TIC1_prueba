package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.CupoMgr;
import um.business.ExperienciaMgr;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;


@Scope("prototype")
@Component
public class VistaExperienciaController implements Initializable {

    @Autowired
    ExperienciaMgr experienciaMgr;

    @Autowired
    CupoMgr cupoMgr;

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


    static Experiencia xp;

    public Experiencia exp;
    private Class<? extends VistaExperienciaController> aClass;

    @FXML
    public void setData(Experiencia experiencia){
        exp = experiencia;
        //xp=experiencia;
        if(experiencia.getFoto() != null){
            Image image =   new Image(new ByteArrayInputStream(experiencia.getFoto()));
            foto.setImage(image);}
        nombre.setText(experiencia.getNombre());
        descripcion.setWrapText(true);
        descripcion.setText(experiencia.getDescripcion());
        ubicacion.setText(experiencia.getUbicacion());
        contacto.setText(experiencia.getOperador().getMail());
    }

    public void setDataxp(){
        Experiencia experiencia;
        experiencia = xp;
        //xp=experiencia;
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

        try {
            if(cupoMgr.getCupo(exp, DayOfWeek.from(LocalDate.now())) != null){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("experienciaCompleta.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ExperienciaCompletaContoller expController = fxmlLoader.getController();
                expController.setData(exp);

                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.getRoot()));
                stage.show();
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("experienciaCompleta.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();


                ExperienciaCompletaContoller expController = fxmlLoader.getController();
                expController.setData(exp);

                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.getRoot()));
                stage.show();
                /*showAlert(
                        "ATENCIÓN",
                        "Esta experiencia no admite reservas");*/
                //ACÁ VA EL LOADER DEL NUEVO SI ES Q DIFERENCIAMOS ENTRE LOS Q SE PUEDEN
                //RESERVAR Y LOS QUE NO.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
