package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.game.*;
import cl.grupo.maldonado.app.basket.repositories.MatchRepository;
import cl.grupo.maldonado.app.basket.repositories.MatchStatRepository;
import cl.grupo.maldonado.app.basket.repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private MatchStatRepository matchStatRepository;

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

    @Transactional
    public void updateState(Match match){

        List<MatchStat> stats =  matchStatRepository.findByMatch( match);
        double scoreLocal = getScore( TypeTeam.LOCAL , stats);
        double scoreVisitor = getScore( TypeTeam.VISITOR , stats);

        //TODO : ADD STATS GLOBAL ChampionshipTeam

        repository.updateStateById( match.getOid(),
                match.getState(),
                (int)scoreLocal,
                (int)scoreVisitor);
    }


    public double getScore(TypeTeam type, List<MatchStat> stats){
        return stats.stream()
                .filter(matchStat -> matchStat.getTypeTeam().equals( type ))
                .filter( matchStat -> matchStat.getType().equals(TypeStat.PTS) )
                .mapToDouble( value ->  value.getValue() )
                .sum();
    }

}
