package um.business.entities;

import javax.persistence.*;

@Entity
public class PreferenciaTurista {

    @EmbeddedId
    private PreferenciaTuristaKey id;

    @ManyToOne
    @MapsId("turistaId")
    @JoinColumn(name = "turista_id")
    private Turista turista;

    @ManyToOne
    @MapsId("preferenciaId")
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferencia;

    private int puntuacion;

    public PreferenciaTurista(){

    }

    public PreferenciaTurista(Turista turista, Preferencia preferencia, int puntuacion){

        this.turista = turista;
        this.preferencia = preferencia;
        this.puntuacion = puntuacion;

    }

    public PreferenciaTuristaKey getId() {
        return id;
    }

    public void setId(PreferenciaTuristaKey id) {
        this.id = id;
    }

    public Turista getTurista() {
        return turista;
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
