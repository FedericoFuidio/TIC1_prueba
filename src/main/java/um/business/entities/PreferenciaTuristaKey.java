package um.business.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PreferenciaTuristaKey implements Serializable {

    @Column(name = "turista_id")
    long turistaId;

    @Column(name = "preferencia_id")
    long preferenciaId;

    public PreferenciaTuristaKey(){

    }

    public PreferenciaTuristaKey(long turistaId, long preferenciaId){

        this.turistaId = turistaId;
        this.preferenciaId = preferenciaId;
    }

    public long getTuristaId() {
        return turistaId;
    }

    public void setTuristaId(long turistaId) {
        this.turistaId = turistaId;
    }

    public long getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(long preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenciaTuristaKey that = (PreferenciaTuristaKey) o;
        return turistaId == that.turistaId && preferenciaId == that.preferenciaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turistaId, preferenciaId);
    }
}
