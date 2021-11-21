package um.ui.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import um.Main;
import um.business.ExperienciaMgr;
import um.business.ReservaMgr;
import um.business.TuristaMgr;
import um.business.entities.Experiencia;
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
    private Button btnBuscar;

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
    private Button btnDeporte;

    @FXML
    private Button btnCultura;

    @FXML
    private Button btnGastronomia;

    @FXML
    private Button btnAventura;

    @FXML
    private Button btnMisPreferencias;

    @FXML
    private Button btnAddPreferenicas;

    @FXML
    private TextField searchField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane experienciasGrid;

    @FXML
    private Button addPreferencias;

    private List<Experiencia> experiencias = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private List<Reserva> reservas_a_calificar = new ArrayList<>();

    static Experiencia experiencia_vista;

    private String seleccion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recomendadas();
    }

    public void updateScreen(){
        experienciasGrid.getChildren().clear();
        experiencias.clear();
        reservas.clear();
        reservas_a_calificar.clear();
    }

    public void buscar(ActionEvent actionEvent){
        updateScreen();
        try {
            String nombre = searchField.getText().toLowerCase();
            filtrar(nombre);

            int column = 0;
            int row = 0;

            for (Experiencia ex : experiencias) {
                experiencia_vista = ex;

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("VistaExperiencia.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                VistaExperienciaController expController = fxmlLoader.getController();
                expController.setData(ex);



                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }


        }catch (Exception e){
                e.printStackTrace();

        }

    }

    public void recomendadas(){
        updateScreen();
        experiencias.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Experiencia ex : experiencias) {
                experiencia_vista = ex;


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
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


    public void filtrarPreferencias(ActionEvent actionEvent){
        updateScreen();
        try {
            filtrarPorPreferencia(seleccion);

            int column = 0;
            int row = 0;

            for (Experiencia ex : experiencias) {
                experiencia_vista = ex;

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("VistaExperiencia.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                VistaExperienciaController expController = fxmlLoader.getController();
                expController.setData(ex);



                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }


        }catch (Exception e){
            e.printStackTrace();

        }

    }

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
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("ReservasTurista.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ReservasTuristaController resController = fxmlLoader.getController();
                resController.setData(re);



                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void openCalificaciones(ActionEvent actionEvent) {
        updateScreen();
        getReservasPorCalficar();
        int column = 0;
        int row = 0;
        try {
            for (Reserva re : reservas_a_calificar) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                //fxmlLoader.setControllerFactory(Main.getContext()::getBean);
                fxmlLoader.setLocation(getClass().getResource("ReservasParaCalificar.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ReservasTuristaController resController = fxmlLoader.getController();
                resController.setData(re);



                experienciasGrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public void addPreferencias(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(AgregarPreferenciaController.class.getResourceAsStream("agregarPreferencias.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();



        }catch(Exception e){
            showAlert("ERROR", "Lo sentimos, ocurrió un error inesperado");
        }


    }

    public void deletePreferencias(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Main.getContext()::getBean);

            Parent root = fxmlLoader.load(EliminarPreferenciaController.class.getResourceAsStream("eliminarPreferencias.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }catch(Exception e){
            showAlert("ERROR", "Lo sentimos, ocurrió un error inesperado");
        }

        recomendadas();
    }

    public void openRecomendados(ActionEvent actionEvent){
        updateScreen();
        //searchExp();
        experiencias.addAll(getData());
        int column = 0;
        int row = 0;
        try {
            for (Experiencia ex : experiencias) {
                experiencia_vista = ex;


                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Main.getContext()::getBean);
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
            if (r.isAceptada()){reservas.add(r);}

        }
        return reservas;
    }

    private void getReservasPorCalficar(){

        Iterable<Reserva> temp = experienciaMgr.getReservasPorCalificar(UserController.turistaIngresado);
        for(Reserva r : temp){
            reservas_a_calificar.add(r);
        }

    }

    private void filtrar(String nombre){

        experiencias = experienciaMgr.filtrarExperiencias(nombre);

    }

    private void filtrarPorPreferencia(String preferencia){

        experiencias = experienciaMgr.filtrarPorPreferencia(preferencia);
    }

    public void deporte(ActionEvent actionEvent){

        seleccion = "Deporte";
        filtrarPreferencias(actionEvent);
    }

    public void cultura(ActionEvent actionEvent){

        seleccion = "Cultura";
        filtrarPreferencias(actionEvent);
    }

    public void gastronomia(ActionEvent actionEvent){

        seleccion = "Gastronomia";
        filtrarPreferencias(actionEvent);
    }

    public void aventura(ActionEvent actionEvent){

        seleccion = "Aventura";
        filtrarPreferencias(actionEvent);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }



}