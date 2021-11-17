package um.persistance;


import org.springframework.data.repository.CrudRepository;
import um.business.entities.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Long> {

    Administrador findAdministradorById(long id);
    Administrador findAdministradorByUserName(String username);
}
