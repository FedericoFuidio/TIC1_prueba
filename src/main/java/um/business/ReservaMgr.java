package um.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import um.business.entities.Cupo;
import um.business.entities.Reserva;
import um.business.entities.ReservaKey;
import um.business.entities.Turista;
import um.business.exception.ClassAlreadyExists;
import um.business.exception.InvalidInformation;
import um.persistance.ReservaRepository;

import java.sql.Date;

@Service
public class ReservaMgr {

    @Autowired
    private ReservaRepository reservaRepository;

    public void addReserva(Turista turista, Cupo cupo, Integer cantidad, Date feha) throws InvalidInformation, ClassAlreadyExists {
        if(turista == null || cupo == null || cantidad == null || feha == null){
            throw new InvalidInformation();
        }
        if(reservaRepository.findAllByFechaAndTuristaAndCupo(feha, turista, cupo) != null){
            throw new ClassAlreadyExists();
        }

        if(cupo.getCuposLibres() < cantidad){
            throw new InvalidInformation();
        }

        ReservaKey id = new ReservaKey(turista.getId(), cupo.getId());

        Reserva r = new Reserva(id, turista, cupo, cantidad, feha);
        reservaRepository.save(r);
    }
}
