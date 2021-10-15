package um.business.entities;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Turista extends User{


    private String name;
    private String apellido;
    private String pais;
    @Column(unique = true)
    private Long passport;



    public Turista(){
    }


    public Turista(String mail, String userName, String password, String name, String apellido, String pais, Long passport){

        super(mail, userName, password);
        this.name = name;
        this.apellido = apellido;
        this.pais = pais;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getPassport() {
        return passport;
    }

    public void setPassport(Long passport) {
        this.passport = passport;
    }
}
