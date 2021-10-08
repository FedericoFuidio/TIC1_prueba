package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "adminOperadores")
public class AdminOperador {

    @Id
    @GeneratedValue(generator="admins_ids")
    @GenericGenerator(name="admins_ids", strategy = "increment")
    private Long id;
    private String nombre;
    private String apellido;
    private String password;
    private boolean validado;

    @ManyToOne
    private Operador operador;

    public AdminOperador(){

    }

    public AdminOperador(String nombre, String apellido, String password, Operador operador){
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.operador = operador;
        this.validado = false;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }
}
