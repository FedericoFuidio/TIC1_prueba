package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.User;


public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param userName
     * @return
     */
    User findOneByUserName(String userName);
    User findOneByUserNameAndPassword(String userName, String password);

}
