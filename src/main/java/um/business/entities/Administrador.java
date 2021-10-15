package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Administrador extends User{

    @Id
    @GeneratedValue(generator="admin_ids")
    @GenericGenerator(name="admin_ids", strategy = "increment")
    private long id;

    private String nombre;
    private String apellido;

    public Administrador(){

    }

    public Administrador(String nombre, String apellido, String password, String mail, String userName){

        super(mail, userName, password);
        this.nombre = nombre;
        this.apellido = apellido;

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
