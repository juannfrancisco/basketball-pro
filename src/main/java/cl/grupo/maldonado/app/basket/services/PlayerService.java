package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Coach;
import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.repositories.CoachRepository;
import cl.grupo.maldonado.app.basket.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    public List<Player> listAll(){
        return repository.findAll();
    }

    public Player findById( Integer oid ){
        return repository.findById( oid).get();
    }

    public List<Player> findByTeam( Team team ){
        return repository.findByCurrentTeam(team);
    }

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(Player player){
        repository.save(player);
    }

}
