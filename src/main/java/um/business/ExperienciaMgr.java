package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.*;
import um.business.exception.InvalidInformation;
import um.business.exception.UserNotFound;
import um.persistance.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExperienciaMgr {

    @Autowired
    PreferenciaExperienciaRepository preferenciaExperienciaRepository;

    @Autowired
    ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    CupoRepository cupoRepository;

    @Autowired
    OperadorMgr operadorMgr;

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    CalificacionRepository calificacionRepository;

    public void addExperiencia(String nombre, String ubicacion, String descripcion, byte[] foto, byte[] mapa,String mailOperador)
            throws InvalidInformation, UserNotFound {

        if(nombre == null || nombre.equals("") || ubicacion == null || ubicacion.equals("")){
            throw new InvalidInformation();
        }

        Operador operador = operadorMgr.getByMail(mailOperador);

        if(operador == null){
            throw new UserNotFound();
        }

        Experiencia nueva = new Experiencia(nombre, ubicacion, descripcion, foto, mapa, operador);
        experienciaGeneralRepository.save(nueva);

    }

    public Iterable<Experiencia> getExperiencias(){

        return experienciaGeneralRepository.findAll();
    }

    public Experiencia getExperienciaByNombre(String nombre){

        return experienciaGeneralRepository.getExperienciaByNombre(nombre);
    }

    public Iterable<Experiencia> getExperienciaByOperador(Operador operador){

        if(operador != null) {
            System.out.println(operador.getDescripcion());
            return experienciaGeneralRepository.getAllByOperador(operador);
        }else{
            return getExperiencias();
        }

    }

    public void addCupoGeneral(Experiencia experiencia, LocalTime horaApertura, LocalTime horaCierre, DayOfWeek dia, Integer personas) throws InvalidInformation{

        if(horaApertura.compareTo(horaCierre) >= 0){
            throw new InvalidInformation();
        }

        Cupo cupo = cupoRepository.getCupoByExperienciaAndDia(experiencia, dia);

        if(cupo != null){
            cupo.setHoraApertura(horaApertura);
            cupo.setHoraCierre(horaCierre);
            cupo.setCupos(personas);
            cupoRepository.save(cupo);

        }else{
            Cupo nuevo = new Cupo(personas, dia, horaApertura, horaCierre, experiencia);
            cupoRepository.save(nuevo);
        }




    }

    public void validar(Experiencia experiencia){

        experiencia.setValidado(true);
        experienciaGeneralRepository.save(experiencia);
    }

    public void bloquear(Experiencia experiencia){
        experiencia.setValidado(false);
        experienciaGeneralRepository.save(experiencia);
    }

    public Iterable<Reserva> getReservasPorCalificar(Turista turista){

        Iterable<Reserva> reservas = reservaRepository.findAllByTurista(turista);
        ObservableList<Reserva> reservas_a_calificar = FXCollections.observableArrayList();

        for(Reserva r : reservas){
            if(r.getFecha().before(java.sql.Date.valueOf(LocalDate.now()))
            && calificacionRepository.getCalificacionByTuristaAndReserva(turista, r) == null && r.isAceptada() && !r.isCancelada()){
                reservas_a_calificar.add(r);
            }
        }

        return reservas_a_calificar;

    }

    public List<Experiencia> filtrarExperiencias(String nombre){

        Iterable<Experiencia> experiencias = experienciaGeneralRepository.findAll();
        List<Experiencia> resultado = new ArrayList<>();

        for(Experiencia e : experiencias){

            if((e.getNombre().toLowerCase().contains(nombre) || e.getOperador().getName().toLowerCase().contains(nombre))
                    && e.isValidado() && e.getOperador().isValidado()){

                resultado.add(e);
            }
        }

        return resultado;

    }

    public List<Experiencia> filtrarPorPreferencia(String preferencia){

        Iterable<Experiencia> experiencias = experienciaGeneralRepository.findAll();
        List<Experiencia> resultado = new ArrayList<>();

        for(Experiencia e : experiencias){

            if(e.isValidado() && e.getOperador().isValidado()) {
                Iterable<PreferenciaExperiencia> prEx = preferenciaExperienciaRepository.getAllByExperiencia(e);
                boolean temp = false;
                for (PreferenciaExperiencia pe : prEx) {

                    if (pe.getPreferencia().getNombre().equals(preferencia)) {

                        temp = true;
                        break;
                    }
                }

                if (temp) {
                    resultado.add(e);
                }
            }
        }

        return resultado;
    }

    public Iterable<Calificacion> getCalificaciones(Experiencia experiencia){

        return calificacionRepository.getAllByExperiencia(experiencia);

    }

}
