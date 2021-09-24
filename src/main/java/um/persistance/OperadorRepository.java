package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.Operador;

public interface OperadorRepository extends CrudRepository<Operador, Long> {
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param userName
     * @return
     */

    Operador findOperadorByUserName(String userName);
    Operador findOperadorByMail(String mail);

}
