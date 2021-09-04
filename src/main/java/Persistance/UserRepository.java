package persistance;

import org.springframework.data.repository.CrudRepository;
import business.Entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param document
     * @return
     */
    User findOneByDocument(long document);

}
