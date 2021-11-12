package um.ui.cliente;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import um.business.ReservaMgr;
import um.business.TuristaMgr;
import um.business.entities.Experiencia;
import um.business.entities.Operador;
import um.business.entities.Reserva;

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

    @Autowired
    private ReservaMgr reservaMgr;

    @FXML
    private ImageView imgPerfil;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnReservas;

    @FXML
    private Button btnCalificar;

    @FXML
    private ImageView imgBusqueda;

    @FXML
    private TextField searchField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane experienciasGrid;

    private List<Experiencia> experiencias = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    static Experiencia experiencia_vista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateScreen();
        //searchExp();
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
        experienciasGrid.getChildren().clear();
        experiencias.clear();
        reservas.clear();
    }

   /* public void searchExp(){
        FilteredList<Experiencia> filteredData = new FilteredList<>(experiencias, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (person.getName().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
            });
        });

        SortedList<Experiencia> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(experienciasGrid.comparatorProperty());
        experienciasGrid.(sortedData);
    }*/

    public void openPerfil(ActionEvent actionEvent) {
        //Falta crear fxml de perfil
    }

    public void openReservas(ActionEvent actionEvent) {
        updateScreen();
        getReservas();
        int column = 0;
        int row = 0;
        try {
            for (Reserva re : reservas) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("ReservasTurista.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                reservasTuristaController resController = fxmlLoader.getController();
                resController.setData(re);



                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void openCalificaciones(ActionEvent actionEvent) {

    }

    private List<Experiencia> getData(){
        List<Experiencia> experiencias = new ArrayList<>();
        Iterable<Experiencia> expBD = turistaMgr.recomendaciones(UserController.turistaIngresado);
        for(Experiencia e : expBD){
            experiencias.add(e);
        }
        return experiencias;
    }

    private List<Reserva> getReservas(){
        List<Reserva> lReservas = new ArrayList<>();
        Iterable<Reserva> resBD = reservaMgr.GetReservasTurista(UserController.turistaIngresado);
        for(Reserva r : resBD){
            reservas.add(r);
        }
        return reservas;
    }

}