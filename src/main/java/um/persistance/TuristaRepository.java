package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Turista;

public interface TuristaRepository extends CrudRepository<Turista, Long> {

    /**
     * Retorna un operador por userName si encuentra mas de una lanza una excepcion
     * @param userName
     * @return
     */

    Turista findTuristaByUserName(String userName);
    Turista findTuristaByMail(String mail);
}
