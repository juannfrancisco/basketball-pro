package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Court;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.repositories.ChampionshipRepository;
import cl.grupo.maldonado.app.basket.repositories.CourtRepository;
import cl.grupo.maldonado.app.basket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository repository;

    @Autowired
    private TeamRepository teamRepository;


    public List<Championship> listAll(){
        return repository.findAll();
    }

    public Championship findById( Integer oid ){
        return repository.findById( oid).get();
    }

    public void deleteById( Integer oid ){
        repository.deleteById(oid);
    }

    public void save(Championship championship){
        repository.save(championship);
    }

    public void addTeam( Integer oid,  Team team){
        Team teamUpdate = teamRepository.findById( team.getOid() ).get();
        teamUpdate.setChampionship( new Championship(oid) );
        teamRepository.save(teamUpdate);
    }


}
