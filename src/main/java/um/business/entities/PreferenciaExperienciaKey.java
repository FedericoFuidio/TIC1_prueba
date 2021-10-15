package um.business.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PreferenciaExperienciaKey implements Serializable {

    @Column(name = "preferencia_id")
    long preferenciaId;

    @Column(name = "experiencia_id")
    long experienciaId;

    public PreferenciaExperienciaKey(){

    }

    public PreferenciaExperienciaKey(long preferenciaId, long experienciaId){
        this.experienciaId = experienciaId;
        this.preferenciaId = preferenciaId;
    }

    public long getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(long preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    public long getExperienciaId() {
        return experienciaId;
    }

    public void setExperienciaId(long experienciaId) {
        this.experienciaId = experienciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenciaExperienciaKey that = (PreferenciaExperienciaKey) o;
        return preferenciaId == that.preferenciaId && experienciaId == that.experienciaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferenciaId, experienciaId);
    }
}
