package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Cupo {

    @Id
    @GeneratedValue(generator="cupos_ids")
    @GenericGenerator(name="cupos_ids", strategy = "increment")
    private long id;
    private int cupos;
    private Date fecha;
    private int cuposLibres;

    @ManyToOne
    private Experiencia experiencia;

    public Cupo(){

    }

    public Cupo(int cupos, Date fecha, Experiencia experiencia){

        this.cupos = cupos;
        this.fecha = fecha;
        this.experiencia = experiencia;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCuposLibres() {
        return cuposLibres;
    }

    public void setCuposLibres(int cuposLibres) {
        this.cuposLibres = cuposLibres;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
}
