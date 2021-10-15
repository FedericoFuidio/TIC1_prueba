package um.business.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ValidarExperienciaKey implements Serializable {

    @Column(name = "admin_id")
    long adminId;

    @Column(name = "experiencia_id")
    long experienciaId;

    public ValidarExperienciaKey(){

    }

    public ValidarExperienciaKey(long adminId, long experienciaId){

        this.adminId = adminId;
        this.experienciaId = experienciaId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
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
        ValidarExperienciaKey that = (ValidarExperienciaKey) o;
        return adminId == that.adminId && experienciaId == that.experienciaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, experienciaId);
    }
}
