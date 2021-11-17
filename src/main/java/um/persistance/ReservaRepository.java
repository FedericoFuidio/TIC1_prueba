package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Cupo;
import um.business.entities.Reserva;
import um.business.entities.Turista;

import java.sql.Date;


public interface ReservaRepository  extends CrudRepository<Reserva, Long> {
    Reserva findAllByFechaAndTuristaAndCupo(Date fecha, Turista turista, Cupo cupo);
    Iterable<Reserva> findAllByFecha(Date fecha);
    Iterable<Reserva> findAllByTurista(Turista turista);
    Iterable<Reserva> findAllByCupo(Cupo cupo);


}
