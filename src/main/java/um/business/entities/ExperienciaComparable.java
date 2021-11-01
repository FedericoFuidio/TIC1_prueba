package um.business.entities;

public class ExperienciaComparable implements Comparable<ExperienciaComparable> {

    private Experiencia experiencia;
    private double puntaje;

    public ExperienciaComparable(Experiencia experiencia, double puntaje){
        this.experiencia = experiencia;
        this.puntaje = puntaje;
    }

    public ExperienciaComparable(Experiencia experiencia){
        this.experiencia = experiencia;
        this.puntaje = 0;
    }

    public Experiencia getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Experiencia experiencia) {
        this.experiencia = experiencia;
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }


    @Override
    public int compareTo(ExperienciaComparable o) {
        int resultado = 0;

        double resta = this.puntaje - o.puntaje;
        if(resta < 0) { //o.puntaje es mayor a puntaje

            resultado = 1;
        } else if(resta > 0){
            resultado = -1;
        }

        return resultado;
    }
}
