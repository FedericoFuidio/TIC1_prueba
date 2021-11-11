package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class Cupo {

    @Id
    @GeneratedValue(generator="cupos_ids")
    @GenericGenerator(name="cupos_ids", strategy = "increment")
    private long id;
    private int cupos;
    private DayOfWeek dia;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private int cuposLibres;

    @ManyToOne
    private Experiencia experiencia;

    public Cupo(){

    }

    public Cupo(int cupos, DayOfWeek dia, LocalTime horaApertura, LocalTime horaCierre, Experiencia experiencia){

        this.cupos = cupos;
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.experiencia = experiencia;
        this.cuposLibres = cupos;

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

    public DayOfWeek getDia() {
        return dia;
    }

    public void setDia(DayOfWeek dia) {
        this.dia = dia;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }
}
