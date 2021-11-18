package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.*;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.CalificacionRepository;
import um.persistance.CupoRepository;
import um.persistance.ExperienciaGeneralRepository;
import um.persistance.ReservaRepository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CupoRepository cupoRepository;

    @Autowired
    private ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    public void addReserva(Turista turista, Cupo cupo, Integer cantidad, Date fecha, Time hora) throws InvalidInformation, ClassAlreadyExists {
        if(turista == null || cupo == null || cantidad == null || fecha == null || hora == null){
            throw new InvalidInformation();
        }
        if(reservaRepository.findAllByFechaAndTuristaAndCupo(fecha, turista, cupo) != null){
            throw new ClassAlreadyExists();
        }

        Iterable<Reserva> reservas = reservaRepository.findAllByFechaAndHora(fecha,hora);
        int cLibres = cupo.getCupos();
        for (Reserva r : reservas){
            cLibres -= r.getCantidad();
        }
        if(cLibres < cantidad){
            throw new InvalidInformation();
        }

        ReservaKey id = new ReservaKey(turista.getId(), cupo.getId());

        Reserva r = new Reserva(turista, cupo, cantidad, fecha, hora);
        reservaRepository.save(r);
    }

    public Iterable<Reserva> GetReservasTurista(Turista t){
        ObservableList<Reserva> rvs = FXCollections.observableArrayList();
        Iterable<Reserva> rs = reservaRepository.findAllByTurista(t);
        for(Reserva r : rs){
            if(!r.isCancelada()){
                rvs.add(r);
            }
        }
        return rvs;
    }

    public ObservableList<Reserva> getReservasByExperiencia(Experiencia experiencia){

        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        Iterable<Cupo> cupos = cupoRepository.findAllByExperiencia(experiencia);
        for(Cupo c : cupos){
            Iterable<Reserva> reserva_cupo = reservaRepository.findAllByCupo(c);
            for(Reserva r : reserva_cupo){
                if(!r.isCancelada()){reservas.add(r);}
            }

        }

        return reservas;
    }

    public Iterable<Reserva> getReservas(){
        Iterable<Reserva> reservas = reservaRepository.findAll();
        ObservableList<Reserva> reservas_futuras = FXCollections.observableArrayList();

        for(Reserva r : reservas){
            if(!r.getFecha().before(java.sql.Date.valueOf(LocalDate.now()))){
                reservas_futuras.add(r);
            }
        }
        return reservas_futuras;
    }

    public void validar(Reserva reserva){
        reserva.setAceptada(true);
        reservaRepository.save(reserva);
    }

    public void bloquear(Reserva reserva){
        reserva.setAceptada(false);
        reservaRepository.save(reserva);
    }

    public void calificar(Reserva reserva, Turista turista, int puntaje, String comentario, boolean esPublica) throws InvalidInformation, ClassAlreadyExists{

        if(reserva == null || turista == null || comentario == null || comentario.equals("")){

            throw new InvalidInformation();

        }

        Cupo cupo = reserva.getCupo();
        Experiencia experiencia = cupo.getExperiencia();

        if(calificacionRepository.getCalificacionByTuristaAndReserva(turista, reserva) != null){
            throw new ClassAlreadyExists();
        }

        Calificacion calificacion = new Calificacion(reserva, turista, java.sql.Date.valueOf(LocalDate.now()), puntaje, comentario, esPublica, reserva.getCupo().getExperiencia());
        experiencia.setCalificaciones(experiencia.getCalificaciones() + 1);
        experiencia.setPuntajeTotal(experiencia.getPuntajeTotal() + puntaje);
        experiencia.setPuntaje((float) (experiencia.getPuntajeTotal())/(experiencia.getCalificaciones()));

        calificacionRepository.save(calificacion);
        experienciaGeneralRepository.save(experiencia);

    }

    public void cancelarReserva(Reserva r){
        r.setCancelada(true);
        reservaRepository.save(r);
    }

}
