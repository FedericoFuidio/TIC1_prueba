package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Operador;

public interface OperadorRepository extends CrudRepository<Operador, Long> {
    /**
     * Retorna un turista por documento si encuentra mas de una lanza una excepcion
     * @param mail
     * @return
     */

    Operador findOperadorByMail(String mail);

}
