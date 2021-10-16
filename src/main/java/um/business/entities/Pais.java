package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(generator="pais_ids")
    @GenericGenerator(name="pais_ids", strategy = "increment")
    private long id;
    private String nombre;
    private String brev;

    public Pais(){

    }

    public Pais(String nombre, String brev){
        this.nombre = nombre;
        this.brev = brev;
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
}
