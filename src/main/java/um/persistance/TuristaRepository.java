package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Turista;

public interface TuristaRepository extends CrudRepository<Turista, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param userName
     * @return
     */

    Turista findTuristaByUserName(String userName);
}
