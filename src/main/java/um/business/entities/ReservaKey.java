package um.business.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReservaKey implements Serializable {

    @Column(name = "turista_id")
    private long turistaId;

    @Column(name = "cupo_id")
    private long cupoId;





    public ReservaKey(){

    }

    public ReservaKey(long turistaId, long cupoId){

        this.cupoId = cupoId;
        this.turistaId = turistaId;

    }

    public long getTuristaId() {
        return turistaId;
    }

    public void setTuristaId(long turistaId) {
        this.turistaId = turistaId;
    }

    public long getCupoId() {
        return cupoId;
    }

    public void setCupoId(long cupoId) {
        this.cupoId = cupoId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservaKey that = (ReservaKey) o;
        return turistaId == that.turistaId && cupoId == that.cupoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turistaId, cupoId);
    }
}
