package um.persistance;

import um.business.entities.Operador;

public interface OperadorRepository {
    /**
     * Retorna un cliente por documento si encuentra mas de una lanza una excepcion
     * @param userName
     * @return
     */

    Operador findOperadorByUserName(String userName);
    Operador findOperadorByMail(String mail);

}
