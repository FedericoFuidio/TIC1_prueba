package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "preferencias_generales")
public class PreferenciaGeneral {

    @Id
    @GeneratedValue(generator="pref_ids")
    @GenericGenerator(name="pref_ids", strategy = "increment")
    private long id;
    private String nombre;
    private String descripcion;

    @ManyToMany
    private List<Turista> turistaList;


    public PreferenciaGeneral(){

    }

    public PreferenciaGeneral(String nombre, String descripcion){
        this.descripcion = descripcion;
        this.nombre = nombre;

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

    public List<Turista> getTuristaList() {
        return turistaList;
    }

}
