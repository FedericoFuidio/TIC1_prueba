package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cupo {

    @Id
    @GeneratedValue(generator="cupos_ids")
    @GenericGenerator(name="cupos_ids", strategy = "increment")
    private long id;
    private int cupos;
    private String dia;
    private String horaInicio;
    private String horaFin;
    private int cuposLibres;

    @ManyToOne
    private Experiencia experiencia;

    public Cupo(){

    }

    public Cupo(int cupos, String dia, String horaInicio, String horaFin, Experiencia experiencia){

        this.cupos = cupos;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
