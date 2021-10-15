package um.business.entities;

import javax.persistence.*;

@Entity
public class PrefGeneralPrefEspecifica {

    @EmbeddedId
    private PrefGeneralPrefEspecificaKey id;

    @ManyToOne
    @MapsId("preferenciaGeneralId")
    @JoinColumn(name = "preferenciaGeneral_id")
    private PreferenciaGeneral preferenciaGeneral;

    @ManyToOne
    @MapsId("preferenciaEspecificaId")
    @JoinColumn(name = "preferenciaEspecifica_id")
    private PreferenciaEspecifica preferenciaEspecifica;

    public PrefGeneralPrefEspecifica(){

    }

    public PrefGeneralPrefEspecifica(PreferenciaEspecifica preferenciaEspecifica, PreferenciaGeneral preferenciaGeneral){

        this.preferenciaEspecifica = preferenciaEspecifica;
        this.preferenciaGeneral = preferenciaGeneral;
    }

    public PrefGeneralPrefEspecificaKey getId() {
        return id;
    }

    public void setId(PrefGeneralPrefEspecificaKey id) {
        this.id = id;
    }

    public PreferenciaGeneral getPreferenciaGeneral() {
        return preferenciaGeneral;
    }

    public void setPreferenciaGeneral(PreferenciaGeneral preferenciaGeneral) {
        this.preferenciaGeneral = preferenciaGeneral;
    }

    public PreferenciaEspecifica getPreferenciaEspecifica() {
        return preferenciaEspecifica;
    }

    public void setPreferenciaEspecifica(PreferenciaEspecifica preferenciaEspecifica) {
        this.preferenciaEspecifica = preferenciaEspecifica;
    }
}
