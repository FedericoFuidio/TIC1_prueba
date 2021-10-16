package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Pais;

public interface PaisRepository extends CrudRepository<Pais, Long> {

    Pais findPaisByNombre(String nombre);
}
