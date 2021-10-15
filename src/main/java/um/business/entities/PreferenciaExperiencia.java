package um.business.entities;

import javax.persistence.*;

@Entity
public class PreferenciaExperiencia {

    @EmbeddedId
    private PreferenciaExperienciaKey id;

    @ManyToOne
    @MapsId("preferenicaId")
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferencia;

    @ManyToOne
    @MapsId("experienciaId")
    @JoinColumn(name = "experiencia_id")
    private ExperienciaGeneral experiencia;

    public PreferenciaExperiencia(){

    }

    public PreferenciaExperiencia(Preferencia preferencia, ExperienciaGeneral experiencia){

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

    public ExperienciaGeneral getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaGeneral experiencia) {
        this.experiencia = experiencia;
    }
}
