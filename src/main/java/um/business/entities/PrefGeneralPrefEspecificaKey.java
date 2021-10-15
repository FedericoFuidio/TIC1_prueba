package um.business.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrefGeneralPrefEspecificaKey implements Serializable {

    @Column(name = "preferenciaGeneral_id")
    long preferenciaGeneralId;

    @Column(name = "preferenciaEspecifica_id")
    long preferenciaEspecificaId;

    public PrefGeneralPrefEspecificaKey(){

    }

    public PrefGeneralPrefEspecificaKey(long preferenciaGeneralId, long preferenciaEspecificaId){

        this.preferenciaGeneralId = preferenciaGeneralId;
        this.preferenciaEspecificaId = preferenciaEspecificaId;
    }

    public long getPreferenciaGeneralId() {
        return preferenciaGeneralId;
    }

    public void setPreferenciaGeneralId(long preferenciaGeneralId) {
        this.preferenciaGeneralId = preferenciaGeneralId;
    }

    public long getPreferenciaEspecificaId() {
        return preferenciaEspecificaId;
    }

    public void setPreferenciaEspecificaId(long preferenciaEspecificaId) {
        this.preferenciaEspecificaId = preferenciaEspecificaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrefGeneralPrefEspecificaKey that = (PrefGeneralPrefEspecificaKey) o;
        return preferenciaGeneralId == that.preferenciaGeneralId && preferenciaEspecificaId == that.preferenciaEspecificaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferenciaGeneralId, preferenciaEspecificaId);
    }
}
