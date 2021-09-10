package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(generator="clients_ids")
    @GenericGenerator(name="clients_ids", strategy = "increment")
    private long id;
    private String country;
    private String userName;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private LocalDate birthDay;

    public User() {
    }

    public User(String nombre, String apellido, String userName, String mail, LocalDate birthDate, String country, String password) {
        this.mail = mail;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.country = country;
        this.userName = userName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

