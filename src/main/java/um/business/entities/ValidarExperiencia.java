package um.business.entities;

import javax.persistence.*;

@Entity
public class ValidarExperiencia {

    @EmbeddedId
    private ValidarExperienciaKey id;

    @ManyToOne
    @MapsId("adminId")
    @JoinColumn(name = "admin_id")
    private Administrador admin;

    @ManyToOne
    @MapsId("experienciaId")
    @JoinColumn(name = "experiencia_id")
    private Experiencia experiencia;

    private boolean resulatado;

    public ValidarExperiencia(){

    }

    public ValidarExperiencia(Administrador admin, Experiencia experiencia, boolean resulatado){

        this.admin = admin;
        this.experiencia = experiencia;
        this.resulatado = resulatado;
    }

    public ValidarExperienciaKey getId() {
        return id;
    }

    public void setId(ValidarExperienciaKey id) {
        this.id = id;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public boolean isResulatado() {
        return resulatado;
    }

    public void setResulatado(boolean resulatado) {
        this.resulatado = resulatado;
    }
}
