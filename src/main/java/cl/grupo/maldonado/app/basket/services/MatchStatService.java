package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchStat;
import cl.grupo.maldonado.app.basket.core.game.MatchState;
import cl.grupo.maldonado.app.basket.repositories.MatchRepository;
import cl.grupo.maldonado.app.basket.repositories.MatchStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchStatService {

    @Autowired
    private MatchStatRepository repository;



    public MatchStat findById( Integer oid ){
        return repository.findById( oid).get();
    }


    public List<MatchStat> findByMatch( Match match ){
        return repository.findByMatch(match);
    }


    public List<MatchStat> findByPlayer(Player player){
        return repository.findByPlayer( player );
    };

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(MatchStat match){
        repository.save(match);
    }

}
