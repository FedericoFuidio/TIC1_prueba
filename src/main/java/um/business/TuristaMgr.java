package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.*;
import um.business.exception.InvalidInformation;
import um.business.exception.RepitedMail;
import um.business.exception.RepitedUserName;
import um.persistance.*;

@Service
public class TuristaMgr {

    @Autowired
    private TuristaRepository turistaRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AdminOperadorRepository adminOperadorRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ExperienciaGeneralRepository experienciaGeneralRepository;

    @Autowired
    private PreferenciaTuristaRepository preferenciaTuristaRepository;

    @Autowired
    private PreferenciaRepository preferenciaRepository;

    @Autowired
    private PreferenciaExperienciaRepository preferenciaExperienciaRepository;


    //Agregamos un Turista al sistema:
    public void addTurista(String mail, String userName, String password, String name,
                      String apellido, String pais, Long passport) throws RepitedUserName,
                        InvalidInformation, RepitedMail {

        if(mail == null || mail.equals("") || userName == null || userName.equals("") ||
            password == null || password.equals("") || name == null || name.equals("") ||
            apellido == null || apellido.equals("") || pais == null || pais.equals("")
            || passport == null){

            throw new InvalidInformation();
        }


        if(turistaRepository.findTuristaByUserName(userName) != null
        || administradorRepository.findAdministradorByUserName(userName) != null
        || adminOperadorRepository.findAdminOperadorByUsername(userName) != null){
            throw new RepitedUserName();
        }

        if(turistaRepository.findTuristaByMail(mail) != null){
            throw new RepitedMail();
        }
        Pais country = paisRepository.findPaisByNombre(pais);

        Turista nuevo = new Turista(mail, userName, password, name, apellido, country, passport);
        turistaRepository.save(nuevo);
        Iterable<Turista> turistaList = turistaRepository.findAll();

    }

    public Iterable<Turista> GetTuristas(){

        return turistaRepository.findAll();

    }

    public Turista getTuritstaByUserName(String userName){
        return turistaRepository.findTuristaByUserName(userName);
    }


    public ObservableList<Experiencia> recomendaciones(Turista turista){

        ObservableList<Experiencia> recomendados = FXCollections.observableArrayList();
        Iterable<Experiencia> todas = experienciaGeneralRepository.findAll();
        Iterable<PreferenciaTurista> prefs = preferenciaTuristaRepository.getAllByTurista(turista);
        ObservableList<Long> preferencias = FXCollections.observableArrayList();
        ObservableList<ExperienciaComparable> comparacion = FXCollections.observableArrayList();

        ObservableList<Experiencia> experiencias_validadas = FXCollections.observableArrayList();

        for(PreferenciaTurista pt : prefs){

            //Obtenemos una lista con todas las preferencias del turista
            preferencias.add(pt.getPreferencia().getId());

        }

        for(Experiencia e : todas){

            //Solo usamos experiancias que estan validadas, y puestas por operadores validados:
            if(e.isValidado() && e.getOperador().isValidado()){

                experiencias_validadas.add(e);
            }
        }

        //preferencias = preferencias del turista.
        double n = preferencias.size();

        for(Experiencia e : experiencias_validadas){

            //Definimos una experienciaComparable, el puntaje inicial es 0
            ExperienciaComparable experienciaComparable = new ExperienciaComparable(e);

            //Definimos una lista de Preferencias donde esten todas las preferencias asociadas
            //a la experiencia:
            Iterable<PreferenciaExperiencia> temp = preferenciaExperienciaRepository.getAllByExperiencia(e);
            ObservableList<Preferencia> preferenciasE = FXCollections.observableArrayList();
            for(PreferenciaExperiencia pe : temp){

                preferenciasE.add(pe.getPreferencia());

            }

            //por cada preferencia del usuario que sea igual a las preferencias de la experiencia e,
            //aumentamos el contador de e en 1
            for(Preferencia pe : preferenciasE){

                if(preferencias.contains(pe.getId())){
                    experienciaComparable.setPuntaje(experienciaComparable.getPuntaje() + 1);
                }

            }

            experienciaComparable.setPuntaje((experienciaComparable.getPuntaje()*(5/n)*0.60 + experienciaComparable.getExperiencia().getPuntaje()*0.40));

            comparacion.add(experienciaComparable);

        }

        /*
        Despues del loop for, vamos a tener en comparacion las experienciasComparables con su respectivo puntaje,
        ahora solo hace falta ordenarlas
         */

        /*

        ExperienciaComparable ec1 = comparacion.get(0);
        ExperienciaComparable ec2 = comparacion.get(1);
        System.out.println(ec1.compareTo(ec2));
        System.out.println("_________________________");
        for(ExperienciaComparable ec : comparacion){


            System.out.print(ec.getExperiencia().getNombre());
            System.out.print("Puntaje: " + ec.getPuntaje());
            System.out.println();

        }

        System.out.println("________________________");
        ordenar(comparacion);

        for(ExperienciaComparable ec : comparacion){

            System.out.print(ec.getExperiencia().getNombre());
            System.out.print("Puntaje: " + ec.getPuntaje());
            System.out.println();

        }

         */

        ordenar(comparacion);



        int numero_experiencias = comparacion.size() - 1;
        if(numero_experiencias > 15){
            numero_experiencias = 15;
        }



        for(int temp = 0; temp <= numero_experiencias; temp ++){


            //Queremos que aparezca como recomendados las 15 mejores rankeadas
            recomendados.add(comparacion.get(temp).getExperiencia());
        }




        return recomendados;
    }

    private void ordenar(ObservableList<ExperienciaComparable> lista){

        int longitud = lista.size();

        for(int i = 0; i < longitud - 1; i++){

            for(int j = 0; j < longitud - i -1; j++){

                if(lista.get(j).compareTo(lista.get(j+1)) > 0){

                    //Cambio de lugar:
                    ExperienciaComparable cambio = lista.get(j);
                    lista.set(j, lista.get(j+1));
                    lista.set(j+1, cambio);
                }
            }
        }

    }

}
