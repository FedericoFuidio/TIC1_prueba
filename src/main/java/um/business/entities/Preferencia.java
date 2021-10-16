package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Preferencia {

    @Id
    @GeneratedValue(generator="prefs_ids")
    @GenericGenerator(name="prefs_ids", strategy = "increment")
    private long id;
    private String nombre;
    private String descripcion;



    public Preferencia(){

    }

    public Preferencia(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
