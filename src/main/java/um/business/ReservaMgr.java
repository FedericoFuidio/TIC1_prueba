package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.*;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.CupoRepository;
import um.persistance.ReservaRepository;

import java.sql.Date;
import java.sql.Time;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private CupoRepository cupoRepository;

    public void addReserva(Turista turista, Cupo cupo, Integer cantidad, Date fecha, Time hora) throws InvalidInformation, ClassAlreadyExists {
        if(turista == null || cupo == null || cantidad == null || fecha == null || hora == null){
            throw new InvalidInformation();
        }
        if(reservaRepository.findAllByFechaAndTuristaAndCupo(fecha, turista, cupo) != null){
            throw new ClassAlreadyExists();
        }

        Iterable<Reserva> reservas = reservaRepository.findAllByFecha(fecha);
        int cLibres = cupo.getCupos();
        for (Reserva r : reservas){
            cLibres -= r.getCantidad();
        }
        if(cLibres < cantidad){
            throw new InvalidInformation();
        }

        ReservaKey id = new ReservaKey(turista.getId(), cupo.getId());

        Reserva r = new Reserva(id, turista, cupo, cantidad, fecha, hora);
        reservaRepository.save(r);
    }

    public Iterable<Reserva> GetReservasTurista(Turista t){
        return reservaRepository.findAllByTurista(t);
    }

    public ObservableList<Reserva> getReservasByExperiencia(Experiencia experiencia){

        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        Iterable<Cupo> cupos = cupoRepository.findAllByExperiencia(experiencia);
        for(Cupo c : cupos){
            Iterable<Reserva> reserva_cupo = reservaRepository.findAllByCupo(c);
            for(Reserva r : reserva_cupo){

                reservas.add(r);
            }

        }

        return reservas;
    }

    public Iterable<Reserva> getReservas(){

        return reservaRepository.findAll();
    }

    public void validar(Reserva reserva){
        reserva.setAceptada(true);
        reservaRepository.save(reserva);
    }

    public void bloquear(Reserva reserva){
        reserva.setAceptada(false);
        reservaRepository.save(reserva);
    }

}
