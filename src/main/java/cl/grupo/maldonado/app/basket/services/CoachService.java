package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository repository;


    public List<Coach> listAll(){
        return repository.findAll();
    }

    public Coach findById( Integer oid ){
        return repository.findById( oid).get();
    }

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(Coach coach){
        repository.save(coach);
    }

}
