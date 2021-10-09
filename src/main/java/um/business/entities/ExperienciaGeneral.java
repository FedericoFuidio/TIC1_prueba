package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Experiencias")
public class ExperienciaGeneral {

    @Id
    @GeneratedValue(generator="experiencias_ids")
    @GenericGenerator(name="experiencias_ids", strategy = "increment")
    private Long id;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private boolean validado;
    private String foto;

    @ManyToOne
    private Operador operador;

    public ExperienciaGeneral(){

    }

    public ExperienciaGeneral(String nombre, String ubicacion, String descripcion, String foto, Operador operador){

        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.operador = operador;
        this.validado = false;
        this.descripcion = descripcion;
        this.foto = foto;

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }
}
