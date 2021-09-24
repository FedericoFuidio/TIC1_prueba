package um.business.entities;

import javax.persistence.Entity;

@Entity
public class Operador extends User{

    private String foto;
    private String name;
    private String phone;
    private String descripcion;
    private String sitioWeb;
    private String ubicacion;
    private boolean validado;

    public Operador(){

    }

    public Operador(String mail, String userName, String password, String foto, String name,
                    String phone, String descripcion, String sitioWeb, String ubicacion){

        super(mail, userName, password);
        this.foto = foto;
        this.name = name;
        this.phone = phone;
        this.descripcion = descripcion;
        this.sitioWeb = sitioWeb;
        this.ubicacion = ubicacion;
        this.validado = false;

    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacto() {
        return phone;
    }

    public void setContacto(String contacto) {
        this.phone = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
