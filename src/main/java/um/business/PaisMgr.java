package um.business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Pais;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.PaisRepository;

@Service
public class PaisMgr {

    @Autowired
    PaisRepository paisRepository;

    public void addPais(String nombre, String brev) throws InvalidInformation, ClassAlreadyExists {

        if(nombre == null || nombre.equals("") || brev == null || brev.equals("")){
            throw new InvalidInformation();
        }

        if(paisRepository.findPaisByNombre(nombre) != null){
            throw new ClassAlreadyExists();
        }

        Pais pais = new Pais(nombre, brev);
        paisRepository.save(pais);

    }

    public ObservableList<String> getPaises() {

        ObservableList<String> paises = FXCollections.observableArrayList();
        Iterable<Pais> temp = paisRepository.findAll();

        for(Pais p : temp){
            paises.add(p.getNombre());
        }

        return paises;
    }
}
