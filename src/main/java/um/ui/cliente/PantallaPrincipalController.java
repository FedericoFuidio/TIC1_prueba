package um.ui.cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.ExperienciaMgr;
import um.business.entities.Experiencia;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaPrincipalController implements Initializable {

    @Autowired
    private ExperienciaMgr experienciaMgr;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Button btnPerfil;

    @FXML
    private ImageView imgAjustes;

    @FXML
    private Button btnAjustes;

    @FXML
    private ImageView imgHistorial;

    @FXML
    private Button btnHistorial;

    @FXML
    private ImageView imgBusqueda;

    @FXML
    private TextField searchField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane experienciasGrid;

    private List<Experiencia> experiencias = new ArrayList<>();

    private List<Experiencia> getData(){
        List<Experiencia> experiencias = new ArrayList<>();
        Iterable<Experiencia> expBD = experienciaMgr.getExperiencias();
        for(Experiencia e : expBD){
            experiencias.add(e);
        }
        return experiencias;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Image imgbusqueda = new Image(getClass().getResourceAsStream("imgs/busqueda.jpg"));
        imgBusqueda.setImage(imgbusqueda);
        Image imgperfil = new Image(getClass().getResourceAsStream("imgs/perfil.png"));
        imgPerfil.setImage(imgperfil);
        Image imgajustes = new Image(getClass().getResourceAsStream("imgs/ajustes.png"));
        imgAjustes.setImage(imgajustes);
        Image imghistorial = new Image(getClass().getResourceAsStream("imgs/historial.png"));
        imgHistorial.setImage(imghistorial);*/
        /*scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        GridPane.setVgrow(scrollPane, Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, Priority.ALWAYS);*/
        updateScreen();
        experiencias.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Experiencia ex : experiencias) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("VistaExperiencia.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                VistaExperienciaController expController = fxmlLoader.getController();
                expController.setData(ex);

                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (Exception exception){
            exception.printStackTrace();
            }
    }

    public void updateScreen(){
        experiencias.clear();
    }
}