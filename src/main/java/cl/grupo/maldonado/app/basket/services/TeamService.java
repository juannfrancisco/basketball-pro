package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipState;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipTeam;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.repositories.ChampionshipTeamRepository;
import cl.grupo.maldonado.app.basket.repositories.MatchRepository;
import cl.grupo.maldonado.app.basket.repositories.PlayerRepository;
import cl.grupo.maldonado.app.basket.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    @Autowired
    private ChampionshipTeamRepository championshipTeamRepo;

    @Autowired
    private MatchRepository matchRepository;

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

    public List<Championship> findChampionshipsByName(String name ){
        Team team = findByName( name );
        List<Championship> result = new ArrayList<>();
        List<ChampionshipTeam> ct =championshipTeamRepo.findByTeam(team);
        ct.forEach(championshipTeam -> result.add( championshipTeam.getChampionship() ));
        return result;
    }

    public List<Match> findMatchesByName(String name ){
        Team team = findByName( name );
        List<Match> result = new ArrayList<>();
        result = matchRepository.findByVisitorOrLocal(team,team);
        return result;
    }

    public  List<List<ChampionshipTeam>> findStandingsByName(String name ){
        //Map<String, Object> result = new HashMap<>();
        List<List<ChampionshipTeam>> result = new ArrayList<>();

        Team team = findByName( name );
        Stream<ChampionshipTeam> sct = championshipTeamRepo.findByTeam(team).stream()
                .filter(championshipTeam -> (null == championshipTeam.getChampionship().getState() ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.PENDING ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.IN_PROGRESS ) );
        List<ChampionshipTeam> ct = sct.collect(Collectors.toList());

        ct.forEach( championshipTeam -> {
            List<ChampionshipTeam> stats = championshipTeamRepo.findByChampionship(championshipTeam.getChampionship());
            result.add(stats);
        } );

        return result;
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


    @Transactional
    public void updatePlayer( Team team, Player player ){

        playerRepository.updateInfoById(player.getOid(),
                player.getNumber(),
                player.getPosition(),
                player.getName(),
                player.getLastName(),
                player.getHeight(),
                player.getWeight(),
                player.getGender(),
                player.getBirthdate());
    }


    public void deletePlayer( Integer oid ){
        playerRepository.deleteById(oid);
    }



}
