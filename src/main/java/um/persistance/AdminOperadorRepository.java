package um.persistance;

import org.springframework.data.repository.CrudRepository;
import um.business.entities.AdminOperador;

public interface AdminOperadorRepository extends CrudRepository<AdminOperador, Long> {

    AdminOperador findAdminOperadorByApellido(String apellido);
    AdminOperador findAdminOperadorByNombre(String nombre);
}
