package um.business.entities;

import javax.persistence.*;

@Entity
public class PreferenciaExperiencia {

    @EmbeddedId
    private PreferenciaExperienciaKey id;

    @ManyToOne
    @MapsId("preferenciaId")
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferencia;

    @ManyToOne
    @MapsId("experienciaId")
    @JoinColumn(name = "experiencia_id")
    private Experiencia experiencia;

    public PreferenciaExperiencia(){

    }

    public PreferenciaExperiencia(PreferenciaExperienciaKey id, Preferencia preferencia, Experiencia experiencia){

        this.id = id;
        this.preferencia = preferencia;
        this.experiencia = experiencia;
    }

    public PreferenciaExperienciaKey getId() {
        return id;
    }

    public void setId(PreferenciaExperienciaKey id) {
        this.id = id;
    }

    public Preferencia getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(Preferencia preferencia) {
        this.preferencia = preferencia;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }
}
