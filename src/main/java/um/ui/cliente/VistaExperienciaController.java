package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.entities.Experiencia;

import java.io.ByteArrayInputStream;
import java.io.IOException;

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


    @FXML
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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);
            fxmlLoader.setLocation(getClass().getResource("experienciaCompleta.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();


            ExperienciaCompletaContoller expController = fxmlLoader.getController();
            expController.setData(xp);


            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.getRoot()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
