package cl.grupo.maldonado.app.basket.services;

import cl.grupo.maldonado.app.basket.core.Player;
import cl.grupo.maldonado.app.basket.core.Team;
import cl.grupo.maldonado.app.basket.core.TeamStatistics;
import cl.grupo.maldonado.app.basket.core.championship.Championship;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipState;
import cl.grupo.maldonado.app.basket.core.championship.ChampionshipTeam;
import cl.grupo.maldonado.app.basket.core.game.Match;
import cl.grupo.maldonado.app.basket.core.game.MatchState;
import cl.grupo.maldonado.app.basket.core.game.TypeStat;
import cl.grupo.maldonado.app.basket.repositories.*;
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
    private MatchStatRepository matchStatRepository;

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
                null != team.getContact() ? team.getContact().getPhone() : null,
                null != team.getContact() ? team.getContact().getEmail() : null,
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

    public Match findLastMatch( String name ){
        Team team = findByName( name );
        return matchRepository.findLastByTeam(team, MatchState.FINALIZED).get(0);
    }

    public TeamStatistics findStatisticsLastSeason( String name ){
        Team team = findByName( name );
        List<ChampionshipTeam> champions = championshipTeamRepo.findByTeam(team).stream()
                .filter(championshipTeam -> (null == championshipTeam.getChampionship().getState() ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.PENDING ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.IN_PROGRESS ) ).
                        collect(Collectors.toList());

        if(champions.isEmpty()){
            throw new RuntimeException("No esta presente en ningun campeonato");
        }

        champions.get(0);

        Integer pointsPerGameLocal = matchRepository.scoreLocal(team);
        Integer pointsPerGameVisitor = matchRepository.scoreVisitor(team);
        Integer assistsPerGame = matchStatRepository.statisticsPerGame(team.getOid(), TypeStat.AST);
        Integer reboundsPerGame = matchStatRepository.statisticsPerGame(team.getOid(), TypeStat.OR);
        Integer threePointersPerGame = matchStatRepository.statisticsPerGame(team.getOid(), TypeStat.PTS);

        Integer countGamesLocal = 0;
        Integer countGamesVisitor = 0;
        List<Match> matches = matchRepository.findLastByTeam( team, MatchState.FINALIZED );
        for( Match match: matches ){
            if( match.getVisitor().getOid().equals( team.getOid() ) ){
                countGamesVisitor++;
            }else{
                countGamesLocal++;
            }

        }

        Integer countGames = matches.size();



        TeamStatistics statistics = new TeamStatistics();
        statistics.setAssistsPerGame(assistsPerGame);
        statistics.setThreePointersPerGame(threePointersPerGame);
        statistics.setReboundsPerGame(reboundsPerGame);
        statistics.setPointPerGameLocal(pointsPerGameLocal);
        statistics.setPointPerGameVisitor(pointsPerGameVisitor);
        statistics.setCountGames(countGames);
        statistics.setCountGamesLocal(countGamesLocal);
        statistics.setCountGamesVisitor(countGamesVisitor);

        return statistics;

    }




    public  List<List<ChampionshipTeam>> findAllStandingsByName(String name ){
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


    public  List<ChampionshipTeam> findStandingsByName(String name ){
        List<ChampionshipTeam> result = new ArrayList<>();

        Team team = findByName( name );
        Stream<ChampionshipTeam> sct = championshipTeamRepo.findByTeam(team).stream()
                .filter(championshipTeam -> (null == championshipTeam.getChampionship().getState() ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.PENDING ||
                        championshipTeam.getChampionship().getState() == ChampionshipState.IN_PROGRESS ) );
        List<ChampionshipTeam> ct = sct.collect(Collectors.toList());

        ct.forEach( championshipTeam -> {
            List<ChampionshipTeam> stats = championshipTeamRepo.findByChampionship(championshipTeam.getChampionship());
            result.addAll(stats);
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
