package um.business.entities;

import javax.persistence.Entity;

@Entity(name = "preferencias_generales")
public class PreferenciaGeneral extends Preferencia{

    private String nombre;
    private String descripcion;


    public PreferenciaGeneral(){

    }

    public PreferenciaGeneral(String nombre, String descripcion){
        super(nombre, descripcion);

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
