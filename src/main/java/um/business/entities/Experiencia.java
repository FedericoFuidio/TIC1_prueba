package um.business.entities;

import com.mysql.cj.protocol.ColumnDefinition;
import javafx.scene.Node;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(generator="experiencias_ids")
    @GenericGenerator(name="experiencias_ids", strategy = "increment")
    private Long id;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private boolean validado;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] foto;

    @ManyToOne
    private Operador operador;

    public Experiencia(){

    }

    public Experiencia(String nombre, String ubicacion, String descripcion, byte[] foto, Operador operador){

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
