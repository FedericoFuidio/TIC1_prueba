package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Cupo;
import um.business.entities.Reserva;
import um.business.entities.ReservaKey;
import um.business.entities.Turista;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.ReservaRepository;

import java.sql.Date;
import java.sql.Time;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

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
}
