package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
public class Calificacion {

    @Id
    @GeneratedValue(generator="calificaciones_ids")
    @GenericGenerator(name="calificaciones_ids", strategy = "increment")
    private long id;
    private Date fecha;
    private int puntaje;
    private String cometario;
    private boolean esPublica;

    @ManyToOne
    Reserva reserva;

    @ManyToOne
    Turista turista;

    public Calificacion(){

    }

    public Calificacion(Reserva reserva, Turista turista, Date fecha, int puntaje, String comentario, boolean esPublica){

        this.reserva = reserva;
        this.turista = turista;
        this.fecha = fecha;
        this.puntaje = puntaje;
        this.cometario = comentario;
        this.esPublica = esPublica;

    }

    public Calificacion(Date fecha, int puntaje, boolean esPublica){
        this.fecha = fecha;
        this.puntaje = puntaje;
        this.esPublica = esPublica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }
}
