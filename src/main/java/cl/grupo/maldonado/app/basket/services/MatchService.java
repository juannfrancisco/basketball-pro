package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchState;
import cl.grupo.maldonado.app.basket.repositories.MatchRepository;
import cl.grupo.maldonado.app.basket.repositories.PlayerRepository;
import cl.grupo.maldonado.app.basket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private PlayerRepository playerRepository;


    public List<Match> listAll(){
        return repository.findAll();
    }

    public Match findById( Integer oid ){
        Match match = repository.findById( oid).get();
        return match;
    }

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(Match match){
        match.setState( MatchState.PENDING );
        repository.save(match);
    }

}
