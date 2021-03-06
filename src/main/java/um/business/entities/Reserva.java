package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Reserva {


    @Id
    @GeneratedValue(generator="reserva_id")
    @GenericGenerator(name="reserva_id", strategy = "increment")
    @Column(name = "reservas_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "turista_id")
    private Turista turista;

    @ManyToOne
    @JoinColumn(name = "cupo_id")
    private Cupo cupo;


    private int cantidad;


    private Date fecha;


    private Time hora;
    private boolean aceptada;
    private boolean cancelada;

    public Reserva(){

    }

    public Reserva(Turista turista, Cupo cupo, int cantidad, Date fecha, Time hora){


        this.turista = turista;
        this.cupo = cupo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.hora = hora;
        this.cancelada = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTurista() {
        return turista.getUserName();
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



    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }


}
