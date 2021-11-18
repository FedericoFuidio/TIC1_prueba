package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Operador {


    @Id
    @GeneratedValue(generator="operadores_ids")
    @GenericGenerator(name="operadores_ids", strategy = "increment")
    private long id;
    private String mail;
    private String foto;
    private String name;
    private String phone;
    private String descripcion;
    private String sitioWeb;
    private String ubicacion;
    private boolean validado;
    private byte[] image;

    public Operador(){

    }

    public Operador(String mail, String foto, String name,
                    String phone, String descripcion, String sitioWeb, String ubicacion){

        this.mail = mail;
        this.foto = foto;
        this.name = name;
        this.phone = phone;
        this.descripcion = descripcion;
        this.sitioWeb = sitioWeb;
        this.ubicacion = ubicacion;
        this.validado = true;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String contacto) {
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
