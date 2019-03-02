package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Court;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipTeam;
import cl.grupo.maldonado.app.basket.repositories.ChampionshipRepository;
import cl.grupo.maldonado.app.basket.repositories.ChampionshipTeamRepository;
import cl.grupo.maldonado.app.basket.repositories.CourtRepository;
import cl.grupo.maldonado.app.basket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionshipService {

    @Autowired
    private ChampionshipRepository repository;

    @Autowired
    private ChampionshipTeamRepository championshipTeamRepository;


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
        ChampionshipTeam x = new ChampionshipTeam();
        x.setChampionship(new Championship(oid));
        x.setTeam(team);
        championshipTeamRepository.save(x);
    }


    /**
     *
     * @param championship
     * @return
     */
    public List<Team> findTeamsByChampionship(Championship championship){
        List<Team> teams = new ArrayList<>();
        List<ChampionshipTeam>  champList = championshipTeamRepository.findByChampionship(championship);
        champList.forEach(championshipTeam -> teams.add(championshipTeam.getTeam()));
        return teams;
    }


}
