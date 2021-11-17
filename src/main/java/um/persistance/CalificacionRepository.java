package um.persistance;


import org.springframework.data.repository.CrudRepository;
import um.business.entities.Calificacion;
import um.business.entities.Experiencia;
import um.business.entities.Reserva;
import um.business.entities.Turista;

public interface CalificacionRepository extends CrudRepository<Calificacion, Long> {

    Calificacion getCalificacionByTuristaAndReserva(Turista turista, Reserva reserva);
    Iterable<Calificacion> getAllByExperiencia(Experiencia experiencia);
}
