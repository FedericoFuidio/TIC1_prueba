package um.business.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reserva {

    @EmbeddedId
    private ReservaKey id;

    @ManyToOne
    @MapsId("turistaId")
    @JoinColumn(name = "turista_id")
    private Turista turista;

    @ManyToOne
    @MapsId("cupoId")
    @JoinColumn(name = "cupo_id")
    private Cupo cupo;


    private int cantidad;
    private Date fecha;
    private boolean aceptada;

    public Reserva(){

    }

    public Reserva(Turista turista, Cupo cupo, int cantidad, Date feha){

        this.turista = turista;
        this.cupo = cupo;
        this.cantidad = cantidad;
        this.fecha = feha;
    }

    public ReservaKey getId() {
        return id;
    }

    public void setId(ReservaKey id) {
        this.id = id;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Cupo getCupo() {
        return cupo;
    }

    public void setCupo(Cupo cupo) {
        this.cupo = cupo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }
}
