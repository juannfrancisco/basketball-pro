package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.repositories.PlayerRepository;
import cl.grupo.maldonado.app.basket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;


    @Autowired
    private PlayerRepository playerRepository;



    /**
     * @param team
     */
    public void save( Team team ){
        team.setPlayers( new ArrayList<>() );
        repository.save(team);
    }


    /**
     * @param team
     */
    @Transactional
    public void update( Team team ){
        repository.updateInfoById(team.getOid(),
                team.getBio(),
                team.getNameURL(),
                team.getContact().getPhone(),
                team.getContact().getEmail(),
                team.getName());
    }

    public List<Team> getAll(  ){
        return repository.findAll();
    }

    public Team getById( Integer oid ){
        Team team = repository.findById( oid ).get();
        team.setPlayers( playerRepository.findByCurrentTeam(team) );
        team.getPlayers().forEach(player -> player.setCurrentTeam(null));
        return team;

    }


    /**
     *
     * @param name
     * @return
     */
    public Team findByName( String name ){
        Team team = repository.findByNameURL( name ).get(0);
        //team.setPlayers( playerRepository.findByCurrentTeam(team) );
        //team.getPlayers().forEach(player -> player.setCurrentTeam(null));
        return team;

    }

    /**
     *
     * @param name
     * @return
     */
    public List<Player> findPlayersByName(String name ){
        Team team = findByName( name );
        List<Player> players = playerRepository.findByCurrentTeam(team);
        players.forEach(player -> player.setCurrentTeam(null));
        return players;
    }

    public void deleteById( Integer oid ){
         repository.deleteById(oid);
    }


    /**
     *
     * @param team
     * @param player
     */
    public void addPlayer( Team team, Player player ){
        player.setCurrentTeam( team );
        playerRepository.save(player);
    }


    public void deletePlayer( Integer oid ){
        playerRepository.deleteById(oid);
    }



}
