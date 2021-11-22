package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.ExperienciaMgr;
import um.business.entities.Calificacion;
import um.business.entities.Experiencia;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ListaCalificacionesController implements Initializable {

    @Autowired
    private ExperienciaMgr experienciaMgr;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane calificacionesGrid;

    private Iterable<Calificacion> calificaciones;

    static Calificacion calificacion;

    public void setData(Experiencia e){

        getCalificaciones(e);
        int column = 0;
        int row = 0;
        try {
            for (Calificacion c : calificaciones) {
                calificacion = c;
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);

                Parent root = fxmlLoader.load(VistaCalificacionController.class.getResourceAsStream("calificacionesCompleta.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));


                calificacionesGrid.add(root, column, row++);
                GridPane.setMargin(root, new Insets(10));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void getCalificaciones(Experiencia e){

        calificaciones = experienciaMgr.getCalificaciones(e);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData(ExperienciaCompletaContoller.exp);
    }
}
