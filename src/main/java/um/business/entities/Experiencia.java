package um.business.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity(name = "Experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(generator="experiencias_ids")
    @GenericGenerator(name="experiencias_ids", strategy = "increment")
    private Long id;
    private String nombre;
    private String ubicacion;
    private String descripcion;
    private boolean validado;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] foto;
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] mapa;
    private double puntaje;
    private int puntajeTotal;

    private int horaInicio;
    private int horaFin;
    private int calificaciones; //Representa la cantidad de calificaciones

    //calificaciones y puntajes son atributos calculados,
    //los guardamos en la clase para ahorrar tiempo en el algoritmo de recomendacion.


    @ManyToOne
    private Operador operador;

    public Experiencia(){

    }

    public Experiencia(String nombre, String ubicacion, String descripcion, byte[] foto, byte[] mapa,Operador operador){

        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.operador = operador;
        this.validado = false;
        this.descripcion = descripcion;
        this.foto = foto;
        this.mapa = mapa;
        this.calificaciones = 0;
        this.puntaje = 0;
        this.puntajeTotal = 0;

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

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getMapa() {
        return mapa;
    }

    public void setMapa(byte[] mapa) {
        this.mapa = mapa;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }

    public int getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(int calificaciones) {
        this.calificaciones = calificaciones;
    }



    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    public String getNombreOperador(){
        return  this.operador.getName();
    }
}
