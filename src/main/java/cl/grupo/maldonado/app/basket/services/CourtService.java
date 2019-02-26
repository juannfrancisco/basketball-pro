package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.Court;
import cl.grupo.maldonado.app.basket.repositories.CoachRepository;
import cl.grupo.maldonado.app.basket.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService {

    @Autowired
    private CourtRepository repository;


    public List<Court> listAll(){
        return repository.findAll();
    }

    public Court findById( Integer oid ){
        return repository.findById( oid).get();
    }

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(Court court){
        repository.save(court);
    }

}
