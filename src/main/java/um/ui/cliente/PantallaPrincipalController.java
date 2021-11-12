package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.business.ExperienciaMgr;
import um.business.TuristaMgr;
import um.business.entities.Experiencia;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class PantallaPrincipalController implements Initializable {

    @Autowired
    private ExperienciaMgr experienciaMgr;

    @Autowired
    private TuristaMgr turistaMgr;

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
        //Iterable<Experiencia> expBD = experienciaMgr.getExperiencias();
        Iterable<Experiencia> expBD = turistaMgr.recomendaciones(UserController.turistaIngresado);
        for(Experiencia e : expBD){
            experiencias.add(e);
        }
        return experiencias;
    }

    static Experiencia experiencia_vista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updateScreen();
        experiencias.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Experiencia ex : experiencias) {
                experiencia_vista = ex;


                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
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

    public void openPerfil(ActionEvent actionEvent) {
        //Falta crear fxml de perfil
    }

    public void openAjustes(ActionEvent actionEvent) {
        //Falta crear fxml de ajustes
    }
}